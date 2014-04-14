import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Anders Eriksson.
 */
public class HelloServlet extends HttpServlet {

    private String greeting = "Hello World";

    public HelloServlet() {
        //s
    };

    public HelloServlet(String greeting) {
        this.greeting = greeting;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>" + greeting + "</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());

        try {
            response.getWriter().println("<table><tr>");

            Map<String, Map<String, String>> data = ReadCSV.getDataFromCSV("root.csv");
            for (String header  : data.get("header").keySet()) {
                response.getWriter().println("<th>" + header + "</th>");
            }
            response.getWriter().println("</tr>");
            response.getWriter().println("<tr>");

            Map<String, String> row0 = data.get("row 0");
            for (String item  : row0.keySet()) {
                response.getWriter().println("<td>" + row0.get(item) + "</td>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
