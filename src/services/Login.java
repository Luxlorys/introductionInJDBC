package services;

import entity.User;

import java.sql.*;

public class Login {

    private final static String URL = "jdbc:mysql://localhost:3306/db";
    private final static String USER = "gromozeqa";
    private final static String PASSWORD = "password#";

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
        Hashing hashing = new Hashing();

        String hashOfPassword = hashing.getSecurePassword(password)[0];
        hashOfPassword += user.getSalt(); // add salt to user password

        return login.equals(user.getLogin()) && hashOfPassword.equals(user.getPassword());
    }
}
