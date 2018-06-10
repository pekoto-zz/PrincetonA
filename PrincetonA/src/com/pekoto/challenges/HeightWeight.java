package com.pekoto.challenges;

public class HeightWeight implements Comparable<HeightWeight> {

    private int height;
    private int weight;
    
    public HeightWeight(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + height + ", " + weight + ")";
    }
    
    @Override
    public int compareTo(HeightWeight other) {
        if(this.height != other.height) {
            return ((Integer)this.height).compareTo((Integer)other.height);
        } else {
            return ((Integer)this.weight).compareTo((Integer)other.weight);
        }
    }
    
    public boolean canComeBefore(HeightWeight other) {
        return height < other.height && weight < other.weight;
    }
}
