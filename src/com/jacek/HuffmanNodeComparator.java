package com.jacek;

import java.util.Comparator;

public class HuffmanNodeComparator implements Comparator<HuffmanTreeNode> {

    @Override
    public int compare(HuffmanTreeNode arg0, HuffmanTreeNode arg1) {
        return (arg0.frequency - arg1.frequency);
    }
}