package com.general.template.service;

import com.general.template.common.exception.UserNotFoundException;
import com.general.template.service.implementation.UserServiceImpl;
import com.general.template.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userService;

    @Autowired
    public CustomUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("================ Username ================");
        System.out.println(username);
        System.out.println("==========================================");
        User user = null;

        try {
            user = this.userService.readByUsername(username);
        } catch (UserNotFoundException e) {
            System.out.println("=================User not found exception==================");
            e.printStackTrace();
        }

        if (user == null) {
            System.out.println("=================User is null====================");
            throw new UsernameNotFoundException("User is not registered");
        }

        return user;
    }
}