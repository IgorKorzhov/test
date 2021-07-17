package com.example.library.library.repository;

import com.example.library.library.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> search(String str);
}
