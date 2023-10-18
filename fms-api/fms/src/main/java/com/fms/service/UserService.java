package com.fms.service;

import com.fms.config.TokenGenerator;
import com.fms.domain.File;
import com.fms.domain.User;
import com.fms.error.custom.errors.DifferentAccountException;
import com.fms.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.fms.error.message.ErrorMessages.*;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    public User findUserById(UUID userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else throw new EntityNotFoundException(USER_WITH_THIS_ID_DOES_NOT_EXIST);
    }

    public Optional<User> findUserOrEmptyByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else throw new EntityNotFoundException(USER_DOES_NOT_EXIST);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void addFileToUser(File file) {
        if (file.getUser().getFiles() == null) {
            file.getUser().setFiles(new ArrayList<File>());
        }
        file.getUser().getFiles().add(file);
        userRepository.save(file.getUser());
    }

    public String deleteUser(UUID userId, String token) {
        User foundUser = findUserById(userId);
        String email = tokenGenerator.getEmailFromJwtToken(token);
        if (!email.equals(foundUser.getEmail())) {
            throw new DifferentAccountException(YOU_TRIED_TO_DELETE_A_DIFFERENT_ACCOUNT_OF_YOUR_OWN_MALICIOUS_ACTIVITY_SUSPECTED);
        }
        userRepository.delete(foundUser);
        return "Account deleted Successfully";
    }
}
