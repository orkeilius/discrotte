package com.discrotte.backend.model;

import org.springframework.security.crypto.bcrypt.BCrypt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "userInfo")
public class User {
	
	@Id
	@Column(name = "name", nullable = false)
	public String name;
	
	@Column(name = "password", nullable = false)
	public String password;
	
	public  User(String name,String password){
		this.name = name;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt()) ;
	}
	
    public User() {}
    
	public Boolean CheckPassword(String password) {
		// placeholder for password validation with salt and security
		return BCrypt.checkpw(password, this.password);
	}
}
