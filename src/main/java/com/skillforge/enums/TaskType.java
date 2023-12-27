package com.skillforge.enums;

import com.skillforge.SkillforgeApplication;
import com.skillforge.entities.Task;
import com.skillforge.services.generators.GeneratorT_1_1;
import com.skillforge.services.generators.GeneratorT_2_1;
import com.skillforge.utils.Generator;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.*;


@Getter
@ToString
public enum TaskType {


    TASK_1_1(GeneratorT_1_1.class, 1, 1, Level.EASY),
    TASK_1_2(GeneratorT_1_1.class, 2, 1, Level.EASY),
    TASK_2_1(GeneratorT_2_1.class, 2, 2, Level.EASY);

    static {
        random = new Random();
    }
    private static ApplicationContext context;
    private final Class<? extends Generator> generator;
    private final static Random random;
    private final int number;
    private final int typeId;
    private final Level level;

    TaskType(Class<? extends Generator> generator, int typeId, int number, Level level) {
        this.generator = generator;
        this.typeId = typeId;
        this.level = level;
        this.number = number;
    }

    public static HashSet<TaskType> returnInstancesByLevelAndNumber(Level level, int number) {
        TaskType[] taskTypes = TaskType.values();
        HashSet<TaskType> set = new HashSet<>();
        if (!level.equals(Level.EASY) && !level.equals(Level.HARD) && !level.equals(Level.MEDIUM)) {
            throw new IllegalArgumentException("This level of difficulty does not exist : " + level);
        }
        for (TaskType t : taskTypes) {
            if (t.getLevel().equals(level) && t.getNumber() == number) {
                set.add(t);
            }
        }
        if (set.size() != 0) {
            return set;
        }
        throw new IllegalArgumentException("Tasks with this level of difficulty don't exist : " + level);
    }

    public static TaskType returnInstanceById(int typeId) {
        TaskType[] taskTypes = TaskType.values();
        for (TaskType t : taskTypes) {
            if (t.getTypeId() == typeId) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tasks with this id don't exist : " + typeId);
    }

    public static TaskType returnInstanceByLevelAndNumber(Level level, int number) {
        var set = returnInstancesByLevelAndNumber(level, number);
        return List.copyOf(set).get(random.nextInt(set.size()));

    }

    public static Task generate(Level level, int number) {
        return returnInstanceByLevelAndNumber(level, number).generate();

    }

    public Task generate() {
        Task task = context.getBean(this.generator).generate();
        task.setTaskType(this);
        task.setCreatedDate(LocalDateTime.now());
        return task;

    }
    public static void setContext(ApplicationContext context){
        TaskType.context = context;
    }
}
