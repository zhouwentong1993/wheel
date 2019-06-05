package com.wentong.howtomcatworks.ex03.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 负责处理 HTTP 连接
 */
public class HttpConnector implements Runnable {

    boolean stop = false;
    private String scheme = "http";

    public String getScheme() {
        return scheme;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8088, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                continue;
            }
            HttpProcessor httpProcessor = new HttpProcessor(this);
            httpProcessor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
