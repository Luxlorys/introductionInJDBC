package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDAO implements DAO{

    private final static String URL = "jdbc:mysql://localhost:3306/db";
    private final static String USER = "gromozeqa";
    private final static String PASSWORD = "password#";


    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
