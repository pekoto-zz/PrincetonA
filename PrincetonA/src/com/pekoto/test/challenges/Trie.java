package com.pekoto.test.challenges;

import java.util.ArrayList;

public class Trie {
    private TrieNode root = new TrieNode();
    
    public ArrayList<Integer> search(String s) {
        return root.search(s);
    }
    
    public void insertString(String s, int index) {
        root.insertString(s, index);
    }
    
    public TrieNode getRoot() {
        return root;
    }
}
