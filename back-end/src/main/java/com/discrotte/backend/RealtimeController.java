package com.discrotte.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.discrotte.backend.model.Message;
import com.discrotte.backend.model.User;
import com.discrotte.backend.service.MessageService;
import com.discrotte.backend.service.UserService;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RealtimeController extends TextWebSocketHandler {
	private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	private record SessionInfo(WebSocketSession session, User prenom) {
	}
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		//System.out.println(userService.getUserByToken(session.getUri().toString().substring(30)).get().getName());
		sessions.add(session);

	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) {

		//return;
		User author = (User) userService.getUser(session.getPrincipal().getName()).get();

		Message newMessage = new Message(author, message.getPayload());
		messageService.saveMessage(newMessage);

		for (WebSocketSession s : sessions) {
			try {
				if(s.isOpen()) {
					s.sendMessage(new TextMessage(newMessage.getJsonString().toString()));//new TextMessage(newMessage.getJsonString().toString()));					
				}
				else {
					sessions.remove(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}