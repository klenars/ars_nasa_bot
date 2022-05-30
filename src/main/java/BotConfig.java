import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BotConfig {
    private static int TG_SERVER_PORT;
    private static String TG_API_TOKEN;
    private static String TG_API_URL;
    private static String Ngrok_TOKEN;
    private static String Ngrok_URL;
    private static String NASA_API_TOKEN;
    private static String NASA_API_URL;

    static {
        Properties property = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")){

            property.load(fileInputStream);

            TG_SERVER_PORT = Integer.parseInt(property.getProperty("TG_SERVER_PORT"));
            TG_API_TOKEN = property.getProperty("TG_API_TOKEN");
            TG_API_URL = property.getProperty("TG_API_URL");
            Ngrok_TOKEN = property.getProperty("Ngrok_TOKEN");
            Ngrok_URL = property.getProperty("Ngrok_URL");
            NASA_API_TOKEN = property.getProperty("NASA_API_TOKEN");
            NASA_API_URL = property.getProperty("NASA_API_URL");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    public static int getTgServerPort() {
        return TG_SERVER_PORT;
    }

    public static String getTgApiToken() {
        return TG_API_TOKEN;
    }

    public static String getTgApiUrl() {
        return TG_API_URL;
    }

    public static String getNgrokToken() {
        return Ngrok_TOKEN;
    }

    public static String getNgrokUrl() {
        return Ngrok_URL;
    }

    public static String getNasaApiToken() {
        return NASA_API_TOKEN;
    }

    public static String getNasaApiUrl() {
        return NASA_API_URL;
    }
}
