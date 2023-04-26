package com.creppyfm.donetaskmanager.service;

import com.creppyfm.donetaskmanager.model.User;
import com.creppyfm.donetaskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
