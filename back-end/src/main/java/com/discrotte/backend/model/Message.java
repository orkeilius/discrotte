package com.discrotte.backend.model;


import java.util.Date;

import org.json.JSONObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

	
	@Id
	@GeneratedValue()
	protected int id;
	
	@ManyToOne
	private User author;
	
	private String text;
	
	private Date date;

	public Message(User author, String text) {
		super();
		this.author = author;
		this.text = text;
		this.date = new Date(System.currentTimeMillis());
	}
	public Message() {}
	
	public JSONObject getJsonString() {
		return new JSONObject()
		.put("text",this.text)
		.put("author",this.author.getName())
		.put("date",this.date.getTime());
	}
}

