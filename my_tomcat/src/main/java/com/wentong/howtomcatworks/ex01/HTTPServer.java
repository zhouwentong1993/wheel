package com.wentong.howtomcatworks.ex01;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    public static void main(String[] args) throws Exception {
        HTTPServer httpServer = new HTTPServer();
        httpServer.await();
    }

    private void await() throws Exception {

        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(8088, 1, InetAddress.getByName("127.0.0.1"))) {
            boolean shutdown = false;
            while (!shutdown) {
                socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(socket.getOutputStream());
                response.setRequest(request);
                response.sendStaticResource();
                shutdown = request.getUri().equals("/SHUTDOWN");
            }
        }
    }

}
