package com.fms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name="files")
public class File extends BaseEntity{

    @NonNull
    @NotEmpty
    private String name;
    @NonNull
    @NotEmpty
    private String path;
    @NonNull
    @NotEmpty
    private String type;
    @ManyToOne()
    @JsonIgnore
    @NotEmpty
    User user;
}


