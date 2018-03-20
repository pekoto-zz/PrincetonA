package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.datastructures.DirectedEdge;
import com.pekoto.datastructures.EdgeWeightedDirectedGraph;

/**
 * Unit tests for the EdgeWeightedDirectedGraph class
 */
public class EdgeWeightedDirectedGraphTests {
    
    @Test
    public void testAdjacencyLists() {
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(4);
        
        graph.addEdge(new DirectedEdge(0, 1, 5.0));
        graph.addEdge(new DirectedEdge(0, 2, 7.0));
        
        ArrayList<DirectedEdge> edgesFromZero = new ArrayList<DirectedEdge>();
        
        for(DirectedEdge edge: graph.adjacentVertices(0)) {
            edgesFromZero.add(edge);
        }

        assertEquals(0, edgesFromZero.get(0).from());
        assertEquals(2, edgesFromZero.get(0).to());
        assertEquals(7.0, edgesFromZero.get(0).weight(), 0.1);

        assertEquals(0, edgesFromZero.get(1).from());
        assertEquals(1, edgesFromZero.get(1).to());
        assertEquals(5.0, edgesFromZero.get(1).weight(), 0.1);

        ArrayList<DirectedEdge> edgesFromOne = new ArrayList<DirectedEdge>();
        
        for(DirectedEdge edge: graph.adjacentVertices(1)) {
            edgesFromOne.add(edge);
        }

        assertEquals(0, edgesFromOne.size());

        ArrayList<DirectedEdge> edgesFromTwo = new ArrayList<DirectedEdge>();
        
        for(DirectedEdge edge: graph.adjacentVertices(2)) {
            edgesFromTwo.add(edge);
        }

        assertEquals(0, edgesFromTwo.size());
    }

    @Test
    public void testNumOfVertices() {
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(3);
        
        assertEquals(3, graph.numOfVertices());
    }
    
    @Test
    public void testNumOfEdges() {
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(4);
        
        graph.addEdge(new DirectedEdge(0, 1, 5.0));
        graph.addEdge(new DirectedEdge(0, 2, 7.0));
        
        
        assertEquals(2, graph.numOfEdges());
    }
}
