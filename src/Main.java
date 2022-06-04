import config.Config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Main {

    static String URL = Config.getURL();
    static String USER = Config.getUSER();
    static String PASSWORD = Config.getPASSWORD();

    public static void main(String[] args) throws SQLException {
        insert("second_user", "userTwoo");
        select();
    }

    private static Statement getStatement() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        return statement;
    }

    private static boolean select() throws SQLException {
        try {
            ResultSet rs = getStatement().executeQuery("SELECT * FROM User");
            System.out.println("Database view: ");
            while (rs.next()) {
                System.out.print("Id: " + rs.getInt("id") + " | ");
                System.out.print("Login: " + rs.getString("login") + " | ");
                System.out.println("Password: " + rs.getString("password") + " | ");

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }


    private static boolean insert(String login, String password) {
        try {
            System.out.println("Inserting a new user to the database...");
            String query = "INSERT INTO User (login, password) VALUES (\"" + login + "\", \"" + getPasswordHash(password) + "\")";
            getStatement().executeUpdate(query);
            System.out.println(login + " - successfully added to database");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }


    private static boolean delete(String login) {
        try {
            String query = "DELETE FROM User WHERE login = " + "\"" + login + "\"";
            getStatement().executeUpdate(query);
            System.out.println(login + "successfully deleted from database");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }


    private static boolean update(String login, String password) {
        return true;
    }


    private static String getPasswordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // not recommended to use
            byte[] digest = md.digest(password.getBytes());
            final String hash = new BigInteger(1, digest).toString(16);

            return hash;
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error");
        }
        return "";
    }
}



