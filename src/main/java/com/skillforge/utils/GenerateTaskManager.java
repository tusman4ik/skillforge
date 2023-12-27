package com.skillforge.utils;

import com.skillforge.entities.Task;
import com.skillforge.enums.Level;
import com.skillforge.enums.TaskType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Component
public class GenerateTaskManager {
    static {
        random = new Random();
    }
    public static final Random random;

    public Task generate(Level level, int number){
        HashSet<TaskType> set = TaskType.returnInstancesByLevelAndNumber(level, number);
        TaskType taskType = List.copyOf(set).get(random.nextInt(set.size()));
        return taskType.generate();
    }


}
