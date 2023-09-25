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
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "role", nullable = false)
	private String role;
	
	private String token;
	
	
	
	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public User(String name,String password){
		this.name = name;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt()) ;
		this.role = "USER";
	}
	
    public User() {}
    
	public Boolean CheckPassword(String password) {
		// placeholder for password validation with salt and security
		return BCrypt.checkpw(password, this.password);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}