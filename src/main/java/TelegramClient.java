import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        TOKEN = "5081727056:AAHKOhlUXEzmGsMdfeDuloxwzefqZ-5FiKk";
        httpClient = HttpClient.newHttpClient();
        url = "https://api.telegram.org/bot";
    }

    public void sendHello() {
        int lastMessageId = 0;

        while (true) {
            String response = getUpdates();
            int message_id = getMessageId(response);
            int chat_id = getChatId(response);
            if (message_id != lastMessageId) {
                sendMessage("Hello!", chat_id);
                lastMessageId = message_id;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getUpdates() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url + TOKEN + "/getUpdates" + "?offset=-1"))
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        return sendRequest(request);
    }

    private String sendMessage(String text, int chatId) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url + TOKEN + "/sendMessage" + "?chat_id=" + chatId + "&text=" + text))
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

    private int getMessageId(String response) {
        return getMessage(response)
                .get("message_id").getAsInt();
    }

    private int getChatId(String response) {
        return getMessage(response)
                .getAsJsonObject("chat")
                .get("id").getAsInt();
    }

    private JsonObject getMessage(String response) {
        return JsonParser.parseString(response)
                .getAsJsonObject()
                .getAsJsonArray("result")
                .get(0).getAsJsonObject()
                .getAsJsonObject("message");
    }
}
