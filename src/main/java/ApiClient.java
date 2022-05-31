public interface ApiClient {

    String sendMessage(String text, int chatId);
    String sendGetMe();

}
