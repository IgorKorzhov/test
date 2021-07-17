package com.example.library.library.service;

import com.example.library.library.model.Role;
import com.example.library.library.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();

    List<Role> getAllRoles();

    User createNewUser(User user);

    User getUserById(Long pid);

    User updateUser(User user);

    void deliteUserById(Long pid);

    List<User> filter(String str);

    Optional<User> getUserByLogin(String login);
}
