package da.tasks.servlet.exam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=\"results.txt\"");
        PrintWriter out = response.getWriter();
        ServletContext ctx = getServletContext();
        Iterator<String> it = ctx.getAttributeNames().asIterator();
        while (it.hasNext())
        {
            String nextAtt = it.next();
            if (nextAtt.startsWith("exam"))
            {
                out.println(nextAtt + ": " + ctx.getAttribute(nextAtt));
            }
        }
    }
}
