package com.discrotte.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.discrotte.backend.model.Message;
import com.discrotte.backend.repository.MessageRepository;



@Service
public class MessageService {

    @Autowired
    private MessageRepository MessageRepository;

    public Optional<Message> getMessage(final int id) {
       return MessageRepository.findById(id);   
    }
    
    public List<Message> getOldMessage(final Date date) {
    	return MessageRepository.findFirst10ByDateLessThanOrderByDateDesc(date);   
    }
    
    public List<Message> getNewMessage(final Date date) {
    	return MessageRepository.findFirst10ByDateGreaterThanOrderByDateAsc(date);   
    }

    public Iterable<Message> getMessage() {
        return MessageRepository.findAll();
    }

    public void deleteMessage(final Message message) {
    	MessageRepository.delete(message);;
    }

    public Message saveMessage(Message message) {
    	Message savedMessage = MessageRepository.save(message);
        return savedMessage;
    }

}