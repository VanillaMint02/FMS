package com.fms.mapper;

import com.fms.domain.User;
import com.fms.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class UserMapper {
    private final FileMapper fileMapper;

    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(UUID.fromString(userDto.getId()))
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .files(userDto.getFiles().stream().map(fileMapper::toEntity).toList())
                .build();
    }
    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .files(user.getFiles().stream().map(fileMapper::toDto).toList())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }
}