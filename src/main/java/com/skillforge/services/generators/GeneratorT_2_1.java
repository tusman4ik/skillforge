package com.skillforge.services.generators;

import com.skillforge.entities.ResourceStorage;
import com.skillforge.entities.Task;
import com.skillforge.enums.TaskType;
import com.skillforge.utils.Generator;
import com.skillforge.utils.huffman.HuffmanRandomFrequency;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GeneratorT_2_1 implements Generator {
    private String params = "param6, param8, param9";

    @Override
    public Task generate() {
        Task task = new Task();
        var hashMap = getListValue(params);
        task.setSolve(generateSolve(hashMap));
        task.setAnswer(generateAnswer(hashMap));
        task.setAnalyticsList(generateAnalyticsList(hashMap));
        return task;
    }

    private String generateAnswer(HashMap<String, Pair<String, String>> hashMap) {
        return hashMap.get("param8").getValue0();
    }

    private String generateAnalyticsList(HashMap<String, Pair<String, String>> hashMap) {
        return null;
    }

    private String generateSolve(HashMap<String, Pair<String, String>> hashMap) {
        String fName = hashMap.get("param6").getValue0();
        String sName = fName;
        while (sName.equals(fName)) {
            sName = ResourceStorage.get("param6").getValue0();
        }
        HashMap<Character, String> codeTable = HuffmanRandomFrequency.generateHuffmanCodes(hashMap.get("param8").getValue0());
        String symbols = hashMap.get("param9").getValue0();
        return fName + " и Петя играли в шпионов и кодировали сообщения собственным шифром. Фрагмент кодовой таблицы приведен ниже:\n" +

                codeTable.entrySet().stream().map(
                        entry -> entry.getKey() + "-" + entry.getValue() + ", "
                ).reduce("", (acc, str) -> acc + str) +
                "\n" +
                "Расшифруйте сообщение, если известно, что буквы в нем не повторяются: \n" +
                generateMessage(hashMap, codeTable)
                + "\n" +
                "Запишите в ответе расшифрованное сообщение.";
    }

    private String generateMessage(HashMap<String, Pair<String, String>> hashMap, HashMap<Character, String> codeTable) {
        StringBuilder result = new StringBuilder();
        char substitute1 = hashMap.get("param9").getValue0().split(":")[0].charAt(0);
        char substitute2 = hashMap.get("param9").getValue0().split(":")[1].charAt(0);
        String keyWord = hashMap.get("param8").getValue0();
        StringBuilder keyWordStringBuiler = new StringBuilder();
        for (char c : keyWord.toCharArray()) {
            keyWordStringBuiler.append(codeTable.get(c));
        }
        keyWord = keyWordStringBuiler.toString();
        for (char c : keyWord.toCharArray()) {
            if (c == '1') {
                result.append(substitute1);
            } else {
                result.append(substitute2);
            }
        }
        return result.toString();
    }
}
