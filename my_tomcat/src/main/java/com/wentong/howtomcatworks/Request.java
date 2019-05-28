package com.wentong.howtomcatworks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 代表请求类
 */
public class Request {

    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUri() {
        return uri;
    }

    public void parse() throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder requestBody = new StringBuilder(2048);
        String requestLine;
        while ((requestLine = bufferedReader.readLine()) != null) {
            requestBody.append(requestLine);
            System.out.println(requestLine);
        }
        System.out.println(requestBody);
        this.uri = parseUri(requestBody.toString());
    }


    /**
     * 解析 uri，举例 GET /index.html HTTP/1.1 返回 /index.html
     */
    private static String parseUri(String sourceUri) {
        int firstSpace = sourceUri.indexOf(' ');
        if (firstSpace != -1) {
            int secondSpace = sourceUri.indexOf(' ', firstSpace + 1);
            if (secondSpace != -1) {
                return sourceUri.substring(firstSpace + 1, secondSpace);
            }
        }
        throw new IllegalArgumentException("路径：" + sourceUri + "无法被解析");
    }
}
