import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;

public class LoginServer {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/login", (HttpExchange exchange) -> {

            if ("POST".equals(exchange.getRequestMethod())) {

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(exchange.getRequestBody()));

                String body = reader.readLine(); // username=...&password=...

                String[] params = body.split("&");
                String username = params[0].split("=")[1];
                String password = params[1].split("=")[1];

                LoginService service = new LoginService();
                boolean success = service.loginUser(username, password);

                String response = success ? "SUCCESS" : "FAIL";
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080...");
    }
}