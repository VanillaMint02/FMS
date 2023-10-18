package com.fms.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Table(name="users")
public class User extends BaseEntity{
    @NonNull
    @NotEmpty
    private String username;
    @NonNull
    @NotEmpty
    private String password;
    @NonNull
    @NotEmpty
    private String email;

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.PERSIST)
    private List<File> files=new ArrayList<File>();
}
