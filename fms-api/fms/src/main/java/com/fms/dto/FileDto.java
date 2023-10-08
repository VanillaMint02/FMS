package com.fms.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FileDto {
    private String id;
    private String name;
    private String path;
    private String type;
}
