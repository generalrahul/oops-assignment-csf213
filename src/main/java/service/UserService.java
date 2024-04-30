package service;

import model.User;

public interface UserService {
    User registerUser(User newUser);
    User loginUser(String email, String password);
}
