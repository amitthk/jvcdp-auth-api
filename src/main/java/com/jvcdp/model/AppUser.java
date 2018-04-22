package com.jvcdp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "APPLICATION_USER")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	private String name;
	private String userName;
	private String email;
	private String passwordHash;
	private String salt;
	private Date lastLoginTimeStamp = new Date();
	private Date createTimeStamp = new Date();
	private Date updateTimeStamp = new Date();
	@Column(name = "API_ID")
	private String apiId;

	public AppUser() { }

	public AppUser(Long id, String name, String userName, String email, String passwordHash, String salt, Date lastLoginTimeStamp, Date createTimeStamp, Date updateTimeStamp, String apiId) {
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.passwordHash = passwordHash;
		this.salt = salt;
		this.lastLoginTimeStamp = lastLoginTimeStamp;
		this.createTimeStamp = createTimeStamp;
		this.updateTimeStamp = updateTimeStamp;
		this.apiId = apiId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getLastLoginTimeStamp() {
		return lastLoginTimeStamp;
	}

	public void setLastLoginTimeStamp(Date lastLoginTimeStamp) {
		this.lastLoginTimeStamp = lastLoginTimeStamp;
	}

	public Date getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(Date createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
}
