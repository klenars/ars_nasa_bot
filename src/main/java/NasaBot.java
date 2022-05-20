import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NasaBot {
    private final TelegramClient telegramClient;

    public NasaBot() {
        telegramClient = new TelegramClient();
    }

    public void sendHello(String requestBody) {
        int chat_id = getChatId(requestBody);
        telegramClient.sendMessage("Hello!", chat_id);

    }

    private int getChatId(String response) {
        return getMessage(response)
                .getAsJsonObject("chat")
                .get("id").getAsInt();
    }

    private JsonObject getMessage(String response) {
        return JsonParser.parseString(response)
                .getAsJsonObject()
                .getAsJsonObject("message");
    }
}


// {"update_id":906575370,
//  "message":{"message_id":56,
//              "from":{"id":450844544,"is_bot":false,"first_name":"\u0410\u0440\u0441\u0435\u043d\u0438\u0439","username":"Klen_Ars","language_code":"ru"},
//              "chat":{"id":450844544,"first_name":"\u0410\u0440\u0441\u0435\u043d\u0438\u0439","username":"Klen_Ars","type":"private"},
//              "date":1653048577,"text":"1234"
//              }
// }