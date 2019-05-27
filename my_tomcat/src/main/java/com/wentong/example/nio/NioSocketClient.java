package com.wentong.example.nio;

public class NioSocketClient {

    public static void main(String[] args) throws Exception{

        new Thread(new TimeClientHandle("127.0.0.1",8080),"TimeClient-01").start();
    }

}
