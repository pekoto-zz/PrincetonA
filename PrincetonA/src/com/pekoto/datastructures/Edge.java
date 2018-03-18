package com.pekoto.datastructures;

/**
 * Represents an edge between two vertices v and w
 * in an edge-weighted graph.
 * 
 * Given an edge, we can get the two vertices from it
 * like this:
 * 
 * int v = edge.either();
 * int w = edge.other(w);
 */
public class Edge implements Comparable<Edge> {

    private int v;
    private int w;
    private double weight;
    
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    // Return a vertex in the edge
    public int either() {
        return v;
    }
    
    // Given a vertex at one end of the edge,
    // return the other
    public int other(int other) {
        if(other == v) {
            return w;
        } else {
            return v;
        }
    }
    
    public int compareTo(Edge that) {
        if(this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
