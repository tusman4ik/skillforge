package com.skillforge.utils.huffman;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    HuffmanNode left, right;

    public HuffmanNode(char data) {
        this.data = data;
        left = right = null;
    }

    public int compareTo(HuffmanNode node) {
        return 0; // Не используется при генерации случайных частот
    }
}