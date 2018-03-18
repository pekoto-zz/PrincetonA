package com.pekoto.datastructures;

/**
 * A class representing a directed graph (aka, a digraph).
 * Implemented using adjacency lists.
 * 
 * This is very similar to the graph class (GraphAsAdjacencyList),
 * except that when edges are added, they are only added to the vertex in
 * question's linked list.
 * 
 * The main class holds an array of bags (linked list).
 * Every element in this array is a vertex in the graph,
 * and the linked list at each element is a list of adjacent vertices.
 * 
 * Performance:
 * Insert an edge: O(1) -- just add a new element to the linked list
 * Check if there is an edge between two vertices: outdegree(V) -- number of outgoing vertices at V (size of linked list)
 * Get all vertices adjacent to a given vertice: outdegree(V) -- number of outgoing vertices at V (size of linked list)
 * 
 * Space: 
 * O(V+E) -- number of edges and vertices
 */
public class DirectedGraph {
    
    private final int numOfVertices;

    // An array of bags (linked list)
    // Each bag holds the adjacent vertices
    // Vertices are stored as Integers -- they can be mapped to data using hash maps
    private Bag<Integer>[] vertices;
    private int numOfEdges = 0;
    
    /**
     * Initialise an array of bags, with one bag for each vertex
     *  
     * @param numOfVertices The number of vertices in this graph
     */
    public DirectedGraph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = (Bag<Integer>[]) new Bag[numOfVertices];
        
        for(int vertex = 0; vertex < numOfVertices; vertex++) {
            vertices[vertex] = new Bag<Integer>();
        }
    }
    
    /**
     * Connect two vertices by adding an edge between them
     * (Add an edge to the vertex's adjacency list)
     * 
     * Note: This differs from an undirected graph in that
     * the edge is only added in one direction (it's not added from w to v)
     * 
     * @param v The first vertex index
     * @param w The second vertex index
     */
    public void addEdge(int v, int w) {
        vertices[v].add(w);
        numOfEdges++;
    }
    
    /**
     * Returns all of the vertices incident to the vertex v
     * (The adjacency list for this vertex)
     * 
     * @param v The vertex index to query
     * @return The bag of vertex indices that are adjacent to the vertex v
     */
    public Iterable<Integer> adjacentVertices(int v) {
        return vertices[v];
    }
    
    /**
     * Returns the number of vertices incident to the vertex v
     * (The size of the adjacency list for this vertex)
     * 
     * @param v The vertex index to query
     * @return The number of vertices adjacent to the vertex at index v 
     */
    public int degree(int v) {
        int degree = vertices[v].size();
        
        // Should return +1 if the node is connected to itself
        // (I.e., there is a loop from the vertex to itself)
        for(Integer adjacentVertex: vertices[v]) {
            if(adjacentVertex.equals(v)) {
                degree++;
            }
        }
        
        return degree;
    }
    
    public int numOfVertices() {
        return numOfVertices;
    }
    
    public int numOfEdges() {
        return numOfEdges;
    }
}
