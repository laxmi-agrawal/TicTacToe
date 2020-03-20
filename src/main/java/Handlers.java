import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Handlers {
    private static String SESSION_ID = "sessionid";
    private static Map<Integer,GameState> sessionState = new HashMap<>();
    private static String form = "<form method = \"get\" action = \"/\">" +
            "<label for=\"position\">Choose a position:</label>"+
            "<select id=\"position\" name=\"position\">"+
            "<option value=\"0\">(0,0)</option>"+
            "<option value=\"1\">(0,1)</option>"+
            "<option value=\"2\">(0,2)</option>"+
            "<option value=\"3\">(1,0)</option>"+
            "<option value=\"4\">(1,1)</option>"+
            "<option value=\"5\">(1,2)</option>"+
            "<option value=\"6\">(2,0)</option>"+
            "<option value=\"7\">(2,1)</option>"+
            "<option value=\"8\">(2,2)</option>"+
            "</select>"+
            "<br/>"+
            "<label for=\"value\">Value</label>"+
            "<select id=\"value\" name=\"value\">"+
            "<option value=\"X\">X</option>"+
            "<option value=\"O\">O</option>"+
            "</select>"+
            "<br/>"+
            "<input type=\"submit\" value=\"Play\"/>"+
            "</form>";

    private static GameState gameState = new GameState();

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

    public static int getSessionId(HttpExchange exchange) {
        List<String> cookies = exchange.getRequestHeaders().get("Cookie");
        if(null != cookies){
            for(String cookie : cookies) {
                String[] split = cookie.split("=");
                if(split[0].equals(Handlers.SESSION_ID)){
                    return Integer.parseInt(split[1]);
                }
            }
        }
        Random random = new Random();
        return random.nextInt();
    }

    public static void handleRequestIndex(HttpExchange exchange) throws IOException {
        int sessionId = getSessionId(exchange);
        if(!sessionState.containsKey(sessionId)){
            sessionState.put(sessionId, new GameState());
        }
        GameState gameState = sessionState.get(sessionId);

        String header = "";

        if(exchange.getRequestURI().getQuery() != null && !exchange.getRequestURI().getQuery().isEmpty()) {
            Map<String, String> queryMap = queryParser(exchange.getRequestURI().getQuery());
            header = gameState.update(queryMap.get("value").charAt(0), Integer.parseInt(queryMap.get("position")));
        }

        String response = getHTML("<p>Play Game</p>" + "<pre>\n" +
                header + "\n" + gameState.toString()+
                "</pre>" +
                form);
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Set-Cookie", String.format("%s=%s; path=/",Handlers.SESSION_ID, sessionId));
        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static Map<String, String> queryParser(String params) {
        Map<String, String> paramsMap = new HashMap<>();
        String[] pairs = params.split("&");
        for (String pair : pairs) {
            String[] split = pair.split("=");
            paramsMap.put(split.length == 0 ? "" : split[0], split.length == 1 ? "" : split[1]);
        }
        return paramsMap;
    }
}
