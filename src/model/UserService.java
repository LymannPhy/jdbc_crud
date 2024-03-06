package model;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User searchByID(Integer id);
}
