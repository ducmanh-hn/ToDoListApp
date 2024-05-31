package com.AndroidAPI.controller;

import com.AndroidAPI.entity.User;
import com.AndroidAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(userService.getAllUser());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Integer id){
        return userService.deleteUserById(id);
    }
}
