import org.eclipse.jetty.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anders Eriksson.
 */
public class MarkdownServlet extends HttpServlet {

    public MarkdownServlet() {
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String mdName = request.getPathInfo();
        if ((mdName.length()>0) && (mdName.charAt(0)=='/')) {
            mdName = mdName.substring(1);
        }
        System.out.println("mdName:" + mdName);
        String md = "";

        if (! StringUtil.isBlank(mdName)) {
            md = ReadMD.getDataFromMD(mdName + ".md");
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <body>\n" +
                " <h2>" + mdName + "</h2>\n" +
                "    <textarea id=\"text-input\" oninput=\"this.editor.update()\"\n" +
                "              rows=\"6\" cols=\"60\">" +
//                "Type **Markdown** here." +
                md +
                "</textarea>\n" +
                "    <div id=\"preview\"> </div>\n" +
                "    <script src=\"../markdown.min.js\"></script>\n" +
                "    <script>\n" +
                "      function Editor(input, preview) {\n" +
                "        this.update = function () {\n" +
                "          preview.innerHTML = markdown.toHTML(input.value);\n" +
                "        };\n" +
                "        input.editor = this;\n" +
                "        this.update();\n" +
                "      }\n" +
                "      var $ = function (id) { return document.getElementById(id); };\n" +
                "      new Editor($(\"text-input\"), $(\"preview\"));\n" +
                "    </script>\n" +
                "  </body>\n" +
                "</html>");
    }

}
