package com.pekoto.datastructures;

public class GraphAsAdjacencyList {

    private final int numOfVertices;

    // An array of bags (linked list)
    // Each bag holds the adjacent vertices
    // Vertices are stored as Integers -- they can be mapped to data using hash maps
    private Bag<Integer>[] vertices;
    
    /**
     * Initialise an array of bags, with one bag for each vertex
     *  
     * @param numOfVertices The number of vertices in this graph
     */
    public GraphAsAdjacencyList(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        vertices = (Bag<Integer>[]) new Bag[numOfVertices];
        
        for(int vertex = 0; vertex < numOfVertices; vertex++) {
            vertices[vertex] = new Bag<Integer>();
        }
    }
    
    /**
     * Connect two vertices by adding an edge between them
     * (Add an edge to each of the vertice's adjacency lists)
     * 
     * @param v The first vertex index
     * @param w The second vertex index
     */
    public void addEdge(int v, int w) {
        vertices[w].add(v);
        vertices[v].add(w);
    }
    
    /**
     * Returns all of the vertices incident to the vertex at index v
     * (The adjacency list at this vertex index)
     * 
     * @param v The vertex index to query
     * @return The bag of vertex indices that are adjacent to the vertex as index v
     */
    public Iterable<Integer> adjacentVertices(int v) {
        return vertices[v];
    }
    
    /**
     * Returns the number of vertices adjacent to the vertex at index v
     * (The size of the adjacency list at this vertex index)
     * 
     * @param v The vertex index to query
     * @return The number of vertices adjacent to the vertex at index v 
     */
    public int degree(int v) {
        int degree = vertices[v].size();
        
        // TODO Should return +1 if the node is connected to itself
        // (I.e., there is a look from the vertex to itself)
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
}
