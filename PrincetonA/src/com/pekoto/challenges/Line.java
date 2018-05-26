package com.pekoto.challenges;

public class Line {
    public double slope;
    public double yIntercept;
    
    public Line(LinePoint start, LinePoint end) {
        double deltaY = end.y - start.y;
        double deltaX = end.x - start.x;
        
        slope = deltaY/deltaX;
        yIntercept = end.y - slope * end.x;
    }
}
