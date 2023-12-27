package com.skillforge.utils;

import com.skillforge.entities.ResourceStorage;
import com.skillforge.entities.Task;
import com.skillforge.enums.TaskType;
import lombok.experimental.Accessors;
import org.javatuples.Pair;

import java.util.HashMap;

@FunctionalInterface
public interface Generator {
     Task generate();
     default HashMap<String, Pair<String, String>> getListValue(String params){
        String[] paramsList = params.split(",\\s*");
        HashMap<String, Pair<String, String>> ansList = new HashMap<>();
        for (String s: paramsList){
            ansList.put(s, ResourceStorage.get(s));
        }
        return ansList;
    }
     private TaskType getTaskType(){
        return null;
    }
}
