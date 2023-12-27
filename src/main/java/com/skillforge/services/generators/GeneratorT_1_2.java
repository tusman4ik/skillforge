package com.skillforge.services.generators;

import com.skillforge.entities.Task;
import com.skillforge.enums.TaskType;
import com.skillforge.utils.Generator;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

@Component
@Slf4j
public class GeneratorT_1_2 implements Generator {

    private String params = "param5, param6, param7";

    @Override
    public Task generate() {
        Task task = new Task();
        var hashMap = getListValue(params);
        String answer = this.generateAnswer(hashMap);
        task.setAnswer(answer);
        task.setSolve(this.generateSolve(hashMap,answer));
        task.setAnalyticsList(this.generateAnalyticsList(hashMap,answer));
        return task;
    }


    private String generateSolve(HashMap<String, Pair<String, String>> hashMap, String answer) {
        String numberOfBits=hashMap.get("param5").getValue0();
        String sentence = hashMap.get("param7").getValue0();
        String name =  hashMap.get("param6").getValue0();
        log.info(hashMap.get("param7").getValue1());
        String firstForm = hashMap.get("param7").getValue1().split(", ")[0];
        String secondForm = hashMap.get("param7").getValue1().split(", ")[1];

        String s = "В некоторой кодировке каждый символ кодируется " + numberOfBits + " битами. " + name + " написал текст (в нем нет лишних пробелов):\n" +
                "\n" +
                "«" + sentence + "».\n" +
                "\n" +
                "Ученик вычеркнул из списка название " + firstForm + ". Заодно он вычеркнул ставшие лишними запятые и пробелы — два пробела не должны идти подряд.\n" +
                "\n" +
                "При этом размер нового предложения в данной кодировке оказался на " + (answer.length()+2) * Integer.parseInt(numberOfBits) / 8 + " байтов меньше, чем размер исходного предложения. Напишите в ответе вычеркнутое название " + secondForm + ".";
        return s;
    }

    private String generateAnswer(HashMap<String, Pair<String, String>> hashMap) {
        return hashMap.get("param7").getValue0().split("-")[0].split(",\\s*")[new Random().nextInt(hashMap.get("param7").getValue0().split("-")[0].split(",\\s*").length -1 )];
    }

    private String generateAnalyticsList(HashMap<String, Pair<String, String>> hashMap, String answer) {
        StringBuilder builder = new StringBuilder(answer+":Верный ответ");
        return builder.toString();
    }


}
