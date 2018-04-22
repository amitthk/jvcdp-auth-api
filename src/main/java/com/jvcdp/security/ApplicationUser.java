package com.jvcdp.security;

import java.util.Collection;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

public class ApplicationUser extends User {

private static final long serialVersionUID = 1L;

    private final String apiId;

    public ApplicationUser(String userName, String password, boolean enabled,

        boolean accountNonExpired, boolean credentialsNonExpired,

        boolean accountNonLocked,

        Collection<GrantedAuthority> authorities,

        String apiId) {

            super(userName, password, enabled, accountNonExpired,

               credentialsNonExpired, accountNonLocked, authorities);

            this.apiId = apiId;

    }

public String getApiId() {

return apiId;

}

 }