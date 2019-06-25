package com.wentong.howtomcatworks.ex002;

public class StaticResourceProcessor1 {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
