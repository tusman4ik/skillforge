package com.skillforge.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "authoity")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "authoity_users",
            joinColumns = @JoinColumn(name = "authoity_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users = new HashSet<>();

    @Override
    public String getAuthority() {
        return name;
    }
}