package com.wentong.tomcat.aio;

public class AioSocketServer {

    public static void main(String[] args) throws Exception{
        new Thread(new AsyncTimeServerHandler(8080),"AIO-AsyncTimeServerHandler").start();
    }

}
