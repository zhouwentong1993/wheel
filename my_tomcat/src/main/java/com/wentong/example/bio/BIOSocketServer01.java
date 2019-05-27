package com.wentong.example.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞 io，每一个连接对应着一个线程，容易出现瓶颈。
 */
public class BIOSocketServer01 {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ServerHandler(socket)).start();
        }
    }
}
