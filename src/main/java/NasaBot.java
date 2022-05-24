import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class NasaBot {
    private final TelegramClient telegramClient;
    private final NasaApiClient nasaApiClient;
    private final Gson gson;

    public NasaBot() {
        telegramClient = new TelegramClient();
        nasaApiClient = new NasaApiClient();
        gson = new Gson();
    }

    public void updateHandler(String requestBody) {
        Update update = gson.fromJson(requestBody, Update.class);
        Message message = update.getMessage();

        switch (message.getText()) {
            case "/start":
                sendApod(message);
                break;
//            case "/apod":
//                sendApod(message);
//                break;
            default:
                sayHello(message);
        }
    }

    private void sendApod(Message message) {
        int chatId = message.getChatId();
        String nasaResponse = nasaApiClient.getAPOD();
        NasaApod apod = gson.fromJson(nasaResponse, NasaApod.class);

        String answer = apod.getTitle() + "\n \n"
                + apod.getExplanation() + "\n"
                + apod.getUrl();

        telegramClient.sendMessage(answer, chatId);
    }

    private void sayBotInfo(Message message) {
        String response = telegramClient.sendGetMe();
        JsonElement object = JsonParser.parseString(response)
                .getAsJsonObject()
                .get("result");

        User bot = gson.fromJson(object, User.class);
        int chatId = message.getChatId();

        telegramClient.sendMessage(bot.toPrint(), chatId);
    }

    private void sayHello(Message message) {
        int chatId = message.getChatId();
        telegramClient.sendMessage("Hello!", chatId);
    }
}