package com.skillforge.enums;

import com.skillforge.tasks.generators.GeneratorT_1_1;
import com.skillforge.tasks.generators.GeneratorT_1_2;
import com.skillforge.tasks.generators.GeneratorT_2_1;
import com.skillforge.tasks.Creator;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;


@Getter
@ToString
public enum TaskType {

    TASK_1_1(GeneratorT_1_1.class, 1,Level.EASY),
    TASK_1_2(GeneratorT_1_2.class, 2,Level.EASY),
    TASK_2_1(GeneratorT_2_1.class, 2,Level.EASY);


    private Class<? extends Creator> creator;
    private int number;
    private Level level;



    TaskType(Class<? extends Creator> creator, int number, Level level) {
        this.creator = creator;
        this.number = number;
        this.level = level;
    }
    public static TaskType getRandomInstanceBuByLevelAndNumber(Level level, int number){
        return Arrays.stream(TaskType.values())
                .filter(t -> t.getNumber() == number && t.getLevel() == level)
                .skip((int)(Math.random()*TaskType.values().length))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("TaskType with number = "
                +number+" and level = "+ level.toString()+" not found"));


    }
}
