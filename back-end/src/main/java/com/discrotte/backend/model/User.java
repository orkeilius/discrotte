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
	@Column(name = "name", nullable = false)
	public String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	private  User(String name,String password){
		this.name = name;
		this.password = password;
	}
	
    public User() {}
    
	public Boolean CheckPassword(String password) {
		// placeholder for password validation with salt and security
		return this.password.equals(password);
	}
}
