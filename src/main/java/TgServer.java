import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class TgServer {
    private static final int PORT = 8443;
    private final HttpServer httpServer;
    private final NasaBot nasaBot;

    public TgServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        nasaBot = new NasaBot();

        httpServer.createContext("/", this::requestHandler);
    }

    private void requestHandler(HttpExchange exchange) {
        try {
            String requestBody = readRequestBody(exchange);
            exchange.sendResponseHeaders(200, 0);
            System.out.println(requestBody);
            nasaBot.updateHandler(requestBody);

        } catch (IOException e) {
            System.out.println("Запрос не прочитан!");
            e.printStackTrace();

        } finally {
            exchange.close();
        }
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes());
    }

    public void start() {
        httpServer.start();
    }

    public void stop() {
        httpServer.stop(0);
    }
}
