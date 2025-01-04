package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: вынести блоки подключения куда нибудь
public class UserDaoJDBCImpl implements UserDao {
    private final Util util;

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    public void createUsersTable() {
        try (Connection connection = util.getConnection()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(50) NOT NULL, " +
                    "age SMALLINT NOT NULL" +
                    ")";
            connection.createStatement().execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnection()) {
            String dropTableSQL = "DROP TABLE IF EXISTS users";
            connection.createStatement().execute(dropTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection()) {
            String saveUserSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(saveUserSQL)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnection()) {
            String removeUserSQL = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserSQL)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = util.getConnection()) {
            String getAllUsersSQL = "SELECT id, name, lastName, age FROM users";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(getAllUsersSQL)) {

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnection()) {
            String clearAllUserSQL = "DELETE FROM users";
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(clearAllUserSQL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
