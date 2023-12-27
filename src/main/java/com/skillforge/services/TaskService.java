package com.skillforge.services;

import com.skillforge.entities.Task;
import com.skillforge.enums.Level;
import com.skillforge.enums.TaskType;
import com.skillforge.repositories.TaskRepository;
import com.skillforge.utils.GenerateTaskManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task create(int typeId) {
        var task = TaskType.returnInstanceById(typeId).generate();
        taskRepository.save(task);
        return task;
    }

    public Task create(int number, Level level) {
        var task = TaskType.generate(level, number);
        taskRepository.save(task);
        return task;
    }

    public Task create(TaskType taskType) {
        var task = taskType.generate();
        taskRepository.save(task);
        return task;
    }

}
