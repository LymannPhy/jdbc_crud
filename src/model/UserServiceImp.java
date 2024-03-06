package model;

import repository.UserRepository;

import java.util.List;

public class UserServiceImp implements UserService{
    @Override
    public List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }

    @Override
    public User searchByID(Integer id) {
        return UserRepository.searchUserByID(id);
    }
}
