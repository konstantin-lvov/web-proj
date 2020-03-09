package ru.kl.proj.dao;

import ru.kl.proj.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    void createUser(User user);
    User readUser(String username);
    void updateUser(User user);
    void deleteUser(String username);
}
