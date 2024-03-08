package model;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User searchByID(Integer id);
    boolean createUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(Integer id);
}
