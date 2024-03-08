package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    public static List<User> getAllUsers(){
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        PropertiesLoader.LoadPropertiesFile();
        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while (resultSet.next()){
                userList.add(
                        new User(
                                resultSet.getInt("user_id"),
                                (UUID) resultSet.getObject("user_uuid"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_email"),
                                resultSet.getString("user_password"),
                                resultSet.getBoolean("is_deleted"),
                                resultSet.getBoolean("is_verified")
                        )
                );
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return userList;
    }
    public static User searchUserByID(Integer id){
        String sql = "SELECT *FROM users WHERE user_id = ?";
        PropertiesLoader.LoadPropertiesFile();
        try(
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                Statement statement = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                )
        {
            preparedStatement.setInt(1,id);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ){
                while (resultSet.next()){
                    return new User(
                            resultSet.getInt("user_id"),
                            (UUID) resultSet.getObject("user_uuid"),
                            resultSet.getString("user_name"),
                            resultSet.getString("user_email"),
                            resultSet.getString("user_password"),
                            resultSet.getBoolean("is_deleted"),
                            resultSet.getBoolean("is_verified")
                    );
                }
            }

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return new User();
    }
}
