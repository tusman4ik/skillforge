package com.skillforge.tasks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;
@Slf4j
@Component
public class ResourceStorage {
    private final Random random;
    private final HashMap<String, HashSet<String>> storage;

    {
        random = new Random();
        storage = new HashMap<>();
        storage.put("param1", new HashSet<>(Set.of("#5, 10, 1")));
        storage.put("param2", new HashSet<>(Set.of("#16, 48, 1")));
        storage.put("param3", new HashSet<>(Set.of("#20, 60, 1")));
        storage.put("param4", new HashSet<>(Set.of("битах:1", "байтах:8", "Кбайтах:8000")));
        storage.put("param5", new HashSet<>(Set.of("#1, 4, 8")));
        storage.put("param6", new HashSet<>(Set.of("Александр", "Михаил", "Иван", "Дмитрий", "Сергей", "Андрей", "Артем", "Николай", "Павел", "Егор", "Владимир", "Григорий")));
        storage.put("param7", new HashSet<>(Set.of("Обь, Лена, Волга, Москва, Макензи, Амазонка - реки:одной из рек, реки", "Чад, Куба, Катар, Швеция, Эстония, Танзания, Сальвадор - страны: одной из страны, страны", "Аки, Бали, Банда, Сибуян, Камотес, Лабрадор, Линкольна - моря:одного из морей, моря")));
        storage.put("param8", new HashSet<>(Set.of("НЕБО", "СОЛНЦЕ")));
        storage.put("param9", new HashSet<>(Set.of("1:0", "+:-", "*:^")));

    }

    public String get(String key) {
        Set<String> set = storage.get(key);
        String res = set.stream()
                .skip((int) (Math.random() * storage.get(key).size()))
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException("Param don't exist. Key : "+ key));
        if(!res.startsWith("#")){
            return res;
        }else {
            String [] arrRes= res.replaceAll("#","").split(", ");
            int from = Integer.parseInt(arrRes[0]);
            int to = Integer.parseInt(arrRes[1]);
            int mult = Integer.parseInt(arrRes[2]);
            return String.valueOf(random.nextInt(from,to)*mult);
        }

    }


}
