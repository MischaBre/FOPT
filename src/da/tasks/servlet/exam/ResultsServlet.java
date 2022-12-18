package da.tasks.servlet.exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Results")
public class ResultsServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext ctx = getServletContext();
        if (request.getParameter("reset") != null)
        {
            ctx.setAttribute("reset", "bam");
        }
        Results results = (Results) ctx.getAttribute("results");
        int q1num = results.getYesAnswers(Question.Q1).size() + results.getNoAnswers(Question.Q1).size();
        int q2num = results.getYesAnswers(Question.Q2).size() + results.getNoAnswers(Question.Q2).size();
        int q1YesPercent = q1num > 0 ? results.getYesAnswers(Question.Q1).size() * 100 / q1num : 0;
        int q1NoPercent = q1num > 0 ? 100 - q1YesPercent : 0;
        int q2YesPercent = q2num > 0 ? results.getYesAnswers(Question.Q2).size() * 100 / q2num : 0;
        int q2NoPercent = q2num > 0 ? 100 - q2YesPercent : 0;

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Results Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Ergebnisse</h1>");
        out.println("<table border=\"1\"><tr><td>Frage</td><td>Anz. Antworten</td><td>Ja</td><td>Nein</td></tr>");
        out.println("<tr><td>3+1=4?</td>");
        out.println("<td>" + String.valueOf(q1num) + "</td><td>" + q1YesPercent + "%</td><td>" + q1NoPercent + "%</td></tr>");
        out.println("<tr><td>7+2=12?</td>");
        out.println("<td>" + String.valueOf(q2num) + "</td><td>" + q2YesPercent + "%</td><td>" + q2NoPercent + "%</td></tr>");
        out.println("</table><br>");
        out.println("<form method=\"get\" action=\"DownloadServlet\"><input type=\"hidden\" name=\"download\" value=\"bam\" />");
        out.println("<input type=\"submit\" value=\"Download\" /></form>");
        out.println("<form method=\"get\" action=\"Results\"><input type=\"hidden\" name=\"reset\" value=\"bam\" />");
        out.println("<input type=\"submit\" value=\"Zurücksetzen\" /></form>");
        out.println("</body></html>");
    }
}
