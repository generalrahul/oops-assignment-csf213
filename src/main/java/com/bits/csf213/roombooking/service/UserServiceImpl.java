package com.bits.csf213.roombooking.service;

import com.bits.csf213.roombooking.model.Booking;
import com.bits.csf213.roombooking.model.User;
import com.bits.csf213.roombooking.repository.UserRepository;
import com.bits.csf213.roombooking.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
            throw new IllegalStateException("Forbidden, Account already exists");
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

        @Override
        public List<User> findAllUsers() {
            List<User> users = userRepository.findAll();
            return users;
        }

        public User findUserById(Long userId) {
            // Implement logic here to retrieve a User from your database based on the userId.
            // If your data access uses JPA, you might use a repository method like:
            return userRepository.findById(userId).orElse(null);
        }
}
