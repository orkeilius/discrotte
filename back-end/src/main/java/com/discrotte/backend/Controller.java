package com.discrotte.backend;

import java.util.HashMap;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import com.discrotte.backend.model.User;
import com.discrotte.backend.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class Controller {
	
	 @Autowired
	 private UserService userService;
	 
	 @PostMapping("/getUser")
	 public HashMap<String, String> getUser(@RequestParam String username) {
		 Optional<User> request = userService.getUser(username);
		 if (request.isEmpty()) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unknown user");
		 }
		 HashMap<String, String> user = new HashMap<>();
		 
		 user.put("name", request.get().name);
		 user.put("role", "USER");
		 	
		 return user;
		}
	
	@PostMapping("/createUSer")
	public void CreateUser(@RequestParam String name,@RequestParam String password) {
		userService.saveUser(new User(name, password));
	}
	@PostMapping("/login")
	public Void Login() {
		return null;
		//placeholder for logout
	}
	@PostMapping("/message")
	public String apiMessage() {
		return "holla";
		//placeholder for logout
	}
}