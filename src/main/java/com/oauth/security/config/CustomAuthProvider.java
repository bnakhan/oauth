package com.oauth.security.config;

import com.oauth.exception.ApplicationException;
import com.oauth.model.User;
import com.oauth.service.CustomUserDetailService;
import com.oauth.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        try {
            if (!passwordEncoder.matches(password, userDetails.getPassword())){
                throw new BadCredentialsException("Invalid email and password!");
            }
        }catch (Exception e){
            throw new ApplicationException(e.getMessage());
        }
        return new CustomAuthenticationToken(username, password);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.equals(authentication);
    }
}
