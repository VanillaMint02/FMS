package com.fms.controller;

import com.fms.dto.UserDto;
import com.fms.mapper.UserMapper;
import com.fms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;
    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody UserDto userDto){
        return new ResponseEntity<>(authService.login(userMapper.toEntity(userDto)), HttpStatus.OK);
    }
}
