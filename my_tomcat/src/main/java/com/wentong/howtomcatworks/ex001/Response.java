package com.wentong.howtomcatworks.ex001;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class Response {

    private Request request;
    private OutputStream outputStream;
    private static final String WEB_ROOT = "WEB-ROOT";
    private static final int BUFFER_SIZE = 1024;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws Exception {
        if (StringUtils.isBlank(request.getUri())) {
            return;
        }
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        File file = new File(System.getProperty("user.dir") + File.separator + "my_tomcat/" + WEB_ROOT + File.separator + request.getUri());
        if (file.exists()) {
            fis = new FileInputStream(file);
            int ch = fis.read(bytes, 0, BUFFER_SIZE);
            while (ch!=-1) {
                outputStream.write(bytes, 0, ch);
                ch = fis.read(bytes, 0, BUFFER_SIZE);
            }
            fis.close();
        } else { // file not found
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            outputStream.write(errorMessage.getBytes());
        }


    }
}
