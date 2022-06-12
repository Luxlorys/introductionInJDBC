

/*
* table = User (id, login, password, salt)
* create table Users (
*
    id       int auto_increment,
    login    varchar(32)  not null,
    password varchar(100) not null,
    salt     varchar(6)   not null,
    constraint Users_pk
        primary key (id)
);

create unique index Users_login_uindex
    on Users (login);

create unique index Users_salt_uindex
    on Users (salt);
* */


import java.sql.*;

public class UserRepository {

    private final static String URL = "jdbc:mysql://localhost:3306/db";
    private final static String USER = "gromozeqa";
    private final static String PASSWORD = "password#";


    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public final boolean getAllUsers() {
        try {
            String query = "SELECT  * FROM Users";

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Database view: ");
            while (resultSet.next()) {
                System.out.print("Id: " + resultSet.getInt("id") + " | ");
                System.out.print("Login: " + resultSet.getString("login") + " | ");
                System.out.println("Password: " + resultSet.getString("password") + " | ");

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }


    public final boolean deleteUser(String login) {
        try {
            String query = "DELETE FROM Users WHERE login = ?";

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();

            System.out.println(login + " - successfully deleted from database");
            return true;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public final boolean insertNewUser(String login, String password) {
        Hashing hash = new Hashing();
        String[] currentPassword = hash.getSecurePassword(password);
        String query = "INSERT INTO Users (login, password, salt) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, currentPassword[0] + currentPassword[1]);
            preparedStatement.setString(3, currentPassword[1]);

            preparedStatement.executeUpdate();
            System.out.println("User: " + login + " successfully added");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
        return true;
    }

    // condition = user login
    public final boolean changeLogin(String oldLogin, String newLogin) {
        String query = "UPDATE Users SET login = ? WHERE login = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, oldLogin);

            preparedStatement.executeUpdate();

            System.out.println("User: " + oldLogin + " successfully updated");
            return true;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public final boolean changePassword(String login, String newPassword) {
        Hashing hash = new Hashing();
        String[] currentPassword = hash.getSecurePassword(newPassword);
        String query = "UPDATE Users SET password = ?, salt = ? WHERE login = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1, currentPassword[0] + currentPassword[1]);
            preparedStatement.setString(2, currentPassword[1]);
            preparedStatement.setString(3, login);

            preparedStatement.executeUpdate();

            System.out.println("User: " + login + " successfully updated");
            return true;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}
