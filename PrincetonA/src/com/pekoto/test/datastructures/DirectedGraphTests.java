package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import com.pekoto.datastructures.DirectedGraph;

/**
 * Unit tests for the DirectedGraph class
 */
public class DirectedGraphTests {
    
    @Test
    public void testAdjacencyLists() {
        DirectedGraph graph = new DirectedGraph(5);
        
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        
        ArrayList<Integer> adjacentToVertexZero = new ArrayList<Integer>();

        // Vertex 0 has no connected vertices
        for(Integer adjacentNode: graph.adjacentVertices(0)) {
            adjacentToVertexZero.add(adjacentNode);
        }
        
        assertEquals(0, adjacentToVertexZero.size());
        
        // Vertex 1 is connected to vertex 2
        ArrayList<Integer> adjacentToVertexOne = new ArrayList<Integer>();
        
        for(Integer adjacentNode: graph.adjacentVertices(1)) {
            adjacentToVertexOne.add(adjacentNode);
        }
        
        assertEquals(1, adjacentToVertexOne.size());
        assertEquals(Integer.valueOf(2), adjacentToVertexOne.get(0));
        
        // Vertex 2 is connected to vertices 3 and 4
        ArrayList<Integer> adjacentToVertexTwo = new ArrayList<Integer>();
        
        for(Integer adjacentNode: graph.adjacentVertices(2)) {
            adjacentToVertexTwo.add(adjacentNode);
        }
        
        assertEquals(2, adjacentToVertexTwo.size());
        assertEquals(Integer.valueOf(4), adjacentToVertexTwo.get(0));
        assertEquals(Integer.valueOf(3), adjacentToVertexTwo.get(1));
        
        // Vertex 3 is connected to nothing
        ArrayList<Integer> adjacentToVertexThree = new ArrayList<Integer>();
        
        for(Integer adjacentNode: graph.adjacentVertices(3)) {
            adjacentToVertexThree.add(adjacentNode);
        }
        
        assertEquals(0, adjacentToVertexThree.size());
        
        // Vertex 4 is connected to nothing
        ArrayList<Integer> adjacentToVertexFour = new ArrayList<Integer>();
        
        for(Integer adjacentNode: graph.adjacentVertices(4)) {
            adjacentToVertexFour.add(adjacentNode);
        }
        
        assertEquals(0, adjacentToVertexFour.size());
    }
    
    @Test
    public void testNumOfVertices() {
        DirectedGraph graph = new DirectedGraph(5);
        
        assertEquals(5, graph.numOfVertices());
    }
    
    @Test
    public void testNumOfEdges() {
        DirectedGraph graph = new DirectedGraph(5);
        
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        
        assertEquals(3, graph.numOfEdges());
    }
    
    @Test
    public void testDegree() {
        DirectedGraph graph = new DirectedGraph(5);
        
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        
        assertEquals(0, graph.degree(0));
        assertEquals(1, graph.degree(1));
        assertEquals(2, graph.degree(2));
        assertEquals(0, graph.degree(3));
        assertEquals(0, graph.degree(4));
    }
}
