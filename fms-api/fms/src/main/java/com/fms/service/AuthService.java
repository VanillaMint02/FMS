package com.fms.service;

import com.fms.config.TokenGenerator;
import com.fms.domain.User;
import com.fms.error.custom.errors.InvalidDataException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fms.error.message.ErrorMessages.*;
import static com.fms.validators.Validators.validEmailAddress;
import static com.fms.validators.Validators.validPassword;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final TokenGenerator tokenGenerator;
    private final UserService userService;
    private final FileService fileService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String login(User user) {
        User foundUser = userService.findUserByEmail(user.getEmail());
        if (bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            return tokenGenerator.generateToken(foundUser.getEmail());
        } else throw new EntityNotFoundException(USER_DOES_NOT_EXIST);

    }

    public User register(User user) {
        Optional<User> foundUser = userService.findUserOrEmptyByEmail(user.getEmail());
        if (!validEmailAddress(user.getEmail())) {
            throw new InvalidDataException(PLEASE_USE_A_VALID_EMAIL_ADDRESS);
        }
        if (foundUser.isPresent()) {
            throw new EntityExistsException(USER_WITH_THIS_EMAIL_ALREADY_EXISTS);
        }
        if (!validPassword(user.getPassword())) {
            throw new InvalidDataException(PASSWORD_MUST_BE_12_CHARACTERS_OR_MORE_CONTAIN_A_NUMBER_AND_A_SPECIAL_CHARACTER);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = userService.saveUser(user);
        fileService.createNewDirectoryForUser(user.getId().toString());
        return user;
    }

}
