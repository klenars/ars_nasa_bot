import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NgrokClient {
    private static final String TOKEN = BotConfig.getNgrokToken();
    private static final String Ngrok_URL = BotConfig.getNgrokUrl();

    public static String getPublicUrl() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(Ngrok_URL))
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
