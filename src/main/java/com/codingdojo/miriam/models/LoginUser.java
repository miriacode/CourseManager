package com.codingdojo.miriam.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
	
	@NotEmpty(message="Please, type a email")
	@Email(message="Please, type a valid email")
	private String email;
	
	@NotEmpty(message="Please, type a password")
	@Size(min=6, max=128, message="Please, type a valid password (from 6 to 128 characters long).")
	private String password;

	public LoginUser() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
