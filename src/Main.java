import javax.print.attribute.standard.PresentationDirection;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Main {

    static String URL = Config.URL;
    static String USER = Config.USER;
    static String PASSWORD = Config.PASSWORD;
    public static void main(String[] args) throws SQLException{
        insert("andrew3", "password##");
//        select();
        delete("test5");
        select();
    }

    private static Connection connection() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    private static boolean select() throws SQLException{
        try{
            Statement statement = connection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM User");

            while (rs.next()){
                System.out.print("Id: " + rs.getInt("id") + " | ");
                System.out.print("Login: " + rs.getString("login") + " | ");
                System.out.println("Password: " + rs.getString("password") + " | ");

            }
        } catch (Exception e){
            System.out.println("Query not executed");
        }
        return true;
    }


    private static boolean insert(String login, String password){
        try{
            String query = "INSERT INTO User (login, password) VALUES (\"" + login + "\", \"" + getPasswordHash(password) + "\")";
            Statement statement = connection().createStatement();
            statement.executeUpdate(query);
        } catch (Exception exception){
            System.out.println("Insert error");
        }
        return true;
    }


    private static boolean delete(String login){
        try {
            Statement statement = connection().createStatement();

            String query = "DELETE FROM User WHERE login = " + "\"" + login + "\"";
            statement.executeUpdate(query);
        } catch (Exception exception){
            System.out.println("User not deleted");
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



