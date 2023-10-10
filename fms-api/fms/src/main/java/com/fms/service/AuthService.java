package com.fms.service;

import com.fms.config.JwtService;
import com.fms.domain.User;
import com.fms.dto.UserDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.fms.error.message.ErrorMessages.USER_DOES_NOT_EXIST;
import static com.fms.error.message.ErrorMessages.USER_WITH_THIS_EMAIL_ALREADY_EXISTS;

@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public String login(User user){
        User foundUser=userService.findUserByEmail(user.getEmail());
        if (bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return jwtService.createJwtToken(foundUser);
        }
        else throw new EntityNotFoundException(USER_DOES_NOT_EXIST);

    }
    public User register(User user){
        User foundUser=userService.findUserByEmail(user.getEmail());
        if (foundUser!=null){
            throw new EntityExistsException(USER_WITH_THIS_EMAIL_ALREADY_EXISTS);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }
}
