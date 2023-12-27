package com.skillforge.services;

import com.skillforge.entities.User;
import com.skillforge.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User" +
                " with username: "+username+" does not exist"));
        return user.toUserSecurityDTO();
    }
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    public void save(User user){
        userRepository.save(user);

    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    @Transactional
    public void updateUser(User user){
        User newUser = userRepository.findById(user.getId()).orElseThrow(()->
                new IllegalArgumentException("User "+user+" did not found"));
        userRepository.deleteById(newUser.getId());
        userRepository.save(user);

    }




}
