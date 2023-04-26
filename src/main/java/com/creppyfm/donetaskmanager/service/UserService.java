package com.creppyfm.donetaskmanager.service;

import com.creppyfm.donetaskmanager.model.User;
import com.creppyfm.donetaskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword()); // Make sure to hash the password before saving
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setBirthday(updatedUser.getBirthday());
            existingUser.setAdmin(updatedUser.isAdmin());

            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

}
