package com.pekoto.challenges;

import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
    }
    
    // To avoid creating new strings,
    // could also pass in index and get char at index
    public void insertString(String s) {
        if(s == null) {
            return;
        }
        
        if(s.length() > 0) {
            char chr = s.charAt(0);
            
            TrieNode child = null;
            
            if(children.containsKey(chr)) {
                child = children.get(chr);
            } else {
                child = new TrieNode();
                children.put(chr,  child);
            }
            
            String remainder = s.substring(1);
            child.insertString(remainder);
        } else {
            children.put('\0', null);
        }
    }
    
    public boolean terminates() {
        return children.containsKey('\0');
    }
    
    public TrieNode getChild(char chr) {
        return children.get(chr);
    }
}
