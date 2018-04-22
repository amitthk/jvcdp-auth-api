package jvcdp.authapi.model;

public class EmailExistsException extends Exception {
	private static final long serialVersionUID = 8091345514446751786L;

	public EmailExistsException(String msg) {
		super(msg);
	}

}
