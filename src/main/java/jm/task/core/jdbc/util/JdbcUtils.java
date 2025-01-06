package jm.task.core.jdbc.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;

// По-хорошему, dotenv файл должен быть в gitignore, но для удобства проверки я его оставил.
public class JdbcUtils {
    private static final Dotenv environment = Dotenv.load();
    private static final String URL = environment.get("URL");
    private static final String USERNAME = environment.get("USERNAME");
    private static final String PASSWORD = environment.get("PASSWORD");

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
