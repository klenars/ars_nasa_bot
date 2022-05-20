public class Update {
    private int update_id;
    private Message message;

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Update{" +
                "update_id=" + update_id +
                ", message=" + message +
                '}';
    }
}

class Message {
    private int message_id;
    private User from;
    private int date;
    private Chat chat;
    private String text;

    public int getChatId() {
        return chat.getId();
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", from=" + from +
                ", date=" + date +
                ", chat=" + chat +
                ", text='" + text + '\'' +
                '}';
    }
}

class User {
    private int id;
    private String first_name;
    private String username;

    public String toPrint() {
        return "Hi! I'm NASA telegram bot!" +
                " My id: " + id +
                ", my first_name: " + first_name +
                ", my username: " + username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

class Chat {
    private int id;
    private String first_name;
    private String username;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

