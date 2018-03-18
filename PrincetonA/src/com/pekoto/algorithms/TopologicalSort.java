package com.pekoto.algorithms;

import java.util.ArrayList;
import java.util.Stack;

import com.pekoto.datastructures.DirectedGraph;

/**
 * Performs a topological sort on a DAG
 * 
 * Concept: Imagine we had a list of tasks that must be done in order
 *          precedence. We can imagine each task as a vertex in the graph,
 *          and each direction expressing the prerequisites. In which order
 *          should we complete these tasks? This is a topological sort.
 *          
 *          Essentially we are redrawing the graph that that each direction
 *          points towards the TOP.
 *          
 *  1. For each node in the graph:
 *  2. If it hasn't been visited yet...
 *  3. Perform a depth first search on that node
 *      3.1 Mark that node as visited
 *      3.2 Mark that node as appearing in the recursion stack
 *      3.3 For each adjacent node...
 *          3.3.1 If it's in the recursion stack -- error, we have a cycle
 *          3.3.2. If it hasn't been visited yet, perform a depth first search on it (recursive)
 *      3.4 All adjacent vertices checked > remove the node from the recursion stack
 *      3.5 Push this vertex onto the stack
 *  4. Finally, we have all of the nodes on the stack in reverse order
 *  5. Pop the nodes off of the stack to return the correct topological ordering
 *          
 *  Performance: O(V+E) -- same as Depth First Search (linear)
 *  Space: O(V) -- same as Depth First Search
 *          
 */
public class TopologicalSort {

    // Keeps track of whether a node has been processed yet
    private boolean [] visited;
    // Keeps track of whether a node has been visited during a DFS iteration
    // (Each connected component has its own DFS)
    // This is for checking for graph cycles
    private boolean [] inRecursionStack;
    private Stack<Integer> reversePostOrder;
    private ArrayList<Integer> topologicalOrder;
    
    /**
     * Gets the topological ordering for a DAG
     * 
     * 1. Declare arrays and stack
     * 2. For each vertex in the graph, if it hasn't been checked,
     *    perform a DFS. Add the vertices to a stack in reverse order.
     * 3. Pop the stack, adding the vertices in correct topological order.
     * 
     * @param graph The graph to topologically sort
     */
    public TopologicalSort(DirectedGraph graph) {
        visited = new boolean[graph.numOfVertices()];
        inRecursionStack = new boolean[graph.numOfVertices()];
        reversePostOrder = new Stack<Integer>();
        topologicalOrder = new ArrayList<Integer>();
        
        // Perform DFS, adding vertices to stack
        for(int vertex = 0; vertex < graph.numOfVertices(); vertex++) {
            if(!visited[vertex]) {
                depthFirstSearch(graph, vertex);
            }
        }
        
        // Pop the stack
        while(!reversePostOrder.empty()) {
            topologicalOrder.add(reversePostOrder.pop());
        }
    }
    
    /**
     * Recursively perform a DFS starting from vertex
     * 
     * 1. Mark the vertex as visited, and put on recursion stack
     * 2. For each adjacent vertex...
     * 3. If it's in the recursion stack, we have a cycle -- throw exception
     * 4. Otherwise, if it hasn't been visited, do a depth first search on it
     * 5. Once we reach a leaf node, add the vertex to the stack, and remove it from the recursion stack
     * 
     * @param graph The graph to search
     * @param vertex The vertex to check
     */
    private void depthFirstSearch(DirectedGraph graph, int vertex) {
        
        visited[vertex] = true;
        inRecursionStack[vertex] = true;
        
        for(int adjacentVertex: graph.adjacentVertices(vertex)) {
            if(inRecursionStack[adjacentVertex]) {
                throw new IllegalArgumentException(String.format("Graph contains a cycle: no topological sort possible: %s -> %s", vertex, adjacentVertex));
            }
            
            if(!visited[adjacentVertex]) {
                depthFirstSearch(graph, adjacentVertex);
            }
        }

        reversePostOrder.push(vertex);      
        inRecursionStack[vertex] = false;
    }
    
    public ArrayList<Integer> getTopologicalOrdering() {
        return topologicalOrder;
    }
}

