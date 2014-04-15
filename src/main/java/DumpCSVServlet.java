import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Anders Eriksson.
 */
public class DumpCSVServlet extends HttpServlet {

    public DumpCSVServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("session=" + request.getSession(true).getId());

        try {
            response.getWriter().println("<table><tr>");

            Map<String, Map<String, String>> data = ReadCSV.getDataFromCSV("root.csv");
            for (String header  : data.get("header").keySet()) {
                response.getWriter().print("<th>" + header + "</th>");
            }
            response.getWriter().println("</tr>");

            int i = 0;

            Map<String, String> row = data.get("row " + i++);
            while (row != null) {
                response.getWriter().println("<tr>");
                for (String item  : row.keySet()) {
                    response.getWriter().print("<td>" + row.get(item) + "</td>");
                }
                response.getWriter().println("</tr>");
                row = data.get("row " + i++);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
