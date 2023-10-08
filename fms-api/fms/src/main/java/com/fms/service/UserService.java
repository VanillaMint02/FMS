package com.fms.service;

import com.fms.domain.User;
import com.fms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User register(User user){
        return userRepository.save(user);
    }
}
