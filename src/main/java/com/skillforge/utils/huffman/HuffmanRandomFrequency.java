package com.skillforge.utils.huffman;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;



public class HuffmanRandomFrequency {
    public static HashMap<Character, String> generateHuffmanCodes(String text) {
        HashMap<Character, Integer> freqMap = generateRandomFrequencies(text);

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (char c : freqMap.keySet()) {
            priorityQueue.offer(new HuffmanNode(c));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode newNode = new HuffmanNode('\0');
            newNode.left = left;
            newNode.right = right;
            priorityQueue.offer(newNode);
        }

        HuffmanNode root = priorityQueue.peek();
        HashMap<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private static HashMap<Character, Integer> generateRandomFrequencies(String text) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        Random random = new Random();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            freqMap.put(c, random.nextInt(100)); // Генерация случайных частот от 0 до 99
        }

        return freqMap;
    }

    private static void generateCodes(HuffmanNode root, String code, HashMap<Character, String> huffmanCodes) {
        if (root == null) return;

        if (root.data != '\0') {
            huffmanCodes.put(root.data, code);
        }

        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }


}