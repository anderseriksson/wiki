import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by Anders Eriksson.
 */
public class OneServletContext {
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8888);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/wiki");
        server.setHandler(context);

//        context.addServlet(new ServletHolder(new DumpCSVServlet()),"/*");
        context.addServlet(new ServletHolder(new MarkdownServlet()),"/*");


        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("src/main/resources");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resourceHandler, context});
        server.setHandler(handlers);

        server.start();
        server.join();

        //TODO consider starting a browser here
    }

}
