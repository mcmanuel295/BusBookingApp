package com.example.BusBookingApp.service;

import com.example.BusBookingApp.model.User;
import com.example.BusBookingApp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    public MyUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!!!"));


        return new MyUserDetails(user);
    }
}
