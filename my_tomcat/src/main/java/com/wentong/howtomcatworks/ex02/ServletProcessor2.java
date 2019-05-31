package com.wentong.howtomcatworks.ex02;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import static com.wentong.howtomcatworks.ex02.Constant.WEB_ROOT;

public class ServletProcessor2 {

    public void process(Request request, Response response) {
        String requestUri = request.getUri();
        String servletName = requestUri.substring(requestUri.lastIndexOf('/') + 1);
        URLClassLoader urlClassLoader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler urlStreamHandler = null;
            File classPath = new File(WEB_ROOT);
            String repository = new URL("file", null, classPath.getCanonicalPath() + File.separator).toString();
            urls[0] = new URL(null, repository, urlStreamHandler);
            urlClassLoader = new URLClassLoader(urls);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Class clazz = null;
        try {
            clazz = urlClassLoader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            Servlet servlet = (Servlet) clazz.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        } catch (InstantiationException | ServletException | IOException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
