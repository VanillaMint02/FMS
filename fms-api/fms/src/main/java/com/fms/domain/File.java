package com.fms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@RequiredArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name="files")
public class File extends BaseEntity{

    private String name;
    private String path;
    private String type;
    @ManyToOne()
    @JsonIgnore
    User user;
}


