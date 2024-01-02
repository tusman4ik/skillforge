package com.skillforge.tasks;

import com.skillforge.entities.Task;
import com.skillforge.enums.TaskType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;
import java.util.HashMap;

@Getter
public abstract class Creator {

    private final ResourceStorage resourceStorage;
    private final TaskType taskType = null;

    @Autowired
    public Creator(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    public final Task create(){

        HashMap<String, String> hashMap = getHashMap();
        return Task.builder()
                .createdDate(LocalDateTime.now())
                .taskType(getTaskType())
                .solveAndAnswer(createSolveAndAnswer(hashMap))
                .analyticsList(createAnalyticsList(hashMap))
                .build();
    }
    public final HashMap<String, String> getHashMap(){
        HashMap<String, String> answer = new HashMap<>();
        String key = getKey();
        String [] keys = key.split(", ");
        for (String e : keys){
            answer.put(e, resourceStorage.get(e));
        }
        return answer;
    }
    protected abstract String getKey();
    protected abstract String createSolveAndAnswer(HashMap<String, String> hashMap);
    protected abstract String createAnalyticsList(HashMap<String, String> hashMap);
}
