package com.discrotte.backend;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import com.discrotte.backend.model.User;

@RestController
public class Controller {
	
	 @Autowired
	 private UserService userService;
	
	
	@PostMapping("/login")
	public String Login(@RequestParam String name,@RequestParam String password) {
		User user = User.QueryUser(name);
		userService.saveUser(user);
		if (user.CheckPassword(password)) {
			return user.name;
		}
		throw new ResponseStatusException(
		           HttpStatus.FORBIDDEN);
	}
	@PostMapping("/logout")
	public void Logout() {
		//placeholder for logout
	}
}