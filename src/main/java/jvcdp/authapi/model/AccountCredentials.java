package jvcdp.authapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jvcdp.authapi.model.validation.ValidEmail;

public class AccountCredentials {
	@NotNull
	@ValidEmail
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	@Size(min=6,max=255)
	private String projectId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public AccountCredentials() {
		
	}
	
	public AccountCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	  public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	}
