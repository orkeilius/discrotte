package com.discrotte.backend;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

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
	
	
	@PostMapping("/login")
	public String Login(@RequestParam String name,@RequestParam String password) {
		// userService.saveUser(User.QueryUser(name));
		Optional<User> request  = userService.getUser(name);
		if (request.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		else {
			User user = request.get();
			if (user.CheckPassword(password)) {
				return user.name;
			}
		}
		throw new ResponseStatusException(
		           HttpStatus.FORBIDDEN);
	}
	@PostMapping("/logout")
	public void Logout() {
		//placeholder for logout
	}
}