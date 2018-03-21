package com.pekoto.algorithms;

import java.util.PriorityQueue;
import java.util.Stack;

import com.pekoto.datastructures.DirectedEdge;
import com.pekoto.datastructures.EdgeWeightedDirectedGraph;

/**
 * Dijkstra's Algorithm finds the shortest path to
 * all other vertices from a given source vertex.
 * 
 * 1. Set the distance to the source vertex = 0
 * 2. Set the distance to other vertices to infinity/max
 * 3. Take the vertex w closest to the source (will be the source itself initially) -- (del from priority queue)
 * 4. For every vertex v adjacent to w... -- (while priority queue not empty)
 *  4.1 If the distance to w > distance to v + weight -- (relax process)
 *  4.2 Set w's distance to be distance to v + weight, and path to w to v -- (update/add to priority queue)
 *      (Thus the path and weight for w always reduces, and we get shortest paths)
 * 5. Repeat from step 3, taking the next closest vertex to w
 * 
 * Performance:
 * Depends on min priority queue implementation
 * Typically uses a binary heap, which gives you log n for inserting/getting
 * the min element.
 * 
 * So performance is E log(V) -- log(V) for binary heap put/get/update * E edges
 * 
 * Note: Will not work with edges that have -ve weights
 * 
 * Uses:
 * - Maps applications, etc. etc. (many)
 * - Precompute the paths and then just read from arrays
 */
public class DijkstrasAlgorithm {

    private DirectedEdge edgeTo[];
    private double distanceTo[];
    
    // This uses Java's PriorityQueue for convenience, ordered on weight
    // but with an associated index (the vertex index). Unfortunately
    // this contains linear contains/removes methods. See Princeton's IndexMinPQ
    // for an implementation of a priority queue with log n for all operations.
    private PriorityQueue<WeightIndex> priorityQueue;
    
    public DijkstrasAlgorithm(EdgeWeightedDirectedGraph graph, int sourceVertex) {
        
        edgeTo = new DirectedEdge[graph.numOfVertices()];
        distanceTo = new double[graph.numOfEdges()];
        priorityQueue = new PriorityQueue<WeightIndex>();
        
        // Set the distance to all vertices to infinity
        for(int vertex=0; vertex < graph.numOfEdges(); vertex++) {
            distanceTo[vertex] = Double.POSITIVE_INFINITY;
        }
        
        // Set the distance to the source vertex to 0
        distanceTo[sourceVertex] = 0.0;
        
        priorityQueue.add(new WeightIndex(sourceVertex, 0.0));
        
        while(!priorityQueue.isEmpty()) {
            // Get next vertex closest to the source
            int closestVertex = priorityQueue.poll().index;
            
            for(DirectedEdge adjacentEdge: graph.adjacentVertices(closestVertex)) {
                relax(adjacentEdge);
            }
        }
    }
    
    /**
     * If the distance to the "to" vertex can be reduced by following this edge,
     * update the vertex path/weight to use this edge.
     * 
     * Also put/update the edge on the priority queue.
     * 
     * 1. Get the edges
     * 2. If the distanceTo for this edge > distanceTo vertex + edge...
     *  3. Update the distanceTo and edgeTo
     *  4. If the priority queue already contains this edge, update it (or remove it if update not supported)
     *  5. Add the edge to the priority queue if required
     * 
     * @param edge The edge to relax
     */
    private void relax(DirectedEdge edge) {
        int fromVertex = edge.from();
        int toVertex = edge.to();
        
        if(distanceTo[toVertex] > distanceTo[fromVertex] + edge.weight()) {
            distanceTo[toVertex] = distanceTo[fromVertex] + edge.weight();
            
            edgeTo[toVertex] = edge;
            
            // Since we're using Java's priority queue, we have to remove/add
            // taking O(n) time. See Princeton's IndexMinPQ for log(n) implementation.
            if(priorityQueue.contains(new WeightIndex(toVertex))) {
                // With IndexMinPQ this would be a log(n) update, and an else would insert
                priorityQueue.remove(new WeightIndex(toVertex));
            }
            
            priorityQueue.add(new WeightIndex(toVertex, distanceTo[toVertex]));
        }
    }
    
    /**
     * Returns the shortest path from the source vertex to a given vertex
     * 
     * 1. Set the edge to the be the edge to this vertex
     * 2. Add that edge to a stack
     * 3. Set the edge to be the next edge in the edgeTo array
     * 
     * @param vertex The vertex to find the shortest path to
     * @return The shortest path to the vertex
     */
    public Iterable<Integer> shortestPathTo(int vertex) {
        Stack<Integer> stack = new Stack<Integer>();
        
        for(DirectedEdge edge = edgeTo[vertex]; edge != null; edge = edgeTo[edge.from()]) {
            stack.push(edge.from());
        }
        
        return stack;
    }
}
