package com.skillforge.services;

import com.skillforge.entities.Authority;
import com.skillforge.repositories.AuthoityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityService {
    private final AuthoityRepository authoityRepository;

    public Authority findByName(String name){
        return authoityRepository.findByName(name)
                .orElseThrow(()->new IllegalArgumentException("Authority "+name+" does not exist"));
    }

}
