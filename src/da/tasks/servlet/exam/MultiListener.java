package da.tasks.servlet.exam;

import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MultiListener implements ServletContextListener, ServletContextAttributeListener
{

    public void contextInitialized(ServletContextEvent event)
    {
        Results results = new Results();
        ServletContext ctx = event.getServletContext();
        ctx.setAttribute("results", results);
        int clientId = 0;
        ctx.setAttribute("id", clientId);
    }

    public void contextDestroyed(ServletContextEvent event)
    {
    }

    public void attributeAdded(ServletContextAttributeEvent event)
    {
        System.out.println("add: " + event.getName() + event.getValue());
        ServletContext ctx = event.getServletContext();
        String attName = event.getName();
        if (attName.startsWith("exam"))
        {
            String[] eventInfo = attName.split("-");
            Results r = (Results)ctx.getAttribute("results");
            r.addAnswer(
                    Question.valueOf(eventInfo[1].toUpperCase()),
                    (Boolean)event.getValue(),
                    Integer.valueOf(eventInfo[2]));
            return;
        }
        if (attName.equals("reset"))
        {
            Iterator<String> it = ctx.getAttributeNames().asIterator();
            while (it.hasNext())
            {
                String nextToDelete = it.next();
                if (nextToDelete.startsWith("exam"))
                {
                    ctx.removeAttribute(nextToDelete);
                }
            }
            Results r = (Results) ctx.getAttribute("results");
            r.clear();
            ctx.removeAttribute("reset");
            ctx.setAttribute("id", 0);
        }
    }

    public void attributeReplaced(ServletContextAttributeEvent event)
    {
        ServletContext ctx = event.getServletContext();
        String attName = event.getName();
        if (attName.startsWith("exam"))
        {
            String[] eventInfo = attName.split("-");
            Results r = (Results)ctx.getAttribute("results");
            r.addAnswer(
                    Question.valueOf(eventInfo[1].toUpperCase()),
                    (Boolean)ctx.getAttribute(attName),
                    Integer.valueOf(eventInfo[2]));
            return;
        }
    }

    public void attributeRemoved(ServletContextAttributeEvent event)
    {
    }

}
