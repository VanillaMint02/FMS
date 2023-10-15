package com.fms.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name="users")
public class User extends BaseEntity{
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.PERSIST)
    private List<File> files=new ArrayList<File>();
}
