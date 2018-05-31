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
    
    /*
     * Returns the max of a and b without using comparison operators
     * 
     * Point: If we have a variable k that is 1 when a > b, and 0 otherwise,
     *        we can do a * k and b * ~k to get the max value.
     *        k=0 for the lower number, thus getting rid of it.
     *        
     * (This code has a potential overflow bug when the numbers have different signs.
     *  See CTCI for a more robust solution.)
     */
    public static int getMax(int a, int b) {
        int k = sign(a-b);
        int inverseK = flip(k);
        
        return a*k + b*inverseK;
    }
    
    // returns 1 if a is +ve, or 0 if a is -ve
    // 1. a >> 31: Shift -ve indicator bit into 1 position
    // 2. & it with 0001
    // 3. This will give 1 if -ve bit is set (1),
    //    so FLIP is to give us 1 if we have +ve
    private static int sign(int a) {
        int signBitShiftedToOnePos = a >> 31;
        int isNegative = signBitShiftedToOnePos & 0x1;
        return flip(isNegative);
    }
    
    private static int flip(int bit) {
        return 1^bit;
    }
    
    /*
     * Returns the year the most people were alive using sorting.
     * 
     * This works kind of like a merge sort:
     * 1. Sort the births and deaths
     * 2. If the next lowest year is a birth year,
     *    increment the people alive and check if we have a new record
     * 3. If the next lowest year is a death year,
     *    decrement the number of people alive
     * 
     * Performance: O(p log p), where p is the number of people
     *              (The bottleneck is sorting)
     */
    public static int getMaxAliveUsingSorting(Person [] people, int minBirthYear) {
        int [] births = getSortedYears(people, true);
        int [] deaths = getSortedYears(people, false);
        
        int birthIndex = 0;
        int deathIndex = 0;
        int currentlyAlive = 0;
        int maxAlive = 0;
        int yearWithMostAlive = minBirthYear;
        
        while(birthIndex < births.length) {
            if(births[birthIndex] <= deaths[deathIndex]) {
                currentlyAlive++;
                if(currentlyAlive > maxAlive) {
                    maxAlive = currentlyAlive;
                    yearWithMostAlive = births[birthIndex];
                }
                birthIndex++;
            } else if(deaths[deathIndex] < births[birthIndex]) {
                currentlyAlive--;
                deathIndex++;
            }
        }
        
        return yearWithMostAlive;
    }
    
    private static int[] getSortedYears(Person [] people, boolean getBirthYear) {
        int [] years = new int[people.length];
        
        for(int i=0; i<people.length; i++) {
            years[i] = getBirthYear ? people[i].getBirthYear() : people[i].getDeathYear();
        }
        
        Arrays.sort(years);
        
        return years;
    }
    
    /*
     * Get the year the most people were alive be calculating
     * the population change for each year and cumulatively adding them up.
     * 
     * 1. Set up an array of years
     * 2. Increment elements for birth year
     * 3. Decrement elements for death year + 1
     * 4. Iterate through this delta array, cumulatively adding,
     *    and update max year if we hit a new max value
     * 
     * Performance: O(R + P), where P is the number of people, and R is the range of years.
     * (This may not be better than the sorting approach -- it depends)
     */
    public static int getMaxAliveUsingDeltas(Person [] people, int minBirthYear, int maxDeathYear) {
        
        int [] populationDeltas = calculatePopulationDeltas(people, minBirthYear, maxDeathYear);
        int maxYear = getMaxYear(populationDeltas);
        
        return maxYear + minBirthYear;
    }
    
    private static int[] calculatePopulationDeltas(Person [] people, int minBirthYear, int maxDeathYear) {
        int [] populationDeltas = new int [maxDeathYear-minBirthYear+2];
    
        for(Person p : people) {
            int birthIndex = p.getBirthYear()-minBirthYear;
            populationDeltas[birthIndex]++;
            
            // +1 because people are counted living in
            // the year they die
            int deathIndex = p.getDeathYear()-minBirthYear+1;
            populationDeltas[deathIndex]--;
        }
        
        return populationDeltas;
    }
    
    private static int getMaxYear(int [] populationDeltas) {
        int maxYear = 0;
        int maxAlive = 0;
        int currentlyAlive = 0;
        
        for(int year = 0; year < populationDeltas.length; year++) {
            currentlyAlive += populationDeltas[year];
            
            if(currentlyAlive > maxAlive) {
                maxAlive = currentlyAlive;
                maxYear = year;
            }
        }
        
        return maxYear;
    }
    
    /*
     * Finds the two indices that, if the elements between
     * those indices would be sorted, the entire array would be sorted.
     * 
     * Think about it:
     * The array can be split into:
     * Left (sorted)
     * Middle (out of order)
     * Right (sorted)
     * 
     * For the entire array to be sorted, the max of left needs to be <
     * middle, and the min of the right side > middle.
     * 
     * So we shrink the left and right subsequences until these conditions are met.
     */
    public static SimpleTuple findUnsortedSequence(int [] arr) {
        int leftEnd = getEndOfLeftSubsequence(arr);
        
        if(leftEnd == arr.length-1) {
            // Array fully sorted
            return null;
        }
        
        int rightStart = getStartOfRightSubsequence(arr);
        
        int maxIndex = leftEnd;  // End of the left side (all elements right must be bigger than this)
        int minIndex = rightStart;  // Start of the right side (all elements left must be smaller than this)
        
        // Iterate middle to get elements less/greater than
        // the min/max elements in the left/right sides 
        for(int i = leftEnd+1; i < rightStart; i++) {
            if(arr[i] < arr[minIndex]) {
                minIndex = i;
            }
            
            if(arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        
        int leftIndex = shrinkLeft(arr, minIndex, leftEnd);
        int rightIndex = shrinkRight(arr, maxIndex, rightStart);
        
        return new SimpleTuple(leftIndex, rightIndex);
    }
    
    private static int getEndOfLeftSubsequence(int [] arr) {
        // Iterate over the array until we hit an element that
        // is smaller than the one before it (i.e., go from ascending > descending order)
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < arr[i-1]) {
                return i-1;
            }
        }
        
        return arr.length-1;
    }
    
    private static int getStartOfRightSubsequence(int [] arr) {
        // Iterate down the array until we fin an element that is
        // bigger than the element after it (i.e., go from descending > ascending order)
        for(int i = arr.length-2; i >= 0; i--) {
            if(arr[i] > arr[i+1]) {
                return i+1;
            }
        }
        
        return 0;
    }
    
    private static int shrinkLeft(int[] arr, int minIndex, int leftEnd) {
        int midRightMin = arr[minIndex];
        
        for(int i = leftEnd-1; i >= 0; i--) {
            if(arr[i] <= midRightMin) {
                return i+1;
            }
        }
        
        return 0;
    }
    
    private static int shrinkRight(int[] arr, int maxIndex, int rightStart) {
        int leftMidMax = arr[maxIndex];
        
        for(int i = rightStart; i < arr.length; i++) {
            if(arr[i] >= leftMidMax) {
                return i-1;
            }
        }
        
        return 0;
    }
}
