package service;

import model.User;
import repository.UserRepository;
import util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User newUser) {
        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Email already in use");
        }
        newUser.setPassword(PasswordUtils.hashPassword(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User not found with email: " + email));
        if (!PasswordUtils.verifyPassword(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return user;
    }
}
