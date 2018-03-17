package com.pekoto.algorithms;

import com.pekoto.datastructures.GraphAsAdjacencyList;
import com.pekoto.datastructures.QueueWithLinkedList;
import com.pekoto.datastructures.StackWithLinkedList;

/**
 * Given a graph implemented using adjacency lists,
 * this class with find the paths from a given vertex
 * and distance using breadth first search.
 * 
 * 1. Declare two arrays -- edgeTo and distanceTo
 * 2. Put the starting vertex on a queue
 * 3. While the queue is not empty...
 *      3.1 Dequeue the next vertex
 *      3.2 For each adjacent vertex...
 *      3.3 If the vertex hasn't been processed yet,
 *          update edgeTo and distanceTo and put it on the queue
 *      3.4 Repeat for the next element on the queue
 *      
 * Performance:
 * Initial construction algorithm: O(V+E) -- number of vertices + edges
 * Space: O(V) -- edgeTo and distanceTo arrays, + queue
 * 
 * Checking if edge exists: O(1) -- just check to see if edgeTo has value
 * Get path: O(length of path) -- walk backwards through edgeTo
 * Get path length/shortest path: O(1)/O(V) -- quickly get the length of item by querying distanceTo
 * 
 * NOTE: Since the vertices are processed in order, BFS will always return the SHORTEST path
 * 
 * Uses:
 * * Quickly get distance to any other node
 * * Number of leaps in a communications network
 * * Degrees of separation
 * 
 */
public class BreadthFirstSearch {

    private final int NOT_CONNECTED = -1;
    
    private int [] edgeTo;
    private int [] distanceTo;
    private int startingVertex;
    
    public BreadthFirstSearch(GraphAsAdjacencyList graph, int startingVertex) {
        
        this.startingVertex = startingVertex;
        this.edgeTo = new int[graph.numOfVertices()];
        this.distanceTo = new int[graph.numOfVertices()];
        
        for(int i = 0; i < graph.numOfVertices(); i++) {
            edgeTo[i] = NOT_CONNECTED;
        }
        
        breadthFirstSearch(graph, startingVertex);
    }
    
    /**
     * Performs a breadth first search given a graph and starting vertex
     * 
     * Sets the correct values for the edgeTo and distanceTo arrays
     * 
     * 1. Take a vertex and add it to the queue
     * 2. While the queue is not empty...
     * 3. Dequeue the vertex
     * 4. Get the adjacent nodes
     * 5. For each adjacent node, if it's not on the queue...
     * 6. Set the edgeTo and distanceTo values
     * 
     * Performance:
     * O(V+E) -- the number of vertices and edges
     * 
     * @param graph The graph to search
     * @param startingVertex The initial vertex to start the search from
     */
    private void breadthFirstSearch(GraphAsAdjacencyList graph, int startingVertex) {
        QueueWithLinkedList<Integer> queue = new QueueWithLinkedList<Integer>();
        
        queue.enqueue(startingVertex);
        int distance = 0;
        
        while(!queue.isEmpty()) {
            
            // Get the next vertex from the queue
            int vertex = queue.dequeue();            
            distance = distanceTo[vertex] + 1;
            
            // For each adjacent vertex...
            for(int adjacentVertex: graph.adjacentVertices(vertex)) {
                
                // If the edge hasn't been connected yet,
                // connect it and add it to the queue
                if(edgeTo[adjacentVertex] == NOT_CONNECTED) {
                    edgeTo[adjacentVertex] = vertex;
                    distanceTo[adjacentVertex] = distance;
                    queue.enqueue(adjacentVertex);
                }
            }
        }
    }
    
    /**
     * Returns true is a path exists between the starting vertex
     * and given destination vertex
     * 
     * @param destinationVertex The vertex to check if a path exists to
     * @return True if a path exists between the starting vertex and destination vertex,
     *          false otherwise 
     */
    public boolean pathExists(int vertex) {
        return edgeTo[vertex] != NOT_CONNECTED;
    }
    
    /**
     * Returns the path between a starting vertex and a destination vertex
     * 
     * @param destinationVertex The vertex to find the path to
     * @return A stack containing the path from the starting vertex to destination vertex
     */
    public Iterable<Integer> path(int destinationVertex) {
        if(!pathExists(destinationVertex)) {
            return new StackWithLinkedList<Integer>();
        }
        
        StackWithLinkedList<Integer> stack = new StackWithLinkedList<Integer>();
        
        for(int vertex = edgeTo[destinationVertex]; vertex != startingVertex; vertex = edgeTo[vertex]) {
            stack.push(vertex);
        }
        
        stack.push(startingVertex);
        
        return stack;
    }
    
    /**
     * Returns the distance from the destination to the starting vertex,
     * if a path exists, or -1 otherwise.
     * 
     * @param destinationVertex The vertex to get the distance to
     * @return The distance to the starting vertex, or -1 if not connected
     */
    public int distance(int destinationVertex) {
        if(!pathExists(destinationVertex)) {
            return NOT_CONNECTED;
        }
        
        return distanceTo[destinationVertex];
    }
}
