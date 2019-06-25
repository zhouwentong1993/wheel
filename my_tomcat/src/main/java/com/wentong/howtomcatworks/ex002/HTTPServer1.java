package com.wentong.howtomcatworks.ex002;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer1 {

    public static void main(String[] args) throws Exception {
        HTTPServer1 httpServer1 = new HTTPServer1();
        httpServer1.await();
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

                if (request.getUri().startsWith("/servlet")) {
                    new ServletProcessor1().process(request,response);
                } else {
                    new StaticResourceProcessor1().process(request, response);
                }
                shutdown = request.getUri().equals("/SHUTDOWN");
            }
        }
    }

}
