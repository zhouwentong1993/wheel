package com.wentong.howtomcatworks;

import java.io.*;

public class Response {

    private Request request;
    private OutputStream outputStream;
    private static final String WEB_ROOT = "WEB-ROOT";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws Exception {
        File file = new File(System.getProperty("user.dir") + File.separator + WEB_ROOT + File.separator + request.getUri());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {
            if (file.exists()) {
                String content;
                while ((content = bufferedReader.readLine()) != null) {
                    outputStreamWriter.write(content);
                }

            } else { // file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
