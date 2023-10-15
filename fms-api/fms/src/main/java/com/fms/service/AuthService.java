package com.fms.service;

import com.fms.config.TokenGenerator;
import com.fms.domain.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

import static com.fms.error.message.ErrorMessages.USER_DOES_NOT_EXIST;
import static com.fms.error.message.ErrorMessages.USER_WITH_THIS_EMAIL_ALREADY_EXISTS;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final TokenGenerator tokenGenerator;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public String login(User user){
        User foundUser=userService.findUserByEmail(user.getEmail());
        if (bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return tokenGenerator.generateToken(foundUser.getEmail());
        }
        else throw new EntityNotFoundException(USER_DOES_NOT_EXIST);

    }
    public User register(User user){
        Optional<User> foundUser=userService.findUserOrEmptyByEmail(user.getEmail());
        if (foundUser.isPresent()){
            throw new EntityExistsException(USER_WITH_THIS_EMAIL_ALREADY_EXISTS);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user= userService.saveUser(user);
        createNewDirectoryForUser(user.getId().toString());
        return user;
    }
    void createNewDirectoryForUser(String userId){
        String directoryPath = System.getProperty("user.dir") + File.separator + "assets" + File.separator + userId;
        File userDirectory=new File(directoryPath);
        if (!userDirectory.exists()){
            userDirectory.mkdirs();
        }
    }
}
