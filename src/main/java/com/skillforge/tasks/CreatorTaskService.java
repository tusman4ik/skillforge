package com.skillforge.tasks;

import com.skillforge.entities.Task;
import com.skillforge.enums.Level;
import com.skillforge.enums.TaskType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CreatorTaskService {

    private final Map<TaskType, Creator> creatorMap;

    public CreatorTaskService(List<Creator> creators) {
        this.creatorMap = creators.stream().collect(Collectors.toMap(Creator::getTaskType,t->t));
    }
    public Task create(Level level, int number){
        return creatorMap.get(TaskType.getRandomInstanceBuByLevelAndNumber(level, number)).create();
    }
    public Task create(TaskType taskType){
        return creatorMap.get(taskType).create();
    }


}
