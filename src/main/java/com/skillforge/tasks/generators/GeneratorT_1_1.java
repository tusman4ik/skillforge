package com.skillforge.tasks.generators;

import com.skillforge.tasks.ResourceStorage;

import com.skillforge.enums.TaskType;
import com.skillforge.tasks.Creator;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Getter
public class GeneratorT_1_1 extends Creator {
    private final TaskType taskType = TaskType.TASK_1_1;

    @Autowired
    public GeneratorT_1_1(ResourceStorage resourceStorage) {
        super(resourceStorage);
    }

    @Override
    protected String getKey() {
        return "param1, param2, param3, param4, param5";
    }

    @Override
    protected String createSolveAndAnswer(HashMap<String, String> hashMap) {
        double numberOfPages = Integer.parseInt(hashMap.get("param1"));
        double numberOfLines = Integer.parseInt(hashMap.get("param2"));
        double numberOfCharacters = Integer.parseInt(hashMap.get("param3"));
        double symbolWeight = Integer.parseInt(hashMap.get("param5"));
        double unit = Integer.parseInt(hashMap.get("param4"));

        return String.format("Рассказ, набранный на компьютере, содержит %s страниц, на каждой странице %s строк, в каждой строке " +
                        "%s символов. Определите информационный объем рассказа в %s в кодировке Windows, " +
                        "в которой каждый символ кодируется %s бит. Answer : %s",
                hashMap.get("param1"),
                hashMap.get("param2"),
                hashMap.get("param3"),
                hashMap.get("param4"),
                hashMap.get("param5"),
                numberOfCharacters * numberOfLines * numberOfPages * symbolWeight / unit
        );


    }

    @Override
    protected String createAnalyticsList(HashMap<String, String> hashMap) {
        return null;
    }
}
