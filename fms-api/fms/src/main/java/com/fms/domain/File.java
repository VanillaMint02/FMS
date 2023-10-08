package com.fms.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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
    @ManyToOne
    User user;
}


