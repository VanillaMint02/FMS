package com.fms.service;

import com.fms.domain.User;
import com.fms.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.fms.error.message.ErrorMessages.USER_DOES_NOT_EXIST;
import static com.fms.error.message.ErrorMessages.USER_WITH_THIS_EMAIL_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(UUID userId){
        Optional<User> foundUser=userRepository.findById(userId);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        else throw new EntityNotFoundException(USER_DOES_NOT_EXIST);
    }
    public User findUserByEmail(String email){
        Optional<User> foundUser=userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }
        else throw new EntityNotFoundException(USER_DOES_NOT_EXIST);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
