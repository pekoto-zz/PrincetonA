package com.pekoto.test.challenges;

import java.util.ArrayList;
import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private ArrayList<Integer> indices;
    
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        indices = new ArrayList<Integer>();
    }
    
    public void insertString(String s, int index) {
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
            child.insertString(remainder,  index + 1);
        } else {
            children.put('\0', null);
        }
    }
    
    public ArrayList<Integer> search(String s) {
        if (s == null || s.length() == 0) {
            return indices;
        } else {
            char first = s.charAt(0);
            if(children.containsKey(first)) {
                String remainder = s.substring(1);
                return children.get(first).search(remainder);
            }
        }
        
        return null;
    }
    
    public boolean terminates() {
        return children.containsKey('\0');
    }
    
    public TrieNode getChild(char chr) {
        return children.get(chr);
    }
}
