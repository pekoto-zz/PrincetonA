package com.pekoto.algorithms;

/**
 * Helper class for use in Dijkstra's Algorithm.
 * Holds a weight and the associated vertex index.
 */
public class WeightIndex implements Comparable<WeightIndex> {

    public double weight; 
    public int index; 

    public WeightIndex(double weight, int index) { 
        this.weight = weight; 
        this.index = index; 
    } 

    public WeightIndex(int index, double weight) { 
        this.weight = weight; 
        this.index = index; 
    } 
    
    public WeightIndex(int index) { 
        this.index = index; 
    } 
    
    public double weight() {
        return weight;
    }

    public int index() {
        return index;
    }

    @Override
    public int compareTo(WeightIndex other) {
        if(this.weight < other.weight) {
            return -1;
        } else if (this.weight > other.weight) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof WeightIndex){
            WeightIndex otherWeightIndex = (WeightIndex) other;
            return this.index == otherWeightIndex.index;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.index;
        return hash;
    }
}
