package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphNode {
    
    private ArrayList<GraphNode> neighbours;
    private HashMap<String, GraphNode> map;
    private String name;
    private int frequency;
    private boolean visited = false;
    
    public GraphNode(String name, int frequency) {
        this.name = name;
        this.frequency = frequency;
        this.neighbours = new ArrayList<GraphNode>();
        this.map = new HashMap<String, GraphNode>();
    }
    
    public String getName() {
        return name;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    public boolean addNeighbour(GraphNode node) {
        if(map.containsKey(node.getName())) {
            return false;
        }
        
        neighbours.add(node);
        map.put(node.getName(), node);
        return true;
    }
    
    public ArrayList<GraphNode> getNeighbours() {
        return neighbours;
    }
    
    public boolean visited() {
        return visited;
    }
    
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
