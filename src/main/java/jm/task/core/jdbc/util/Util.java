package jm.task.core.jdbc.util;


import java.sql.Connection;


public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/kata";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "root";

    private Connection connection = null;

    public Connection getConnection() {
        try {
            return java.sql.DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
