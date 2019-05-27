package com.wentong.example.bio;

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
//            while (true) {
//                String body = bufferedReader.readLine();
//            html = "http/1.1 200 ok\n"
//                    +"\n\n"
//                    +html;
            printWriter.println("HTTP/1.0 200 OK");//返回应答消息,并结束应答
            printWriter.println("Content-Type:text/html;charset=UTF-8");
            printWriter.println();// 根据 HTTP 协议, 空行将结束头信息

            printWriter.println("<h1> Hello Http Server</h1>");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
