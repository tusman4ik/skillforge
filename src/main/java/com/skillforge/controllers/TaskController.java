package com.skillforge.controllers;

import com.skillforge.entities.Task;
import com.skillforge.enums.Level;
import com.skillforge.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
@Slf4j
public class TaskController {
    private final TaskService taskService;
    @GetMapping
    public Task home(){

        long start = System.currentTimeMillis();
        Task task = taskService.create(2, Level.EASY);
        log.info("HTTP GET /task, completed in "+ ((System.currentTimeMillis() - start))+" ms");
        return task;
    }
}
