import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TelegramClient {
    private final HttpClient httpClient;
    private final String url;
    private final String TOKEN;

    public TelegramClient() {
        TOKEN = System.getenv("TG_TOKEN");
        httpClient = HttpClient.newHttpClient();
        url = "https://api.telegram.org/bot";

        setWebhook(NgrokClient.getPublicUrl());
    }

    private void setWebhook(String serverUrl) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url + TOKEN + "/setWebhook" + "?url=" + serverUrl))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        sendRequest(request);
    }

    protected String sendGetMe() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url + TOKEN + "/getMe"))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        return sendRequest(request);
    }

    protected String sendMessage(String text, int chatId) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\"text\":\"" + text + "\"}"))
                .uri(URI.create(url + TOKEN + "/sendMessage" + "?chat_id=" + chatId))
                .header("content-type", "application/json")
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        return sendRequest(request);
    }

    private String sendRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Сервер вернул ответ: " + response.statusCode());
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
