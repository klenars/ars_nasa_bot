import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BotConfig {
    private static int TG_SERVER_PORT;
    private static String TELEGRAM_API_TOKEN;
    private static String TELEGRAM_API_URL;
    private static String NGROK_TOKEN;
    private static String NGROK_URL;
    private static String NASA_API_TOKEN;
    private static String NASA_API_URL;

    static {
        Properties property = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")){

            property.load(fileInputStream);

            TG_SERVER_PORT = Integer.parseInt(property.getProperty("TG_SERVER_PORT"));
            TELEGRAM_API_TOKEN = property.getProperty("TG_API_TOKEN");
            TELEGRAM_API_URL = property.getProperty("TG_API_URL");
            NGROK_TOKEN = property.getProperty("Ngrok_TOKEN");
            NGROK_URL = property.getProperty("Ngrok_URL");
            NASA_API_TOKEN = property.getProperty("NASA_API_TOKEN");
            NASA_API_URL = property.getProperty("NASA_API_URL");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public static int getTgServerPort() {
        return TG_SERVER_PORT;
    }

    public static String getTelegramApiToken() {
        return TELEGRAM_API_TOKEN;
    }

    public static String getTelegramApiUrl() {
        return TELEGRAM_API_URL;
    }

    public static String getNgrokToken() {
        return NGROK_TOKEN;
    }

    public static String getNgrokUrl() {
        return NGROK_URL;
    }

    public static String getNasaApiToken() {
        return NASA_API_TOKEN;
    }

    public static String getNasaApiUrl() {
        return NASA_API_URL;
    }
}