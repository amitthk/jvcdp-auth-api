package com.jvcdp.model;

import org.springframework.security.core.AuthenticationException;

public class UserNameAlreadyAllotted extends AuthenticationException {
    public UserNameAlreadyAllotted(String message) {
        super(message);
    }
}
