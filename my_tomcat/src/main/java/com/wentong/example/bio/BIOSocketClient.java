package com.wentong.example.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BIOSocketClient {

    public static void main(String[] args) throws Exception {
//        while (true) {
            Socket socket = new Socket("127.0.0.1", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("hello");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(bufferedReader.readLine());
            socket.close();
            printWriter.close();
//        }
    }
}
