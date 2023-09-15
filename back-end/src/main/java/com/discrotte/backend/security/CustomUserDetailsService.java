package com.discrotte.backend.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.discrotte.backend.model.User;
import com.discrotte.backend.repository.UserRepository;

 
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
         
        if (user.isEmpty()) {
        	System.out.println("c'est non");
            throw new UsernameNotFoundException("Could not find user");
        }
        System.out.println(user.get().name);
        return new CustomUserDetails(user.get());
    }

}