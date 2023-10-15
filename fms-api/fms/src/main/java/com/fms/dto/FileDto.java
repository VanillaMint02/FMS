package com.fms.dto;

import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FileDto {
    private UUID id;
    private String name;
    private String path;
    private String type;
    private UUID userId;
}
