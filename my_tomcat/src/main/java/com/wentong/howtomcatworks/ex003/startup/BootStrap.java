package com.wentong.howtomcatworks.ex003.startup;

import com.wentong.howtomcatworks.ex003.connector.http.HttpConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 整体启动类，只和连接器交互
 */
public class BootStrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.start();
        LOGGER.info("server start ^_^");
    }
}
