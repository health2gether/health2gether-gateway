package com.health2gether.gateway.provider;

import com.health2gether.gateway.dto.UserResponse;
import com.health2gether.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author flaoliveira
 * @version : $<br/>
 * : $
 * @since 20/10/2019 17:07
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth)
            throws AuthenticationException {
        String email = auth.getName();
        String password = auth.getCredentials()
                .toString();

        final UserResponse userResponse = userService.findUser(email, password);

        if(userResponse != null) {
            return new UsernamePasswordAuthenticationToken
                                (userResponse.getName(), password, Collections.singleton(new SimpleGrantedAuthority("USER")));
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
