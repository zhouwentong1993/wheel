package com.wentong.tomcat.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                String body = bufferedReader.readLine();
                System.out.println(body);
                if (body != null) {
                    System.out.println("Receive: " + body);
                    printWriter.println("Hello World");
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("thread " + Thread.currentThread() + "die");
    }
}
