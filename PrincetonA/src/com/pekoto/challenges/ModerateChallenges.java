package com.pekoto.challenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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
     * those indices are sorted, the entire array would be sorted.
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
     * So we...
     * 1. Find the max value of the left+mid sections
     * 2. Find the min value of the mid+right sections
     * 3. Shrink the left subsequence until we hit an element < than min (2)
     * 4. Shrink the right subsequence until we hit an element > than max (1)
     * 
     */
    public static SimpleTuple findUnsortedSequence(int [] arr) {
        int leftEnd = getEndOfLeftSubsequence(arr);
        
        if(leftEnd == arr.length-1) {
            // Array fully sorted
            return null;
        }
        
        int rightStart = getStartOfRightSubsequence(arr);
        
        int maxOfLeftAndMid = leftEnd;  // Right must start at an element > than this
        int minOfMidAndRight = rightStart;  // Left must end at an element < than this
        
        // Iterate middle to get elements less/greater than
        // the min/max elements in the left/right sides 
        for(int i = leftEnd+1; i < rightStart; i++) {
            if(arr[i] < arr[minOfMidAndRight]) {
                minOfMidAndRight = i;
            }
            
            if(arr[i] > arr[maxOfLeftAndMid]) {
                maxOfLeftAndMid = i;
            }
        }
        
        // Shrink the indices according to the conditions above
        int leftIndex = shrinkLeft(arr, minOfMidAndRight, leftEnd);
        int rightIndex = shrinkRight(arr, maxOfLeftAndMid, rightStart);
        
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
    
    /* 
     * Returns the max sum of contiguous elements in an array.
     * 
     * This will return 0 for an array of all negative numbers.
     * My own solution in the notebook will return the minimum negative number.
     * 
     * This solution assumes we have at least 1 positive number in the array.
     * Thus, if the running sum falls below 0, reset it, since the next
     * positive number will be greater than it.
     */
    public static int getMaxSum(int [] arr) {
        int maxSum = 0;
        int runningSum = 0;
        
        for(int i = 0; i < arr.length; i++) {
            runningSum += arr[i];
            
            if(maxSum < runningSum) {
                maxSum = runningSum;
            } else if (runningSum < 0) {
                runningSum = 0;
            }
        }
        
        return maxSum;
    }
    
    /*
     * Returns true if a given string matches a pattern of As and Bs
     * 
     * E.g., aababa = CATCATGOCATGOCAT
     * 
     * 1. Treat the first char in the pattern as the "main" word, the other as the alt
     * 2. Count the number of occurance of main and alt we can have, by counting chars in the pattern
     * 3. Get the first index of alt character
     * 4. Count the max size of the main string (string length/number of main char in pattern)
     * 5. Now, for 0 to max size of main string...
     *  5.1 Take the main string to be 0...mainSize
     *  5.2 If alt fits in the pattern (altCount == 0, or remainingLength % alts == 0)
     *  5.3 Get the index of alt (first index of alt * main size)
     *  5.4 Get the size of alt (remaining length / alt count)
     *  5.5 Get the alt substring (based on alt index + size)
     *  5.6 Build a candidate...
     *      5.6.1 Get the first char in the pattern, set it as main
     *      5.6.2 Go through pattern...
     *      5.6.3 If the char is main, insert main word
     *      5.6.4 Otherwise insert alt word
     *  5.7 If our candidate matches our value, return true -- we have a match!
     * 
     * Performance: O(n^2) (O(n) possibilities for main word, and then O(n) to build the candidate)
     */
    public static boolean doesMatch(String pattern, String value) {
        if(pattern.length() == 0) {
            return value.length() == 0;
        }
        
        char mainChar = pattern.charAt(0);
        char altChar = mainChar == 'a' ? 'b' : 'a';
        int mainCount = countOf(pattern, mainChar);
        int altCount = pattern.length()-mainCount;
        
        int firstAltIndex = pattern.indexOf(altChar);
        
        // The maximum length of the "main" word
        // E.g., if A appears 4 times, and the string is 16 chars, A can't be more than 4 chars long
        int maxSizeOfMain = value.length()/mainCount;   
        
        // Try different sizes for main. We know main must be at the start of the String.
        for(int mainSize = 0; mainSize < maxSizeOfMain; mainSize++) {
            int remainingLength = value.length() - mainSize * mainCount;
            String main = value.substring(0, mainSize);
            
            // remainingLength % altCount == 0, i.e., alt string can fit into this pattern
            // (Likewise for altCount == 0, technically the empty string could fit in)
            if(altCount == 0 || remainingLength % altCount == 0) {
                int altIndex = firstAltIndex * mainSize;
                int altSize = altCount == 0 ? 0 : remainingLength/altCount;
                
                String alt = altCount == 0 ? "" : value.substring(altIndex,  altIndex + altSize);
            
                String candidate = buildFromPattern(pattern, main, alt);
                
                if(candidate.equals(value)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private static int countOf(String pattern, char c) {
        int count = 0;
        
        for(int i = 0; i < pattern.length(); i++) {
            if(pattern.charAt(i) == c) {
                count++;
            }
        }
        
        return count;
    }
    
    private static String buildFromPattern(String pattern, String main, String alt) {
        StringBuffer sb = new StringBuffer();
        
        char first = pattern.charAt(0);
        
        for(char c : pattern.toCharArray()) {
            if(c == first) {
                sb.append(main);
            } else {
                sb.append(alt);
            }
        }
        
        return sb.toString();
    }
    
    /*
     * Return a list of contiguous 0 element block sizes
     * 
     * (Could tidy this up, but basically just a depth first search
     *  through a matrix. Could also do a breath first search instead).
     */
    public static List<Integer> getPondSizes(int [][] matrix) {
        int [][] neighbours = { 
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},   {1, 1}
        };
        
        boolean [][] visited = new boolean[matrix.length][matrix[0].length];
        List<Integer> pondSizes = new ArrayList<Integer>();
        
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                if(!visited[row][col]) {
                    visited[row][col] = true;
                    if(matrix[row][col] == 0) {
                        pondSizes.add(dfs(matrix, row, col, visited, neighbours, 1));
                    }
                }
            }
        }
        
        return pondSizes;
    }
    
    private static int dfs(int[][] matrix, int row, int col, boolean[][]visited, int[][] neighbours, int size) {
        
        for(int[] neighbour: neighbours) {
            if(inRange(matrix, row, col, neighbour) && !visited[row+neighbour[0]][col+neighbour[1]]) {
                visited[row+neighbour[0]][col+neighbour[1]] = true;               
                if(matrix[row+neighbour[0]][col+neighbour[1]] == 0) {
                    size = dfs(matrix, row+neighbour[0], col+neighbour[1], visited, neighbours, size+1);
                }
            }
        }
        
        return size;
    }
    
    private static boolean inRange(int[][] matrix, int row, int col, int[]neighbour) {
        int newRow = row+neighbour[0];
        int newCol = col+neighbour[1];
        
        return newRow >= 0 && newRow < matrix.length
                && newCol >= 0 && newCol < matrix[0].length;
    }
    
    /* 
     * Parses a String representing an arithmetic expression
     * and returns the result.
     * 
     * Basically a modified version of Disjkstra's two-stack
     * method, except it account for operator precedence since
     * there are no parenthesis.
     */
    public static double calculate(String expression) {
        Stack<Double> numberStack = new Stack<Double>();
        Stack<Operator> operatorStack = new Stack<Operator>();
        
        for(int i = 0; i < expression.length(); i++) {
            try {
                // Get number and push
                int value = parseNextNumber(expression, i);
                numberStack.push((double)value);
                
                // Get the operator
                i += Integer.toString(value).length();
                
                if(i >= expression.length()) {
                    break;
                }
                
                // Collapse stack, push operator
                Operator operator = parseNextOperator(expression, i);
                collapseStack(operator, numberStack, operatorStack);
                operatorStack.push(operator);
            } catch(NumberFormatException nfe) {
                return Integer.MIN_VALUE;
            }
        }

        // Do a final collapse
        collapseStack(Operator.BLANK, numberStack, operatorStack);
        if(numberStack.size() == 1 && operatorStack.size() == 0) {
            return numberStack.pop();
        }
        
        return 0;
    }
    
    private enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK
    }
    
    // Return the number that starts at offset
    private static int parseNextNumber(String sequence, int offset) {
        StringBuilder sb = new StringBuilder();
        
        while(offset < sequence.length() && Character.isDigit(sequence.charAt(offset))) {
            sb.append(sequence.charAt(offset));
            offset++;
        }
        
        return Integer.parseInt(sb.toString());
    }
    
    // Return the operator at offset
    private static Operator parseNextOperator(String sequence, int offset) {
        if(offset < sequence.length()) {
            char op = sequence.charAt(offset);
            switch(op) {
            case '+': return Operator.ADD;
            case '-': return Operator.SUBTRACT;
            case '*': return Operator.MULTIPLY;
            case '/': return Operator.DIVIDE;
            }
        }
        
        return Operator.BLANK;
    }
    
    // Pop the top two numbers and apply the operator at the
    // top of the operator stack. Push the result onto the numbers stack.
    // Repeat this until the priority of future top > priority of top
    public static void collapseStack(Operator futureTop, Stack<Double> numberStack, Stack<Operator> operatorStack) {
        while(operatorStack.size() >= 1 && numberStack.size() >= 2) {
            if(getOperatorPriority(futureTop) <= getOperatorPriority(operatorStack.peek())) {
                double secondNum = numberStack.pop();
                double firstNum = numberStack.pop();
                Operator operator = operatorStack.pop();
                
                double result = applyOperator(firstNum, operator, secondNum);
                numberStack.push(result);
            } else {
                break;
            }
        }
    }
    
    // Operator priority =
    // Division == multiplication > addition == subtraction
    private static int getOperatorPriority(Operator operator) {
        if(operator == Operator.ADD || operator == Operator.SUBTRACT) {
            return 1;
        } else if (operator == Operator.MULTIPLY || operator == Operator.DIVIDE) {
            return 2;
        } else {
            return 0;
        }
    }
    
    private static double applyOperator(double left, Operator operator, double right) {
        if(operator == Operator.ADD) {
            return left + right;
        } else if (operator == Operator.SUBTRACT) {
            return left - right;
        } else if (operator == Operator.MULTIPLY) {
            return left * right;
        } else if (operator == Operator.DIVIDE) {
            return left / right;
        } else {
            return right;
        }
    }
}
