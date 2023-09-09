package com.discrotte.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "userInfo")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	 
	public String name;
	private String password;
	
	private  User(String name,String password){
		this.name = name;
		this.password = password;
	}
	
	
	public Boolean CheckPassword(String password) {
		// placeholder for password validation with salt and security
		return this.password.equals(password);
	}
	
	
	
	public static User QueryUser(String name) {
		// placeholder for query user from database
		return new User(name,"toto");
	}
}
