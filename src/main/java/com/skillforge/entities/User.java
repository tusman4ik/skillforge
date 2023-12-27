package com.skillforge.entities;


import com.skillforge.dto.dto_entities.UserSecurityDTO;
import com.skillforge.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    //статус
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    @Id
    int id;

     @Column(name = "password")
    private String password;


    @Column(name = "username")
    private String username;

    @Email
    @Column(name = "email")
    private String email;


    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Authority> authoities = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public void setAuthority(Authority authority){
        authoities.add(authority);
    }
    public UserSecurityDTO toUserSecurityDTO() {
        UserSecurityDTO userSecurityDTO = new UserSecurityDTO();
        userSecurityDTO.setId((Integer) this.getId());
        userSecurityDTO.setUsername(this.getUsername());
        userSecurityDTO.setPassword(this.getPassword());
        userSecurityDTO.setAuthoities(this.getAuthoities());
        return userSecurityDTO;
    }
}