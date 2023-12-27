package com.skillforge.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/")
public class ResourcesController {
    @GetMapping
    public ResponseEntity<Resource> home(){
        log.error("-----");
        Resource resource = new ClassPathResource("static/html/home.html");
        return ResponseEntity.ok().body(resource);
    }
}
