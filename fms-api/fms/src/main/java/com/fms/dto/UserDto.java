package com.fms.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private List<FileDto> files;
}
