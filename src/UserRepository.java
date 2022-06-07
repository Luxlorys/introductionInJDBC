

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
import java.util.HashMap;
import java.util.Set;

public class UserRepository {

    private static String URL = "jdbc:mysql://localhost:3306/db";
    private static String USER = "gromozeqa";
    private static String PASSWORD = "password#";


    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static Statement getStatement() {
        try {
            return getConnection().createStatement();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    public final User getUserById(int id) {
        User user = null;

        try {
            ResultSet resultSet = getStatement().executeQuery("SELECT * FROM Users WHERE id = " + id);

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

    public final boolean getAllUsers() {
        try {
            ResultSet resultSet = getStatement().executeQuery("SELECT * FROM Users");
            System.out.println("Database view: ");
            while (resultSet.next()) {
                System.out.print("Id: " + resultSet.getInt("id") + " | ");
                System.out.print("Login: " + resultSet.getString("login") + " | ");
                System.out.print("Password: " + resultSet.getString("password") + " | ");
//                System.out.print("Salt: " + rs.getString("salt") + " | ");

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }


    public final boolean deleteUser(String login) {
        try {
            String query = "DELETE FROM Users WHERE login = " + "\"" + login + "\"";
            getStatement().executeUpdate(query);
            System.out.println(login + " - successfully deleted from database");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }


//    private static boolean update(String login, String password, int id) {
//        try {
//
//            String query = "UPDATE User SET login = " + "\"" + login + "\", password = " + "\"" + password + "\" WHERE id = " + id;
//            getStatement().executeUpdate(query);
//            System.out.println("Successfully updated");
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//        return true;
//    }

}
