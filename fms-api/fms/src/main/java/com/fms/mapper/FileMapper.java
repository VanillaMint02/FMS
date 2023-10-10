package com.fms.mapper;

import com.fms.domain.File;
import com.fms.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class FileMapper {
    public File toEntity(FileDto fileDto){
        return File.builder()
                .name(fileDto.getName())
                .path(fileDto.getPath())
                .id(UUID.fromString(fileDto.getId()))
                .type(fileDto.getType())
                .build();
    }
    public FileDto toDto(File file){
        return FileDto.builder()
                .id(file.getId().toString())
                .type(file.getType())
                .name(file.getName())
                .path(file.getPath())
                .build();
    }
}
