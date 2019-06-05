package com.wentong.howtomcatworks.ex03.connector.http;

import com.wentong.howtomcatworks.ex02.Response;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ResponseFacade implements ServletResponse {

    private ServletResponse servletResponse;

    public ResponseFacade(HttpResponse response) {
        this.servletResponse = response;
    }

    @Override
    public String getCharacterEncoding() {
        return servletResponse.getCharacterEncoding();
    }

    @Override
    public String getContentType() {
        return servletResponse.getContentType();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return servletResponse.getOutputStream();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return servletResponse.getWriter();
    }

    @Override
    public void setCharacterEncoding(String s) {
        servletResponse.setCharacterEncoding(s);
    }

    @Override
    public void setContentLength(int i) {
        servletResponse.setContentLength(i);
    }

    @Override
    public void setContentType(String s) {
        servletResponse.setContentType(s);
    }

    @Override
    public void setBufferSize(int i) {
        servletResponse.setBufferSize(i);
    }

    @Override
    public int getBufferSize() {
        return servletResponse.getBufferSize();
    }

    @Override
    public void flushBuffer() throws IOException {
        servletResponse.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        servletResponse.resetBuffer();
    }

    @Override
    public boolean isCommitted() {
        return servletResponse.isCommitted();
    }

    @Override
    public void reset() {
        servletResponse.reset();
    }

    @Override
    public void setLocale(Locale locale) {
        servletResponse.setLocale(locale);
    }

    @Override
    public Locale getLocale() {
        return servletResponse.getLocale();
    }
}
