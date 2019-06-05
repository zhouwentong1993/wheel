package com.wentong.howtomcatworks.ex03.core;

import com.wentong.howtomcatworks.ex03.connector.http.HttpRequest;
import com.wentong.howtomcatworks.ex03.connector.http.HttpResponse;
import com.wentong.howtomcatworks.ex03.connector.http.RequestFacade;
import com.wentong.howtomcatworks.ex03.connector.http.ResponseFacade;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import static com.wentong.howtomcatworks.ex02.Constant.WEB_ROOT;

// 普通的 Servlet 处理器
public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        String requestUri = request.getRequestURI();
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
            RequestFacade requestFacade = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);
            Servlet servlet = (Servlet) clazz.getDeclaredConstructor().newInstance();
            servlet.service(requestFacade, responseFacade);
        } catch (InstantiationException | ServletException | IOException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
