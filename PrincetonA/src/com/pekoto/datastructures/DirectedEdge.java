package com.pekoto.datastructures;

/**
 * Represents an edge in a directed, weighted graph
 */
public class DirectedEdge {

    private int from;
    private int to;
    private double weight;
    
    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    public int from() {
        return from;
    }
    
    public int to() {
        return to;
    }
    
    public double weight() {
        return weight;
    }
}
