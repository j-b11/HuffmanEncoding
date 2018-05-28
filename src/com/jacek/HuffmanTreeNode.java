package com.jacek;

public class HuffmanTreeNode {
    char character;
    int frequency;

    HuffmanTreeNode nodeToLeft;
    HuffmanTreeNode nodeToRight;

   HuffmanTreeNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    HuffmanTreeNode(int frequency, HuffmanTreeNode nodeToLeft, HuffmanTreeNode nodeToRight) {
        this.character = '~';
        this.frequency = frequency;
        this.nodeToLeft = nodeToLeft;
        this.nodeToRight = nodeToRight;
    }
}
