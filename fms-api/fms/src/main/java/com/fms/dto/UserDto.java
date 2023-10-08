package com.fms.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private List<FileDto> files;
}
