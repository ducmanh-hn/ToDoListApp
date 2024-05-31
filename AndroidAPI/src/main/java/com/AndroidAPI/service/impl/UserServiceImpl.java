package com.AndroidAPI.service.impl;

import com.AndroidAPI.entity.User;
import com.AndroidAPI.repository.UserRepo;
import com.AndroidAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Integer id, User newUser) {
        newUser.setId(id);
        return userRepo.save(newUser);
    }

    @Override
    public boolean deleteUserById(Integer id) {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
