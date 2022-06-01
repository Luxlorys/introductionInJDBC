import config.Config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import config.Config.*;

public class Main {

    static String URL = Config.getURL();
    static String USER = Config.getUSER();
    static String PASSWORD = Config.getPASSWORD();
    public static void main(String[] args) throws SQLException{
//        insert("andrew3", "password##");
////        select();
//        delete("test5");
        select();
    }

    private static Statement getStatement() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        return statement;
    }

    private static boolean select() throws SQLException{
        try{
            ResultSet rs = getStatement().executeQuery("SELECT * FROM User");

            while (rs.next()){
                System.out.print("Id: " + rs.getInt("id") + " | ");
                System.out.print("Login: " + rs.getString("login") + " | ");
                System.out.println("Password: " + rs.getString("password") + " | ");

            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return true;
    }


    private static boolean insert(String login, String password){
        try{
            String query = "INSERT INTO User (login, password) VALUES (\"" + login + "\", \"" + getPasswordHash(password) + "\")";
            getStatement().executeUpdate(query);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return true;
    }


    private static boolean delete(String login){
        try {
            String query = "DELETE FROM User WHERE login = " + "\"" + login + "\"";
            getStatement().executeUpdate(query);
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return true;
    }


    private static boolean update(String login, String password){return true;}


    private static String getPasswordHash(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // not recommended to use
            byte[] digest = md.digest(password.getBytes());
            final String hash = new BigInteger(1, digest).toString(16);

            return hash;
        } catch(NoSuchAlgorithmException e){
            System.err.println("Error");
        }
        return "";
    }
}



