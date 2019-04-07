package com.wentong.tomcat.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NIOServerServer01 {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("IP"),8888));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        // 创建 Reactor 线程，创建多路复用器并启动线程

        Selector selector = Selector.open();
        new Thread(()->{}).start();
    }

}
