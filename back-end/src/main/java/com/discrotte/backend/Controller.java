package com.discrotte.backend;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controller {

	@PostMapping("/login")
	public String Login(@RequestParam String name,@RequestParam String password) {
		User user = User.QueryUser(name);
		if (user.CheckPassword(password)) {
			return user.name;
		}
		throw new ResponseStatusException(
		           HttpStatus.FORBIDDEN);
	}
}