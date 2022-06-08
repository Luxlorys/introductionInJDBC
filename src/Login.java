import java.sql.*;
import config.Config;
public class Login {

    private final static String URL = Config.getURL();
    private final static String USER = Config.getUSER();
    private final static String PASSWORD = Config.getPASSWORD();

    private static User getUserByLogin(String login) {
        User user = null;

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE login = " + "\"" + login + "\"" );

            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("salt"));
            }
            return user;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public final boolean verification() {
        return true;
    }
}
