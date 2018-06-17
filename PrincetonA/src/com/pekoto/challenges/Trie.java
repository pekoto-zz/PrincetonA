package com.pekoto.challenges;


public class Trie {
    private TrieNode root = new TrieNode();
    
    public void insertString(String s) {
        root.insertString(s);
    }
    
    public TrieNode getRoot() {
        return root;
    }
}
