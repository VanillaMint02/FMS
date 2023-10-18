package com.fms.mapper;

import com.fms.domain.File;
import com.fms.domain.User;
import com.fms.dto.FileDto;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileMapper {

    public File toEntity(FileDto fileDto) {
        return File.builder()
                .name(fileDto.getName())
                .path(fileDto.getPath())
                .id(fileDto.getId())
                .type(fileDto.getType())
                .user(User.builder().id(fileDto.getUserId()).build())
                .build();
    }

    public FileDto toDto(File file) {
        return FileDto.builder()
                .id(file.getId())
                .type(file.getType())
                .name(file.getName())
                .path(file.getPath())
                .userId(file.getUser().getId())
                .build();
    }
}
