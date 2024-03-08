// UserServiceImp.java

package model;

import repository.UserRepository;

import java.util.List;

public class UserServiceImp implements UserService {
    @Override
    public List<User> getAllUsers() {
        return UserRepository.getAllUsers();
    }

    @Override
    public User searchByID(Integer id) {
        return UserRepository.searchUserByID(id);
    }

    @Override
    public boolean createUser(User user) {
        return UserRepository.createUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return UserRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return UserRepository.deleteUser(id);
    }
}
