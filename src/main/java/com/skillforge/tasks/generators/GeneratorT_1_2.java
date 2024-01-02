package com.skillforge.tasks.generators;

import com.skillforge.tasks.ResourceStorage;

import com.skillforge.tasks.Creator;

import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Random;

@Component
@Slf4j
public class GeneratorT_1_2 extends Creator {

    private String params = "param5, param6, param7";

    @Autowired
    public GeneratorT_1_2(ResourceStorage resourceStorage) {
        super(resourceStorage);
    }



    private String generateSolve(HashMap<String, Pair<String, String>> hashMap, String answer) {
        Morpher s;
    }

    private String generateAnswer(HashMap<String, Pair<String, String>> hashMap) {
        return hashMap.get("pa \s*")[new Random().nextInt(hashMap.get("param7").getValue0().split("-")[0].split(",\\s*").length -1 )];
    }




    @Override
    protected String getKey() {
        return "param5, param6, param7";
    }

    @Override
    protected String createSolveAndAnswer(HashMap<String, String> hashMap) {
        String numberOfBits=hashMap.get("param5") ;
        String sentence = hashMap.get("param7") ;
        String name =  hashMap.get("param6") ;
        String firstForm = hashMap.get("param7").split(", ")[0];
        String secondForm = hashMap.get("param7").getValue1().split(", ")[1];
        String s = "В некоторой кодировке каждый символ кодируется " + numberOfBits + " битами. " + name + " написал текст (в нем нет лишних пробелов):\n" +
                "\n" +
                "«" + sentence + "».\n" +
                "\n" +
                "Ученик вычеркнул из списка название " + firstForm + ". Заодно он вычеркнул ставшие лишними запятые и пробелы — два пробела не должны идти подряд.\n" +
                "\n" +
                "При этом размер нового предложения в данной кодировке оказался на " + (answer.length()+2) * Integer.parseInt(numberOfBits) / 8 + " байтов меньше, чем размер исходного предложения. Напишите в ответе вычеркнутое название " + secondForm + ".";
        return s;
        return null;
    }

    @Override
    protected String createAnalyticsList(HashMap<String, String> hashMap) {
        return null;
    }
}
