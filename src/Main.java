import java.sql.*;

public class Main {

    static String URL = Config.URL;
    static String USER = Config.USER;
    static String PASSWORD = Config.PASSWORD;
    public static void main(String[] args) throws SQLException{
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
            e.getMessage();
        }
        return true;
    }

    private static boolean insert(String login, String password){return true;}

    private static boolean delete(String login){return true;}

    private static boolean update(String login, String password){return true;}
}



