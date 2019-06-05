package com.wentong.howtomcatworks.ex03.startup;

import com.wentong.howtomcatworks.ex03.connector.http.HttpConnector;

/**
 * 整体启动类，只和连接器交互
 */
public class BootStrap {
    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
    }
}
