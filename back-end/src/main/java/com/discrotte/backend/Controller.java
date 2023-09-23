package com.discrotte.backend;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.discrotte.backend.model.User;
import com.discrotte.backend.model.Message;
import com.discrotte.backend.service.MessageService;
import com.discrotte.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
public class Controller {
	
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 private MessageService messageService;
	 
	 ObjectMapper mapper = new ObjectMapper();
	 
	 @PostMapping("/getUser")
	 public HashMap<String, String> getUser(@RequestBody String username) {
		 Optional<User> request = userService.getUser(username);
		 if (request.isEmpty()) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unknown user");
		 }
		 HashMap<String, String> user = new HashMap<>();
		 
		 user.put("name", request.get().getName());
		 user.put("role", "USER");
		 	
		 return user;
		}
	
	@PostMapping("/createUser")
	public void CreateUser(@RequestParam String name,@RequestParam String password) {
		userService.saveUser(new User(name, password));
	}
	@PostMapping("/login")
	public Void Login() {
		return null;
	}
	
	@PostMapping("/message/send")
	public void sendMessage(@RequestBody String text) {
		Optional<User> author = userService.getCurrentUser();
		if (author.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,"Unknown user");
		}
		messageService.saveMessage(new Message(author.get(), text));
	}
	
	@PostMapping(path = "/message/getOld")
	public String getOldMessage(@RequestBody String time) {
		Date date = new Date( Long.parseLong(time));
		List<Message> request =  messageService.getOldMessage(date);
		
		JSONArray out = new JSONArray();
		for (Iterator iterator = request.iterator(); iterator.hasNext();) {
			Message message = (Message) iterator.next();
			out.put(message.getJsonString());
			
		}
		return out.toString() ;
		
	}
	
	@PostMapping("/message/getNew")
	public String getNewMessage(@RequestBody String time) {
		Date date = new Date( Long.parseLong(time));
		List<Message> request =  messageService.getNewMessage(date);
		
		JSONArray out = new JSONArray();
		for (Iterator iterator = request.iterator(); iterator.hasNext();) {
			Message message = (Message) iterator.next();
			out.put(message.getJsonString());
		}
		return out.toString() ;
	}
}