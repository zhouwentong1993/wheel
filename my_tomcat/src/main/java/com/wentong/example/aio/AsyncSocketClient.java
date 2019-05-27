package com.wentong.example.aio;

import java.io.IOException;

public class AsyncSocketClient {

    public static void main(String[] args) throws IOException {
        new Thread(new AsyncTimeClientHandler("127.0.0.1",8080), "AIO-AsyncTimeClientHandler-01").start();
    }

}
