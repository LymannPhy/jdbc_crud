package controller;

import model.User;
import model.UserService;
import model.UserServiceImp;
import repository.UserRepository;

import java.util.List;

public class UserController {
    private final UserService userService = new UserServiceImp();
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    public User searchUserByID(Integer id){
        return userService.searchByID(id);
    }
}
