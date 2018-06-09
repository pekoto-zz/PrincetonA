package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.HashMap;

public class NameGraph {
    private ArrayList<GraphNode> nodes;
    private HashMap<String, GraphNode> map;
    
    public NameGraph() {
        nodes = new ArrayList<GraphNode>();
        map = new HashMap<String, GraphNode>();
    }
    
    public boolean containsNode(String name) {
        return map.containsKey(name);
    }
    
    public GraphNode addNode(String name, int frequency) {
        if(map.containsKey(name)) {
            return getNode(name);
        }
        
        GraphNode node = new GraphNode(name, frequency);
        nodes.add(node);
        map.put(name, node);
        return node;
    }
    
    private GraphNode getNode(String name) {
        return map.get(name);
    }
    
    public ArrayList<GraphNode> getNodes() {
        return nodes;
    }
    
    public void addEdge(String startName, String endName) {
        GraphNode start = getNode(startName);
        GraphNode end = getNode(endName);
        
        if(start != null && end != null) {
            start.addNeighbour(end);
            end.addNeighbour(start);
        }
    }
}
