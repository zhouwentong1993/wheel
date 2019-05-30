package com.wentong.howtomcatworks.ex02;

public class StaticResourceProcessor1 {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
