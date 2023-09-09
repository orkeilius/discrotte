package com.discrotte.backend;

public class User {
	protected String name;
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
