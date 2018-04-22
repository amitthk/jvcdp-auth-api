package com.jvcdp.model;

import org.springframework.security.core.AuthenticationException;

public class UserNameNotFoundException extends AuthenticationException {

    public UserNameNotFoundException(String message) {
        super(message);
    }
}
