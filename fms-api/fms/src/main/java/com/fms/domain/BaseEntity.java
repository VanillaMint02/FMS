package com.fms.domain;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {
    @UuidGenerator
    @Id
    private UUID id;
}
