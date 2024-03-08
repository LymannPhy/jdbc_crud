package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    public static List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            while (resultSet.next()) {
                userList.add(
                        new User(

                                resultSet.getInt("user_id"),
                                resultSet.getString("user_uuid"), // Use getString() for UUID
                                resultSet.getString("user_name"),
                                resultSet.getString("user_email"),
                                resultSet.getString("user_password"),
                                resultSet.getBoolean("is_deleted"),
                                resultSet.getBoolean("is_verified")
                        )
                );
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return userList;
    }
    public static User searchUserByID(Integer id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {
                while (resultSet.next()) {
                    // Convert user_uuid from String to UUID
                    UUID uuid = UUID.fromString(resultSet.getString("user_uuid"));
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("user_uuid"), // Assigning the String value directly
                            resultSet.getString("user_name"),
                            resultSet.getString("user_email"),
                            resultSet.getString("user_password"),
                            resultSet.getBoolean("is_deleted"),
                            resultSet.getBoolean("is_verified")
                    );
                }
            }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return new User();
    }


    public static boolean createUser(User user) {
        String sql = "INSERT INTO users (user_uuid, user_name, user_email, user_password, is_deleted, is_verified) VALUES (?, ?, ?, ?, ?, ?)";
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setObject(1, UUID.randomUUID());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserEmail());
            preparedStatement.setString(4, user.getUserPassword());
            preparedStatement.setBoolean(5, user.getIsDeleted());
            preparedStatement.setBoolean(6, user.getIsVerified());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    public static boolean updateUser(User user) {
        String sql = "UPDATE users SET user_name=?, user_email=?, user_password=?, is_deleted=?, is_verified=? WHERE user_id=?";
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.setBoolean(4, user.getIsDeleted());
            preparedStatement.setBoolean(5, user.getIsVerified());
            preparedStatement.setInt(6, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(Integer id) {
        String sql = "DELETE FROM users WHERE user_id=?";
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return false;
        }
    }
}
