package controller;

import model.User;
import model.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public User searchUserByID(Integer id) {
        return userService.searchByID(id);
    }

    public boolean createUser(User user) {
        return userService.createUser(user);
    }

    public boolean updateUser(User user) {
        return userService.updateUser(user);
    }

    public boolean deleteUser(Integer id) {
        return userService.deleteUser(id);
    }
}
