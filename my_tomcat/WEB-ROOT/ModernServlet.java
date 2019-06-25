import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class ModernServlet extends HttpServlet {

  public void init(ServletConfig config) {
    System.out.print("ModernServlet -- init");
  }

  public void doGet(HttpServletRequest request, 
    HttpServletResponse response) 
    throws ServletException, IOException {
    
    response.setContentType("text/html");
    response.setStatus(200);
    PrintWriter out = response.getWriter();
    out.print("<html>");
    out.print("<head>");
    out.print("<title>Modern Servlet</title>");
    out.print("</head>");
    out.print("<body>");

    out.print("<h2>Headers</h2");
    Enumeration headers = request.getHeaderNames();
    while (headers.hasMoreElements()) {
      String header = (String) headers.nextElement();
      out.print("<br>" + header + " : " + request.getHeader(header));
    }

    out.print("<br><h2>Method</h2");
    out.print("<br>" + request.getMethod());

    out.print("<br><h2>Parameters</h2");
    Enumeration parameters = request.getParameterNames();
    while (parameters.hasMoreElements()) {
      String parameter = (String) parameters.nextElement();
      out.print("<br>" + parameter + " : " + request.getParameter(parameter));
    }

    out.print("<br><h2>Query String</h2");
    out.print("<br>" + request.getQueryString());

    out.print("<br><h2>Request URI</h2");
    out.print("<br>" + request.getRequestURI());

    out.print("</body>");
    out.print("</html>");

    out.flush();
  }
}