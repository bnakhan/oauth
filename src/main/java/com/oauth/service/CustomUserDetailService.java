package com.oauth.service;

import com.oauth.exception.ResourceNotFoundException;
import com.oauth.model.User;
import com.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email and password!"));
        return CustomUserDetails.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return CustomUserDetails.create(user);

    }

}
