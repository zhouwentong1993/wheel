package com.wentong.example.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIODemo {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"),8888));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 创建 Reactor 线程，创建多路复用器并启动线程
        Selector selector = Selector.open();
        new Thread(()->{}).start();

        // 将 serverSocketChannel 注册到多路复用器 Selector 上，监听 ACCEPT 事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 多路复用器在线程 run 方法的无限循环体内轮询准备就绪的 key
        int num = selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();
        }

        // 多路复用器监听到新客户端接入
        SocketChannel channel = serverSocketChannel.accept();

        // 设置客户端链路为非阻塞模式
        channel.configureBlocking(false);
        channel.socket().setReuseAddress(true);

        // 将新接入的客户端连接注册在多路复用器上，监听读操作
        SelectionKey key = channel.register(selector, SelectionKey.OP_READ);

        // 处理数据
//        channel.read()
    }

}
