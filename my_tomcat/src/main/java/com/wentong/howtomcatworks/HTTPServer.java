package com.wentong.howtomcatworks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {


    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8088, 1, InetAddress.getByName("127.0.0.1"));
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String content;

        while ((content = bufferedReader.readLine()) != null) {
            System.out.println(content);
        }

        socket.close();

//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write();
    }

}
