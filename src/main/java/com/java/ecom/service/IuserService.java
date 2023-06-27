package com.java.ecom.service;

import com.java.ecom.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IuserService {
    User createUser(User user);

    User getUserById(String userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(String userId);
}
