package com.jvcdp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jvcdp.model.validation.ValidEmail;

public class AccountCredentials {
	@NotNull
	@ValidEmail
	private String userName;
	
	@NotNull
	private String password;
	
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

	}
