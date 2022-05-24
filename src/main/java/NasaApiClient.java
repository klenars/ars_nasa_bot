import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NasaApiClient {
    private final String API_KEY;
    private final String apiUrl;
    private final HttpClient nasaClient;

    public NasaApiClient() {
        API_KEY = "DEMO_KEY";
        nasaClient = HttpClient.newHttpClient();
        apiUrl = "https://api.nasa.gov/planetary";
    }

    public String getAPOD() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(apiUrl + "/apod" + "?api_key=" + API_KEY))
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        return sendRequest(request);
    }

    private String sendRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = nasaClient.send(request, HttpResponse.BodyHandlers.ofString());
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
