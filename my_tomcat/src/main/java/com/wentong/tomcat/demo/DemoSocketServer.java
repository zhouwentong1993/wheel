package com.wentong.tomcat.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.WildcardType;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoSocketServer {

    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String read;
        StringBuilder sb = new StringBuilder();
        while ((read = bufferedReader.readLine()) != null) {
            sb.append(read);
            System.out.println(read);
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write(sb.toString() + "----->>>>");

    }
}
