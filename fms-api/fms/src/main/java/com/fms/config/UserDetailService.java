package com.fms.config;


import com.fms.domain.User;
import com.fms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;


    @Override
    public UserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userService.findUserByEmail(email);
        if (foundUser == null) throw new UsernameNotFoundException("Email not found");
        return new UserDetail(foundUser);
    }
}