package com.pekoto.datastructures;

/**
 * A representation of a directed, weighted graph
 * implemented using adjacency lists.
 * 
 * This is very similar to the EdgeWeightedGraph class,
 * but the adjacency lists contain directed weighted edges (see DirectedEdge.java) instead of just Edges.
 * 
 * The main class holds an array of bags (linked list).
 * Every element in this array is a vertex in the graph,
 * and the linked list at each element is a list of adjacent edges.
 * 
 * Performance:
 * Insert an edge: O(1) -- just add a new element to the linked list
 * Check if there is an edge between two vertices: degree(V) -- number of connected vertices at V (size of linked list)
 * Get all vertices adjacent to a given vertex: degree(V) -- number of connected vertices at V (size of linked list)
 * 
 * Space: 
 * O(V+E) -- number of edges and vertices
 */
public class EdgeWeightedDirectedGraph {

    private final int numOfVertices;
    private int numOfEdges;

    // An array of bags (linked list)
    // Each bag holds the adjacent vertices
    // Vertices are stored as Integers -- they can be mapped to data using hash maps
    private Bag<DirectedEdge>[] vertices;
    
    /**
     * Initialise an array of bags, with one bag for each vertex
     *  
     * @param numOfVertices The number of vertices in this graph
     */
    public EdgeWeightedDirectedGraph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = (Bag<DirectedEdge>[]) new Bag[numOfVertices];
        
        for(int vertex = 0; vertex < numOfVertices; vertex++) {
            vertices[vertex] = new Bag<DirectedEdge>();
        }
    }
    
    /**
     * Connect two vertices by adding an edge between them
     * (Add an edge to the from vertice's adjacency list)
     * 
     * @param edge The edge to add
     */
    public void addEdge(DirectedEdge edge) {

        int v = edge.from();
        
        vertices[v].add(edge);
        
        numOfEdges++;
    }
    
    /**
     * Returns all of the vertices incident to the vertex v
     * (The adjacency list for this vertex)
     * 
     * @param v The vertex index to query
     * @return The bag of vertex indices that are adjacent to the vertex v
     */
    public Iterable<DirectedEdge> adjacentVertices(int v) {
        return vertices[v];
    }
    
    public int numOfVertices() {
        return numOfVertices;
    }
    
    public int numOfEdges() {
        return numOfEdges;
    }
}
