package com.bits.csf213.roombooking.service;

import com.bits.csf213.roombooking.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User newUser);
    User loginUser(String email, String password);
    List<User> findAllUsers();
    User findUserById(Long id);
}