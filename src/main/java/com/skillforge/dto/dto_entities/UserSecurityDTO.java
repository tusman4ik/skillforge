package com.skillforge.dto.dto_entities;

import com.skillforge.entities.Authority;
import com.skillforge.entities.User;
import com.skillforge.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * DTO for {@link User}
 */
@Setter
@Getter
@ToString
public class UserSecurityDTO implements Serializable, UserDetails {

    private UserService userService;
    private int id;
    private String password;
    private String username;
    private Set<Authority> authoities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoities.stream().toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public User toUser() {
        return userService.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Parsing error: no user with this DTO : " +this.toString()));
    }
    @PostConstruct
    void setUserService(UserService userService){
        this.userService = userService;
    }
}