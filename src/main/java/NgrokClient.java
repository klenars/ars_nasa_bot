import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NgrokClient {
    private static final String TOKEN = "29QmVn9e1QD2zRd1zNr7Xi5vD4E_2X95FRJPAhPhi7gRaSNZa";

    static String getPublicUrl() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.ngrok.com/tunnels"))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Ngrok-Version", "2")
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Сервер вернул ответ: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return JsonParser.parseString(response.body())
                .getAsJsonObject()
                .getAsJsonArray("tunnels")
                .get(0)
                .getAsJsonObject()
                .get("public_url")
                .getAsString();
    }
}
