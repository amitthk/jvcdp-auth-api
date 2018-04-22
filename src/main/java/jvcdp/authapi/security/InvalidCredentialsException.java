package jvcdp.authapi.security;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String msg) {
		super(msg);
	}

}
