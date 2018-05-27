package com.pekoto.challenges;

import java.util.Arrays;

public class ModerateChallenges {

    /*
     * Swaps two numbers in place using XORs.
     *
     * Example:
     * a = 4; // 0100
     * b = 2; // 0010
     *
     * a = a^b; // 0110
     * b = a^b; // 0100 (4)
     * a = a^b; // 0010 (2)
     */
    public static SimpleTuple swapInPlace(int a, int b) {
        a = a^b;
        b = a^b;
        a = a^b;
        
        return new SimpleTuple(a, b);
    }
    
    /* 
     * Find the point of intersections between two lines,
     * one and two.
     * 
     * Point: Two infinite lines with different slopes will
     *        intercept. So calculate their point of intersection
     *        assuming they are infinite, and then check if
     *        that intersection point lies within the range of lines
     *        one and two.
     */
    public static LinePoint getIntersection(LinePoint lineOneStart, LinePoint lineOneEnd, LinePoint lineTwoStart, LinePoint lineTwoEnd) {
        // To simplify logic, ensure start point comes before
        // end point for both lines, and that line one comes before
        // line two.
        if(lineOneStart.x > lineOneEnd.x) {
            swap(lineOneStart, lineOneEnd);
        }
        
        if(lineTwoStart.x > lineTwoEnd.x) {
            swap(lineTwoStart, lineTwoEnd);
        }
        
        if(lineOneStart.x > lineTwoStart.x) {
            swap(lineOneStart, lineTwoStart);
            swap(lineOneEnd, lineTwoEnd);
        }
        
        // Compute lines
        Line lineOne = new Line(lineOneStart, lineOneEnd);
        Line lineTwo = new Line(lineTwoStart, lineTwoEnd);
        
        // If the lines are parallel, they intercept only if they have the same y
        // intercept and start of line two is in line one.
        // Lines are parallel if they share the same slope.
        if(lineOne.slope == lineTwo.slope) {    // Lines are parallel
            if(lineOne.yIntercept == lineTwo.yIntercept &&  // Lines have same y intercept
                    isBetween(lineOneStart, lineTwoStart, lineOneEnd)) {    // Line two starts between line one's start and end
                return lineTwoStart;
            }
            
            // No intercept
            return null;
        }
        
        // Get intersection coordinate
        double x = (lineTwo.yIntercept - lineOne.yIntercept) / (lineOne.slope - lineTwo.slope);
        double y = x * lineOne.slope + lineOne.yIntercept;
        LinePoint intersection = new LinePoint(x, y);
        
        // Check if intersection within line segment range
        if(isBetween(lineOneStart, intersection, lineOneEnd) &&
                isBetween(lineTwoStart, intersection, lineTwoEnd)) {
            return intersection;
        }
        
        return null;
    }
    
    private static void swap(LinePoint one, LinePoint two) {
        double x = one.x;
        double y = one.y;
        
        one.setLocation(two.x, two.y);
        two.setLocation(x, y);
    }
    
    /*
     * Check if the middle point is between the start and end points
     */
    private static boolean isBetween(LinePoint start, LinePoint middle, LinePoint end) {
        return isBetween(start.x, middle.x, end.x) &&
               isBetween(start.y, middle.y, end.y);
    }
    
    private static boolean isBetween(double start, double middle, double end) {
        if(start > end) {
            return end <= middle && middle <= start;
        } else {
            return start <= middle && middle <= end;
        }
    }
    
    /*
     * Finds the smallest difference between two
     * elements in two arrays.
     * 
     * Point: If the arrays are sorted, moving the larger
     * element can only increase the difference, so we move
     * the smaller one each time.
     */
    public static int findSmallestDifference(int [] a, int [] b) {
        if(a.length == 0 || b.length == 0) {
            return -1;
        }
        
        Arrays.sort(a);
        Arrays.sort(b);
        
        int minDifference = Integer.MAX_VALUE;
        
        int aPointer = 0;
        int bPointer = 0;
        
        while(aPointer < a.length && bPointer < b.length) {
            if(Math.abs(a[aPointer] - b[bPointer]) < minDifference) {
                minDifference = Math.abs(a[aPointer] - b[bPointer]);
            }
            
            if(a[aPointer] < b[bPointer]) {
                aPointer++;
            } else {
                bPointer++;
            }
        }
        
        return minDifference;
    }
}
