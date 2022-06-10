

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


    // maybe later I will be change statement for preparedStatement
    private static Statement getStatement() {
        try {
            return getConnection().createStatement();
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


    public final boolean insertNewUser(String login, String password) {
        Hashing hash = new Hashing();
        String query = "INSERT INTO Users (login, password, salt) VALUES (" + "\"" + login + "\", "
                                                                + "\"" + hash.getSecurePassword(password)[0] + "\", "
                                                                + "\"" + hash.getSecurePassword(password)[1] + "\")";
        try {
            getStatement().executeUpdate(query);
            System.out.println("User: " + login + " successfully added");
            return true;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}
