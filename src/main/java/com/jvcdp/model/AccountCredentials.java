package com.jvcdp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jvcdp.model.validation.ValidEmail;

public class AccountCredentials {
	@NotNull
	@Size(min=6,max = 255)
	private String userName;
	
	@NotNull
	private String password;

	@NotNull
	@ValidEmail
	private String emailAddress;
	
	@NotNull
	@Size(min=3,max=255)
	private String apiId;

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public AccountCredentials() {
		
	}
	
	public AccountCredentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	  public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
