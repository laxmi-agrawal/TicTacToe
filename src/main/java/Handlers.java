import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Handlers {
    public static String getHTML(String sBody) {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>title</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <!-- page content -->\n" +
                sBody +
                "  </body>\n" +
                "</html>";
        return html;
    }

    public static void handleRequestIndex(HttpExchange exchange) throws IOException {
        String response = getHTML("<p>Play Game</p>" + "<a href=\"welcome\">welcome</a>");
        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static Map<String, String> queryParser(String params) {
        Map<String, String> paramsMap = new HashMap<>();
        if (!params.startsWith("?")) {
            return null;
        }
        String[] pairs = params.substring(1).split("&");
        for (String pair : pairs) {
            String[] split = pair.split("=");
            paramsMap.put(split.length == 0 ? "" : split[0], split.length == 1 ? "" : split[1]);
        }
        return paramsMap;
    }

    public static void handleRequestWelcome(HttpExchange exchange) throws IOException {
        String response = getHTML("<p>WELCOME</p>");
        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
