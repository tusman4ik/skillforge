package com.skillforge.controllers;

import com.skillforge.entities.Authority;
import com.skillforge.entities.User;
import com.skillforge.services.AuthorityService;
import com.skillforge.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SecurityController {
    private final UserService userService;
    private final AuthorityService authorityService;

    @GetMapping("/login")
    public ResponseEntity<Resource> login(){
        Resource resource = new ClassPathResource("static/html/home.html");
        return ResponseEntity.ok().body(resource);
    }
    @GetMapping("/signup")
    public ResponseEntity<Resource> signup(){
        Resource resource = new ClassPathResource("static/html/home.html");
        return ResponseEntity.ok().body(resource);
    }
    @PostMapping("/signup")
    public ResponseEntity<Resource> signup(@RequestParam("User") @Valid User user){
        //TODO It remains possible to create identical users
        userService.saveUser(user);
        Resource resource = new ClassPathResource("static/html/home.html");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("RedirectUrl","/login");
        return new ResponseEntity<>(resource, httpHeaders , HttpStatus.FOUND);
    }
    @GetMapping("/logout")
    public ResponseEntity<Resource> logout(){
        return null;
    }
}
