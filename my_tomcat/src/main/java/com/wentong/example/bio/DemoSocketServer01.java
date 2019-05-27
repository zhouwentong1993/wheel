package com.wentong.example.bio;

import com.wentong.example.nio.ServerHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class DemoSocketServer01 {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(new ServerHandler(socket)).start();
        }
    }
}
