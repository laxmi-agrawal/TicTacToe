import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class TicTacToeServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(1024), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(Handlers::handleRequestIndex);

        HttpContext context1 = server.createContext("/welcome");
        context1.setHandler(Handlers::handleRequestWelcome);
        server.start();
    }
}


