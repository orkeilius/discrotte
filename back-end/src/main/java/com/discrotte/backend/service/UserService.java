package com.discrotte.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    public Optional<User> getCurrentUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return this.getUser(authentication.getName());
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