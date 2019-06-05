package com.wentong.howtomcatworks.ex03.core;

import com.wentong.howtomcatworks.ex03.connector.http.HttpRequest;
import com.wentong.howtomcatworks.ex03.connector.http.HttpResponse;

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
