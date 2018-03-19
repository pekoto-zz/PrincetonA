package com.pekoto.datastructures;

/**
 * A representation of an undirected graph
 * implemented using adjacency lists.
 * 
 * This is very similar to the GraphAsAdjacencyList class,
 * but the adjacency lists contain weighted edges (see Edge.java) instead of just Integers.
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
public class EdgeWeightedGraph {

    private final int numOfVertices;
    private int numOfEdges;

    // An array of bags (linked list)
    // Each bag holds the adjacent vertices
    // Vertices are stored as Integers -- they can be mapped to data using hash maps
    private Bag<Edge>[] vertices;
    
    /**
     * Initialise an array of bags, with one bag for each vertex
     *  
     * @param numOfVertices The number of vertices in this graph
     */
    public EdgeWeightedGraph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = (Bag<Edge>[]) new Bag[numOfVertices];
        
        for(int vertex = 0; vertex < numOfVertices; vertex++) {
            vertices[vertex] = new Bag<Edge>();
        }
    }
    
    /**
     * Connect two vertices by adding an edge between them
     * (Add an edge to each vertice's adjacency list)
     * 
     * @param v The first vertex index
     * @param w The second vertex index
     */
    public void addEdge(Edge edge) {

        int v = edge.either();
        int w = edge.other(v);
        
        vertices[v].add(edge);

        // No need to add the reverse edge if we're adding a loop
        // (node connected to itself)
        if(v != w) {
            vertices[w].add(edge);
        }
        
        numOfEdges++;
    }
    
    /**
     * Returns all of the vertices incident to the vertex v
     * (The adjacency list for this vertex)
     * 
     * @param v The vertex index to query
     * @return The bag of vertex indices that are adjacent to the vertex v
     */
    public Iterable<Edge> adjacentVertices(int v) {
        return vertices[v];
    }
    
    public int numOfVertices() {
        return numOfVertices;
    }
    
    public int numOfEdges() {
        return numOfEdges;
    }
}
