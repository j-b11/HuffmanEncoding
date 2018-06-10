package com.jacek;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private String toEncode;
    private HuffmanTreeNode root;

    HuffmanTree(String toEncode) {
        this.toEncode = toEncode;
    }

    //create all the leaf nodes and adds them to the min heap
    private PriorityQueue<HuffmanTreeNode> buildHeap() {
        PriorityQueue<HuffmanTreeNode> minHeap = new PriorityQueue<>(toEncode.length(), new HuffmanNodeComparator());

        //getting frequencies
        int[] frequencies = new int[65536];
        for(int i = 0; i < toEncode.length(); i++)
            frequencies[(int)toEncode.charAt(i)]++;

        //creating leaf nodes and inserting them in priority queue
        for(int i = 0; i < 65536; i++) {
            if(frequencies[i] > 0) {
                HuffmanTreeNode leafNode = new HuffmanTreeNode((char)i, frequencies[i]);
                minHeap.add(leafNode);
            }
        }

        return minHeap;
    }

    private void buildHuffmanTree(){
        PriorityQueue<HuffmanTreeNode> minHeap = buildHeap();

        while(minHeap.size() > 1) {
            HuffmanTreeNode firstMin = minHeap.remove();
            HuffmanTreeNode secondMin = minHeap.remove();

            HuffmanTreeNode internalNode = new HuffmanTreeNode(firstMin.frequency + secondMin.frequency, firstMin, secondMin);

            minHeap.add(internalNode);
        }

        //remaining node is the root node of the tree
        root = minHeap.remove();
    }

    HashMap<Character, String> getHuffmanEncoding() {
        buildHuffmanTree();
        HashMap<Character, String> map = new HashMap<>();

        getHuffmanEncoding(root, "", map);

        return map;
    }

    private void getHuffmanEncoding(HuffmanTreeNode root, String encoding, HashMap<Character, String> map) {
        if(root.nodeToLeft == null && root.nodeToRight == null){
            map.put(root.character, encoding);
            return ;
        }

        getHuffmanEncoding(root.nodeToLeft, encoding + "0", map);
        getHuffmanEncoding(root.nodeToRight, encoding + "1", map);
    }

    double computeEntrpy(HashMap<Character, String> map, String inputText){
        double entropyValue = 0;
        for (Map.Entry<Character, String> entry : map.entrySet()) {

            int count = 0;
            for (char j : inputText.toCharArray()) {
                if (j == entry.getKey()) count++;
            }

            double p = (double) count / (double) inputText.length();
            entropyValue += p * log2(1 / p);
        }
        return entropyValue;
    }


    double computeAverageCodewordLength(HashMap<Character, String> map, String inputText){
        double average = 0;

        for (Map.Entry<Character, String> entry : map.entrySet()) {

            int count = 0;
            for (char j : inputText.toCharArray()) {
                if (j == entry.getKey()) count++;
            }

            double p = (double) count / (double) inputText.length();
            average += entry.getValue().length() * p;
        }
        return average;
    }

    private double log2(double v) {
        return Math.log(v) / Math.log(2);
    }

    private int getHeight(HuffmanTreeNode root){
        if(root == null)
            return 0;

        int heightLeft = getHeight(root.nodeToLeft);
        int heightRight = getHeight(root.nodeToRight);

        return 1 + Math.max(heightLeft, heightRight);
    }

    int getHeight() {
        return getHeight(root);
    }

    HuffmanTreeNode getRoot() {
        return root;
    }
}
