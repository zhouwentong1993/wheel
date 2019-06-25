package com.wentong.howtomcatworks.ex003.connector.http;


import com.wentong.howtomcatworks.ex003.core.ServletProcessor;
import com.wentong.howtomcatworks.ex003.core.StaticResourceProcessor;
import com.wentong.howtomcatworks.org.apache.catalina.util.RequestUtil;
import com.wentong.howtomcatworks.utils.StringManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 负责处理 HTTP 请求
 * 分为两个部分
 * ① 解析 HTTP 请求报文（最复杂）
 * ② 路由（现在比较简陋，后面会加强）
 */
public class HttpProcessor {

    private HttpConnector httpConnector;
    private HttpRequest httpRequest;
    private HttpResponse httpResponse;
    private HttpRequestLine httpRequestLine = new HttpRequestLine();
    private StringManager sm = StringManager.getManager(Constants.PACKAGE);

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {
        SocketInputStream socketInputStream;
        OutputStream outputStream;
        try {
            socketInputStream = new SocketInputStream(socket.getInputStream(), 2048);
            outputStream = socket.getOutputStream();

            httpRequest = new HttpRequest(socketInputStream);
            httpResponse = new HttpResponse(outputStream);
            httpResponse.setRequest(httpRequest);
            httpResponse.setHeader("Server", "Pyrmont Servlet Container");

            // 解析请求
            parseRequest(socketInputStream, outputStream);
            // 解析 header 数据，记得在 Tomcat 8 中是先解析 header 的，算法比较复杂，其实就是循环遍历
            parseHeaders(socketInputStream);

            if (httpRequest.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(httpRequest, httpResponse);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(httpRequest, httpResponse);
            }

            socket.close();

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 HttpRequest，整体拼装细节
     */
    private void parseRequest(SocketInputStream input, OutputStream output)
            throws IOException, ServletException {


        // Parse the incoming request line
        // 解析请求行
        input.readRequestLine(httpRequestLine);
        // 获取 request method
        String method =
                new String(httpRequestLine.method, 0, httpRequestLine.methodEnd);
        String uri;
        String protocol = new String(httpRequestLine.protocol, 0, httpRequestLine.protocolEnd);

        // Validate the incoming request line
        // 简单校验请求合法性，要有请求方法，url 长度要合法
        if (method.length() < 1) {
            throw new ServletException("Missing HTTP request method");
        } else if (httpRequestLine.uriEnd < 1) {
            throw new ServletException("Missing HTTP request URI");
        }
        // Parse any query parameters out of the request URI
        int question = httpRequestLine.indexOf("?");
        if (question >= 0) {
            httpRequest.setQueryString(new String(httpRequestLine.uri, question + 1,
                    httpRequestLine.uriEnd - question - 1));
            uri = new String(httpRequestLine.uri, 0, question);
        } else {
            httpRequest.setQueryString(null);
            uri = new String(httpRequestLine.uri, 0, httpRequestLine.uriEnd);
        }


        // Checking for an absolute URI (with the HTTP protocol)
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            // Parsing out protocol and host name
            if (pos != -1) {
                pos = uri.indexOf('/', pos + 3);
                if (pos == -1) {
                    uri = "";
                } else {
                    uri = uri.substring(pos);
                }
            }
        }

        // Parse any requested session ID out of the request URI
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(';');
            if (semicolon2 >= 0) {
                httpRequest.setRequestedSessionId(rest.substring(0, semicolon2));
                rest = rest.substring(semicolon2);
            } else {
                httpRequest.setRequestedSessionId(rest);
                rest = "";
            }
            httpRequest.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        } else {
            httpRequest.setRequestedSessionId(null);
            httpRequest.setRequestedSessionURL(false);
        }

        // Normalize URI (using String operations at the moment)
        String normalizedUri = normalize(uri);

        // Set the corresponding request properties
        // 设置成关联的 HttpRequest 对象，这样就能和 Servlet 挂上钩了
        ((HttpRequest) httpRequest).setMethod(method);
        httpRequest.setProtocol(protocol);
        if (normalizedUri != null) {
            ((HttpRequest) httpRequest).setRequestURI(normalizedUri);
        } else {
            ((HttpRequest) httpRequest).setRequestURI(uri);
        }

        if (normalizedUri == null) {
            throw new ServletException("Invalid URI: " + uri + "'");
        }
    }

    /**
     * Return a context-relative path, beginning with a "/", that represents
     * the canonical version of the specified path after ".." and "." elements
     * are resolved out.  If the specified path attempts to go outside the
     * boundaries of the current context (i.e. too many ".." path elements
     * are present), return <code>null</code> instead.
     *
     * @param path Path to be normalized
     */
    protected String normalize(String path) {
        if (path == null)
            return null;
        // Create a place for the normalized path
        String normalized = path;

        // Normalize "/%7E" and "/%7e" at the beginning to "/~"
        if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e"))
            normalized = "/~" + normalized.substring(4);

        // Prevent encoding '%', '/', '.' and '\', which are special reserved
        // characters
        if ((normalized.indexOf("%25") >= 0)
                || (normalized.indexOf("%2F") >= 0)
                || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0)
                || (normalized.indexOf("%2f") >= 0)
                || (normalized.indexOf("%2e") >= 0)
                || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }

        if (normalized.equals("/."))
            return "/";

        // Normalize the slashes and add leading slash if necessary
        if (normalized.indexOf('\\') >= 0)
            normalized = normalized.replace('\\', '/');
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid
        // (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0)
            return (null);

        // Return the normalized path that we have completed
        return (normalized);
    }

    /**
     * This method is the simplified version of the similar method in
     * org.apache.catalina.connector.http.HttpProcessor.
     * However, this method only parses some "easy" headers, such as
     * "cookie", "content-length", and "content-type", and ignore other headers.
     *
     * @param input The input stream connected to our socket
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a parsing error occurs
     */
    // 将解析到的 header 对应地映射到 HTTPRequest 类上。
    private void parseHeaders(SocketInputStream input)
            throws IOException, ServletException {
        while (true) {
            HttpHeader header = new HttpHeader();

            // Read the next header
            input.readHeader(header);
            if (header.nameEnd == 0) {
                if (header.valueEnd == 0) {
                    return;
                } else {
                    throw new ServletException
                            (sm.getString("httpProcessor.parseHeaders.colon"));
                }
            }

            String name = new String(header.name, 0, header.nameEnd);
            String value = new String(header.value, 0, header.valueEnd);
            httpRequest.addHeader(name, value);
            // do something for some headers, ignore others.
            if (name.equals("cookie")) {
                Cookie cookies[] = RequestUtil.parseCookieHeader(value);
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("jsessionid")) {
                        // Override anything requested in the URL
                        if (!httpRequest.isRequestedSessionIdFromCookie()) {
                            // Accept only the first session id cookie
                            httpRequest.setRequestedSessionId(cookies[i].getValue());
                            httpRequest.setRequestedSessionCookie(true);
                            httpRequest.setRequestedSessionURL(false);
                        }
                    }
                    httpRequest.addCookie(cookies[i]);
                }
            } else if (name.equals("content-length")) {
                int n = -1;
                try {
                    n = Integer.parseInt(value);
                } catch (Exception e) {
                    throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
                }
                httpRequest.setContentLength(n);
            } else if (name.equals("content-type")) {
                httpRequest.setContentType(value);
            }
        } //end while
    }
}
