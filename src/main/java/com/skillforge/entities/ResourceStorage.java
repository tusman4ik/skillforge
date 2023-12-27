package com.skillforge.entities;

import com.skillforge.enums.HandlerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.javatuples.Pair;


import java.util.*;



@Slf4j
public class ResourceStorage {
    private static final Random random;
    private static HashMap<String, Pair<HandlerType, HashSet<String>>> storage;
    static {
        random = new Random();
        storage = new HashMap<>();
        storage.put("param1", new Pair<>(HandlerType.INT, new HashSet<>(Set.of("5, 10, 1"))));
        storage.put("param2", new Pair<>(HandlerType.INT, new HashSet<>(Set.of("16, 48, 1"))));
        storage.put("param3", new Pair<>(HandlerType.INT, new HashSet<>(Set.of("20, 60, 1"))));
        storage.put("param4", new Pair<>(HandlerType.ONE_VALUE, new HashSet<>(Set.of("битах:1","байтах:8","Кбайтах:8000"))));
        storage.put("param5", new Pair<>(HandlerType.INT, new HashSet<>(Set.of("1, 4, 8"))));
        storage.put("param6", new Pair<>(HandlerType.NO_VALUE, new HashSet<>(Set.of("Александр", "Михаил", "Иван", "Дмитрий", "Сергей", "Андрей", "Артем", "Николай", "Павел", "Егор", "Владимир", "Григорий"))));
        storage.put("param7", new Pair<>(HandlerType.ONE_VALUE, new HashSet<>(Set.of("Обь, Лена, Волга, Москва, Макензи, Амазонка - реки:одной из рек, реки", "Чад, Куба, Катар, Швеция, Эстония, Танзания, Сальвадор - страны: одной из страны, страны","Аки, Бали, Банда, Сибуян, Камотес, Лабрадор, Линкольна - моря:одного из морей, моря"))));
        storage.put("param8", new Pair<>(HandlerType.NO_VALUE, new HashSet<>(Set.of("НЕБО", "СОЛНЦЕ"))));
        storage.put("param9", new Pair<>(HandlerType.NO_VALUE, new HashSet<>(Set.of("1:0","+:-","*:^"))));

    }
    private ResourceStorage(){

    }
    public static Pair<String, String> get(String key){
        var pair = storage.get(key);
        String string = randomValue((HashSet<String>) pair.getValue(1));
        Pair<String, String> answer = null;
        switch ((HandlerType) pair.getValue(0)){
            case NO_VALUE -> answer = parseNoValue(string);
            case ONE_VALUE -> answer = parseOneValue(string);
            case MANY_VALUE -> answer = parseManyValue(string);
            case INT -> answer = parseIntValue(string);
            default -> throw new IllegalStateException("Unexpected value: " + (HandlerType) pair.getValue(0));
        }
        log.debug(answer.toString());
        return Optional.of(answer).orElseThrow(()->new IllegalArgumentException("key = "+key));
    }

    private static Pair<String, String> parseIntValue(String string) {
        int [] arr = Arrays.stream(string.split(",\\s*"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int answ = random.nextInt(arr[0],arr[1]) * arr[2];
        return Optional.of(new Pair<>(String.valueOf(answ),String.valueOf(answ))).orElseThrow(()-> new IllegalArgumentException(string));
    }

    private static Pair<String, String> parseManyValue(String string) {
        String[] arr = string.split(":");
        String key = arr[1].split(",")[random.nextInt(arr[1].split(",").length)];
        return Optional.of( new Pair<>(arr[0], key.replace(" ",""))).orElseThrow(()-> new IllegalArgumentException(string));
    }

    private static String randomValue(HashSet<String> set){

        return Optional.of(List.copyOf(set).get(random.nextInt(set.size()))).orElseThrow(()-> new IllegalArgumentException(set.toString()));

    }
    private static Pair<String, String> parseNoValue(String string){
        return Optional.of(new Pair<>(string, "")).orElseThrow(()-> new IllegalArgumentException("arg = "+string));
    }
    private static Pair<String, String> parseOneValue(String string){
        String [] arr = string.split(":");
        return Optional.of(new Pair<>(arr[0], arr[1])).orElseThrow(()->new IllegalArgumentException("arg = "+ string));
    }

}
