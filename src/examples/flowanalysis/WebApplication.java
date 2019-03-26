package examples.flowanalysis;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class WebApplication
    extends HttpServlet
{
    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        doGet(req, resp);
    }
    
    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        ServletOutputStream response = resp.getOutputStream();
        addHeader(response);
        
        String sUserName = req.getParameter("username");
        
        response.print("Welcome, ");
        response.print(sUserName);
        response.print("\n");
        
        addFooter(response);
        response.flush();
    }
    
    private void addHeader(ServletOutputStream response)
        throws IOException
    {
        response.print("<HTML>\n");
        response.print("<HEAD><TITLE>Example webpage</TITLE></HEAD>\n");
        response.print("<BODY>\n");
    }
    private void addFooter(ServletOutputStream response)
        throws IOException
    {
        response.print("</BODY>\n");
        response.print("</HTML>\n");
    }
}
