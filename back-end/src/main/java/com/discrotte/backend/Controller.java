package com.discrotte.backend;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@GetMapping("/login")
	public String Login(@RequestParam String name,@RequestParam String password) {
		System.out.println(User.QueryUser(name).name);
		System.out.println("tze");
		if (User.QueryUser(name).CheckPassword(password)) {
			return "connected";
		}
		return "invalid ";
	}
}