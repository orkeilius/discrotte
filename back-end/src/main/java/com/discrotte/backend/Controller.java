package com.discrotte.backend;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import com.discrotte.backend.model.User;
import com.discrotte.backend.service.UserService;

@RestController
public class Controller {
	
	 @Autowired
	 private UserService userService;
	
	

	@PostMapping("/createUSer")
	public void CreateUser(@RequestParam String name,@RequestParam String password) {
		userService.saveUser(new User(name, password));
	}
	@PostMapping("/ping")
	public String Logout() {
		userService.saveUser(new User("e","e"));
		return "pong";
		//placeholder for logout
	}
	@PostMapping("/message")
	public String apiMessage() {
		return "holla";
		//placeholder for logout
	}
}