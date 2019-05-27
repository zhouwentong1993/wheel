package com.wentong.example.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoSocketServer {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
        String read;
        StringBuilder sb = new StringBuilder();
        while (true) {
            String body = bufferedReader.readLine();
            if (body != null) {

            }
        }
//        while ((read = bufferedReader.readLine()) != null) {
//            sb.append(read);
//            System.out.println(read);
//        }


    }
}
