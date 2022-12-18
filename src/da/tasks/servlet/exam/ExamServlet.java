package da.tasks.servlet.exam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletContext servletContext = getServletContext();
        Cookie idCookie = null, q1Cookie = null, q2Cookie = null;
        int id = 0;
        String q1String = null, q2String = null;
        Boolean q1 = null, q2 = null;

        if (request.getCookies() != null)
        {
            //System.out.println("Cookies");
            for (Cookie c : request.getCookies())
            {
                //System.out.println(c.getName());
                switch (c.getName())
                {
                    case "exam-id":
                        idCookie = c;
                        break;
                    case "exam-q1":
                        q1Cookie = c;
                        break;
                    case "exam-q2":
                        q2Cookie = c;
                        break;
                    default:
                        break;
                }
            }
        }

        if (request.getMethod().equals("GET"))
        {

            if (idCookie != null)
            {
                id = Integer.valueOf(idCookie.getValue());
            }
            else
            {
                if (request.getParameter("id") == null)
                {
                    id = (int) servletContext.getAttribute("id");
                    id++;
                    servletContext.setAttribute("id", id);
                }
                else
                {
                    id = Integer.valueOf(request.getParameter("id"));
                }
            }
            idCookie = new Cookie("exam-id", String.valueOf(id));
            idCookie.setMaxAge(3600);
            response.addCookie(idCookie);


            String requestQ1 = request.getParameter("q1");
            String requestQ2 = request.getParameter("q2");

            if (q1Cookie != null && requestQ1 != null && !q1Cookie.getValue().equals(requestQ1.equals("y") ? "true" : "false"))
            {
                q1Cookie.setValue(requestQ1.equals("y") ? "true" : "false");
            }
            if (q2Cookie != null && requestQ2 != null && !q2Cookie.getValue().equals(requestQ2.equals("y") ? "true" : "false"))
            {
                q2Cookie.setValue(requestQ2.equals("y") ? "true" : "false");
            }

            q1String = q1Cookie != null ? q1Cookie.getValue().equals("true") ? "y" : "n" : requestQ1;
            q2String = q2Cookie != null ? q2Cookie.getValue().equals("true") ? "y" : "n" : requestQ2;

            //System.out.println(q1String);

            if (q1String != null)
            {
                q1 = q1String.equals("y") ? true : false;
                servletContext.setAttribute("exam-q1-" + String.valueOf(id), q1);
                //System.out.println(servletContext.getAttribute("exam-q1-4711"));
                q1Cookie = new Cookie("exam-q1", q1String.equals("y") ? "true" : "false");
                q1Cookie.setMaxAge(3600);
                response.addCookie(q1Cookie);
            }
            if (q2String != null)
            {
                q2 = q2String.equals("y") ? true : false;
                servletContext.setAttribute("exam-q2-" + String.valueOf(id), q2);
                //System.out.println(servletContext.getAttribute("exam-q2-4711"));
                q2Cookie = new Cookie("exam-q2", q2String.equals("y") ? "true" : "false");
                q2Cookie.setMaxAge(3600);
                response.addCookie(q2Cookie);
            }
        }

        String q1ChoiceY = "";
        String q1ChoiceN = "";
        if (q1 != null)
        {
            q1ChoiceY = q1 ? "checked" : "";
            q1ChoiceN = q1 ? "" : "checked";
        }
        String q2ChoiceY = "";
        String q2ChoiceN = "";
        if (q2 != null)
        {
            q2ChoiceY = q2 ? "checked" : "";
            q2ChoiceN = q2 ? "" : "checked";
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Exam Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Prüfung</h1>");
        //out.println(request.getSession().getId());
        out.println("<form method=\"get\" action=\"ExamServlet\">");
        out.println("<table border=\"1\"><tr><td>Nr</td>");
        out.println("<td><input type=\"text\" name=\"id\" size=\"10\" readonly value=\"" + String.valueOf(id) + "\" /></td></tr>");
        out.println("<tr><td>3+1=4?</td>");
        out.println("<td><input type=\"radio\" name=\"q1\" value=\"y\" " + q1ChoiceY + " /> Ja " +
                "<input type=\"radio\" name=\"q1\" value=\"n\" " + q1ChoiceN + " /> Nein</td></tr>");
        out.println("<tr><td>7+2=12?</td>");
        out.println("<td><input type=\"radio\" name=\"q2\" value=\"y\" " + q2ChoiceY + "  /> Ja " +
                "<input type=\"radio\" name=\"q2\" value=\"n\" " + q2ChoiceN + "  /> Nein</td></tr>");
        out.println("</table><br><input type=\"submit\" value=\"Einreichen\" />");
        out.println("</form></body></html>");
    }
}
