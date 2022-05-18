import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
        JsonElement jsonElement = JsonParser.parseString(response);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray resultArray = jsonObject.getAsJsonArray("result");
        JsonObject jsonObject2 = resultArray.get(0).getAsJsonObject();
        JsonObject message = jsonObject2.getAsJsonObject("message");
        return message.get("message_id").getAsInt();
    }

    static int getChatId(String response) {
        JsonElement jsonElement = JsonParser.parseString(response);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray resultArray = jsonObject.getAsJsonArray("result");
        JsonObject jsonObject2 = resultArray.get(0).getAsJsonObject();
        JsonObject message = jsonObject2.getAsJsonObject("message");
        JsonObject chat = message.getAsJsonObject("chat");
        return chat.get("id").getAsInt();
    }
}
