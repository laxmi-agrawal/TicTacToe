import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class TicTacToeServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(1024), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(TicTacToeServer::handleRequestIndex);

        HttpContext context1 = server.createContext("/welcome");
        context1.setHandler(TicTacToeServer::handleRequestWelcome);
        server.start();
    }

    public static String getHTML(String sBody){
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <title>title</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <!-- page content -->\n" +
                sBody+
                "  </body>\n" +
                "</html>";
        return  html;
    }
    private static void handleRequestIndex(HttpExchange exchange) throws IOException {
        String response = getHTML("<p>Hello There</p>"+"<a href=\"welcome\">welcome</a>");

        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handleRequestWelcome(HttpExchange exchange) throws IOException {
        String response = getHTML("<p>WELCOME</p>");

        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}


