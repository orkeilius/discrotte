package com.discrotte.backend;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.discrotte.backend.model.User;
import com.discrotte.backend.repository.UserRepository;



@Service
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    public Optional<User> getUser(final Long id) {
        return UserRepository.findById(id);
    }

    public Iterable<User> getUsers() {
        return UserRepository.findAll();
    }

    public void deleteUser(final Long id) {
        UserRepository.deleteById(id);
    }

    public User saveUser(User User) {
        User savedUser = UserRepository.save(User);
        return savedUser;
    }

}