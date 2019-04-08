package com.wentong.tomcat.nio;

public class NIOSocketServer01 {

    // 使用 NIO 的模式，创建 server
    public static void main(String[] args) {
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(8080);
        new Thread(multiplexerTimeServer).start();

    }


}
