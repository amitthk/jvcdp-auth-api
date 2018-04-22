package com.jvcdp.model;

import org.springframework.security.core.AuthenticationException;

public class EmailExistsException extends AuthenticationException {
	private static final long serialVersionUID = 8091345514446751786L;

	public EmailExistsException(String msg) {
		super(msg);
	}

}
