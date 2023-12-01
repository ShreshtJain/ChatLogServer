package com.example.ChaltLog.Server.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ChaltLog.Server.Entity.User;
import com.example.ChaltLog.Server.Exceptions.ResourceNotFoundException;
import com.example.ChaltLog.Server.Repository.UserRepository;

@Service
public class UserService {
	
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException());
    }

    public User createUser(User user) {
        
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
    	userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException());
        userRepository.deleteById(userId);
    }

    public void deleteUsers()
    {
    	userRepository.deleteAll();
    }

}