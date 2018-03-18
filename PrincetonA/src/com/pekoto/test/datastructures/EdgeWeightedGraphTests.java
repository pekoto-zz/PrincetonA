package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.pekoto.datastructures.EdgeWeightedGraph;


/**
 * Unit tests for the EdgeWeightGraph class
 */
public class EdgeWeightedGraphTests {

    @Test
    public void testAdjacencyLists() {
        
    }
    
    @Test
    public void testNumOfVertices() {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(3);
        
        assertEquals(3, graph.numOfVertices());
    }
    
    @Test
    public void testNumOfEdges() {
        
    }
}
