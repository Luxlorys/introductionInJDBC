package config;

public class Config {
    static String URL = "jdbc:mysql://localhost:3306/db";
    static String USER = "gromozeqa";
    static String PASSWORD = "password#";

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getURL() {
        return URL;
    }
}
