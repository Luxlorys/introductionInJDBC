import config.Config;

import java.sql.*;

public class Login {

    private final static String URL = Config.getURL();
    private final static String USER = Config.getUSER();
    private final static String PASSWORD = Config.getPASSWORD();

    // needs no comment
    private static User getUserByLogin(String login) {
        User user = null;

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE login = " + "\"" + login + "\"");

            while (resultSet.next()) {
                user = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("salt"));
            }
            return user;

        } catch (SQLException exception) {
            throw new RuntimeException(exception);

        } catch (NullPointerException exception) {
            throw new NullPointerException("");
        }
    }


    // dataFromUser {login, password} compare with user {login, password, salt}
    public final boolean verification(String login, String password) {
        User user = getUserByLogin(login);

        // I will change this later
        // I have to hash the password and then add the salt
        password += user.getSalt(); // add salt to user password

        return login.equals(user.getLogin()) && password.equals(user.getPassword());
    }
}
