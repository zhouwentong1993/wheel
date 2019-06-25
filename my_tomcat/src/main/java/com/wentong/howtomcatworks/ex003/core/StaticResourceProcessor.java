package com.wentong.howtomcatworks.ex003.core;

import com.wentong.howtomcatworks.ex003.connector.http.HttpRequest;
import com.wentong.howtomcatworks.ex003.connector.http.HttpResponse;

// 静态资源处理器
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
