import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        TelegramClient client = new TelegramClient();

        int lastMessageId = 0;

        while (true) {
            String response = client.getUpdates();
            int message_id = getMessageId(response);
            int chat_id = getChatId(response);
            if (message_id != lastMessageId) {
                client.sendMessage("Hello!", chat_id);
                lastMessageId = message_id;
            }
            Thread.sleep(1000);
        }


    }

    static int getMessageId(String response) {
        return getMessage(response)
                .get("message_id").getAsInt();
    }

    static int getChatId(String response) {
        return getMessage(response)
                .getAsJsonObject("chat")
                .get("id").getAsInt();
    }

    static JsonObject getMessage(String response) {
        return JsonParser.parseString(response)
                .getAsJsonObject()
                .getAsJsonArray("result")
                .get(0).getAsJsonObject()
                .getAsJsonObject("message");
    }
}
