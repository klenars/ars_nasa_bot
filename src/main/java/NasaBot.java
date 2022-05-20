import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class NasaBot {
    private final TelegramClient telegramClient;
    private final Gson gson;

    public NasaBot() {
        telegramClient = new TelegramClient();
        gson = new Gson();
    }

    public void updateHandler(String requestBody) {
        Update update = gson.fromJson(requestBody, Update.class);
        Message message = update.getMessage();

        switch (message.getText()) {
            case "/start":
                sayBotInfo(message);
                break;
            default:
                sayHello(message);
        }
    }

    private void sayBotInfo(Message message) {
        String response = telegramClient.sendGetMe();
        JsonElement object = JsonParser.parseString(response).getAsJsonObject().get("result");
        User bot = gson.fromJson(object, User.class);
        int chatId = message.getChatId();
        telegramClient.sendMessage(bot.toPrint(), chatId);
    }

    private void sayHello(Message message) {
        int chatId = message.getChatId();
        telegramClient.sendMessage("Hello!", chatId);
    }
}