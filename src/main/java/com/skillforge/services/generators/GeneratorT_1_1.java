package com.skillforge.services.generators;

import com.skillforge.entities.Task;
import com.skillforge.enums.TaskType;
import com.skillforge.utils.Generator;
import lombok.experimental.Accessors;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

@Component
public final class GeneratorT_1_1 implements Generator {

    private String params = "param1, param2, param3, param4, param5";

    public Task generate(){
        Task task = new Task();
        var hashMap = getListValue(params);
        task.setSolve(this.generateSolve(hashMap));
        task.setAnswer(this.generateAnswer(hashMap));
        task.setAnalyticsList(this.generateAnalyticsList(hashMap));

        return task;
    }
    private String generateSolve(HashMap<String, Pair<String, String>> hashMap){
        return "Рассказ, набранный на компьютере, содержит "+hashMap.get("param1").getValue(0)+
                " страниц, на каждой странице "
                + hashMap.get("param2").getValue0()+ " строк, в каждой строке "+hashMap.get("param3").getValue0()+" символов. Определите информационный объем рассказа в " +
                hashMap.get("param4").getValue(0)+" в кодировке Windows, в которой каждый символ кодируется "+hashMap.get("param5").getValue0()+" бит.";

    }
    private String generateAnswer(HashMap<String, Pair<String, String>> hashMap){
        double numberOfPages =Integer.parseInt((String) hashMap.get("param1").getValue(1));
        double numberOfLines=Integer.parseInt((String) hashMap.get("param2").getValue(1));
        double numberOfCharacters=Integer.parseInt((String) hashMap.get("param3").getValue(1));
        double symbolWeight=Integer.parseInt((String) hashMap.get("param5").getValue(1));
        double unit=Integer.parseInt((String) hashMap.get("param4").getValue(1));
        return " "+ numberOfCharacters * numberOfLines * numberOfPages * symbolWeight / unit +" ";
    }
    private String generateAnalyticsList(HashMap<String, Pair<String, String>> hashMap){
        double answ = Double.parseDouble(generateAnswer(hashMap).replace(" ",""));
        StringBuilder builder = new StringBuilder(answ+":Верный ответ");
        return builder.toString();
    }

}
