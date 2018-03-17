package com.pekoto.algorithms;

import com.pekoto.datastructures.GraphAsAdjacencyList;
import com.pekoto.datastructures.StackWithLinkedList;

/*
 * Given a graph implemented using adjacency lists,
 * this class with find the paths from a given vertex
 * using depth first search.
 * 
 * Depth-first search:
 *  
 *  1. Take a starting vertex
 *  2. Declare 2 arrays that map to your graph vertices:
 *      2.1 A boolean array indicating if an edge has been visited
 *      2.2 An int array, indicating the edge taken to visit this vertex
 *  3. From the starting vertex, recursively:
 *      3.1 Visit a vertex in the adjacency list
 *      3.2 If that vertex has been visited, return
 *      3.3 Otherwise, mark it as visited, updating your arrays from step 2
 *      3.4 Recurse using this vertex's adjacent vertices
 *      
 *  The edge array now shows which vertices have a path to the starting vertex,
 *  and can be used to show the path back to the starting vertex.
 *  
 *  Performance:
 *  Running time: O(V+E) -- num of vertices + edges
 *  Space: O(V) -- arrays for storing whether vertex was visited
 *  
 *  After running...
 *  Finding if a vertex is connected to starting vertex: O(1) (just query value)
 *  Finding a path from a vertex back to starting vertex: O(length of path)
 *      -- Wind back up the edgeTo array until you hit the starting vertex index
 */
public class DepthFirstSearch {

    private final int NOT_CONNECTED = -1;
    
    private boolean [] visited;
    private int [] edgeTo;
    private int startingIndex;
    
    public DepthFirstSearch(GraphAsAdjacencyList graph, int startingIndex) {
        this.startingIndex = startingIndex;
        this.visited = new boolean[graph.numOfVertices()];
        this.edgeTo = new int[graph.numOfVertices()];
        
        // Initialise edges as not connected
        for(int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = NOT_CONNECTED;
        }
        
        depthFirstSearch(graph, startingIndex);
    }
    
    /**
     * Performs a depth first search, marking visited vertices and the edge taken to visit them
     * 
     * 1. Mark this vertex as visited
     * 2. For each adjacent vertex
     *  2.1 If it's been visited, return
     *  2.2 Recurse the search from this vertex
     *  2.3 Mark the edge of this adjacent vertex as being from the previous vertex
     * 
     * @param graph The graph to search
     * @param vertexIndex The current vertex to search from (initially starting index)
     */
    private void depthFirstSearch(GraphAsAdjacencyList graph, int vertexIndex) {
        visited[vertexIndex] = true;
        
        for(int adjacentVertex: graph.adjacentVertices(vertexIndex)) {
            if(visited[adjacentVertex]) {
                return;
            }
            
            depthFirstSearch(graph, adjacentVertex);
            edgeTo[adjacentVertex] = vertexIndex;
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
    public boolean pathExists(int destinationVertex) {
        return edgeTo[destinationVertex] != NOT_CONNECTED;
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
        
        for(int vertex = destinationVertex; edgeTo[vertex] != startingIndex; vertex = edgeTo[vertex]) {
            stack.push(vertex);
        }
        
        return stack;
    }
}
