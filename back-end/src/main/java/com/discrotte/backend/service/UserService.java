package com.discrotte.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.discrotte.backend.model.User;
import com.discrotte.backend.repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public Optional<User> getUser(final String name) {
       return UserRepository.findByName(name);
        
    }

    public Iterable<User> getUsers() {
        return UserRepository.findAll();
    }

    public void deleteUser(final User user) {
        UserRepository.delete(user);;
    }

    public User saveUser(User User) {
        User savedUser = UserRepository.save(User);
        return savedUser;
    }

}