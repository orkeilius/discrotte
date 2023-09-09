package com.discrotte.backend;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@PostMapping("/login")
	public String Login(@RequestParam String name,@RequestParam String password) {
		System.out.println(User.QueryUser(name).name);
		if (User.QueryUser(name).CheckPassword(password)) {
			return "connected";
		}
		return "invalid ";
	}
}