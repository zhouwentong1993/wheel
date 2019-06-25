package com.wentong.howtomcatworks.ex004.startup;

import com.wentong.howtomcatworks.ex004.core.SimpleContainer;
import org.apache.catalina.connector.http.HttpConnector;

import java.util.Scanner;

public class BootStrap {

    public static void main(String[] args) throws Exception{
        HttpConnector httpConnector = new HttpConnector();
        httpConnector.setContainer(new SimpleContainer());
        httpConnector.initialize();
        httpConnector.start();

        new Scanner(System.in).next();
    }

}
