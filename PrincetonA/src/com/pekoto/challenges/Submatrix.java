package com.pekoto.challenges;

public class Submatrix {

    private int startRow;
    private int endRow;
    private int startCol;
    private int endCol;
    private int sum;
    
    public Submatrix(int startRow, int endRow, int startCol, int endCol, int sum) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
        this.sum = sum;
    }
    
    public int startRow() {
        return startRow;
        
    }
    
    public int endRow() {
        return endRow;
    }
    
    public int startCol() {
        return startCol;
    }
    
    public int endCol() {
        return endCol;
    }
    
    public int sum() {
        return sum;
    }
}
