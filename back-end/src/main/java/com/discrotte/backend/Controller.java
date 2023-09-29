package com.discrotte.backend;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.discrotte.backend.model.User;
import com.discrotte.backend.security.UserAuthenticationProvider;
import com.discrotte.backend.model.Message;
import com.discrotte.backend.service.MessageService;
import com.discrotte.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class Controller {

	@Autowired
	private UserService userService;
	private final UserAuthenticationProvider userAuthenticationProvider;

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private Environment env;
	
	ObjectMapper mapper = new ObjectMapper();

	public Controller(UserService userService, UserAuthenticationProvider userAuthenticationProvider) {
		this.userService = userService;
		this.userAuthenticationProvider = userAuthenticationProvider;
		CreateAdmin();
	}

	@PostMapping("/signIn")
	public ResponseEntity<String> signIn(@AuthenticationPrincipal User user) {		
		user.setToken(userAuthenticationProvider.createToken(user.getName()));
		return ResponseEntity.ok(
				new JSONObject()
				.put("token", user.getToken())
				.put("role", user.getRole())
				.toString()
				);
	}


	
	public void CreateAdmin() {
		userService.saveUser(new User("Admin", "lolilol","ADMIN"));
	}
	
	@PostMapping("/createUser")
	public void CreateUser(@RequestBody String requestString) {
		User author =  (User) userService.getUser(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()).get();
		if(author.getRole() != "ADMIN") {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not Admin");
		}
		JSONObject request = new JSONObject(requestString);

		userService.saveUser(new User((String) request.get("newUser"),(String) request.get("newPassword"),"USER"));
	}



	@PostMapping("/message/send")
	public void sendMessage(@RequestBody String text) {
		User author = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		messageService.saveMessage(new Message(author, text));
	}

	@PostMapping(path = "/message/getOld")
	public String getOldMessage(@RequestBody String time) {
		Date date = new Date(Long.parseLong(time));
		List<Message> request = messageService.getOldMessage(date);

		JSONArray out = new JSONArray();
		for (Iterator iterator = request.iterator(); iterator.hasNext();) {
			Message message = (Message) iterator.next();
			out.put(message.getJsonString());

		}
		return out.toString();

	}

	@PostMapping("/message/getNew")
	public String getNewMessage(@RequestBody String time) {
		Date date = new Date(Long.parseLong(time));
		List<Message> request = messageService.getNewMessage(date);

		JSONArray out = new JSONArray();
		for (Iterator iterator = request.iterator(); iterator.hasNext();) {
			Message message = (Message) iterator.next();
			out.put(message.getJsonString());
		}
		return out.toString();
	}
}