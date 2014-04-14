import org.eclipse.jetty.server.Server;
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
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new HelloServlet()),"/*");

        server.start();
        server.join();
    }

}
