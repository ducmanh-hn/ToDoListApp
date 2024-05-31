package com.AndroidAPI.service;

import com.AndroidAPI.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUser();
    User getUserById(Integer id);
    User createUser(User user);
    User updateUser(Integer id, User newUser);
    boolean deleteUserById(Integer id);
}
