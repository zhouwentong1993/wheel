package com.wentong.howtomcatworks.ex03.connector.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class ResponseFacade implements HttpServletResponse {

    private HttpServletResponse servletResponse;

    public ResponseFacade(HttpResponse response) {
        this.servletResponse = response;
    }

    @Override
    public void addCookie(Cookie cookie) {
        servletResponse.addCookie(cookie);
    }

    @Override
    public boolean containsHeader(String s) {
        return servletResponse.containsHeader(s);
    }

    @Override
    public String encodeURL(String s) {
        return servletResponse.encodeURL(s);
    }

    @Override
    public String encodeRedirectURL(String s) {
        return servletResponse.encodeRedirectURL(s);
    }

    @Override
    public String encodeUrl(String s) {
        return servletResponse.encodeURL(s);
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return servletResponse.encodeRedirectURL(s);
    }

    @Override
    public void sendError(int i, String s) throws IOException {
        servletResponse.sendError(i, s);
    }

    @Override
    public void sendError(int i) throws IOException {
        servletResponse.sendError(i);
    }

    @Override
    public void sendRedirect(String s) throws IOException {
        servletResponse.sendRedirect(s);
    }

    @Override
    public void setDateHeader(String s, long l) {
        servletResponse.setDateHeader(s, l);
    }

    @Override
    public void addDateHeader(String s, long l) {
        servletResponse.addDateHeader(s, l);
    }

    @Override
    public void setHeader(String s, String s1) {
        servletResponse.setHeader(s, s1);
    }

    @Override
    public void addHeader(String s, String s1) {
        servletResponse.addHeader(s, s1);
    }

    @Override
    public void setIntHeader(String s, int i) {
        servletResponse.setIntHeader(s, i);
    }

    @Override
    public void addIntHeader(String s, int i) {
        servletResponse.addIntHeader(s, i);
    }

    @Override
    public void setStatus(int i) {
        servletResponse.setStatus(i);
    }

    @Override
    public void setStatus(int i, String s) {
        servletResponse.setStatus(i, s);
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
