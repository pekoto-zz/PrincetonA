package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.datastructures.Edge;
import com.pekoto.datastructures.EdgeWeightedGraph;


/**
 * Unit tests for the EdgeWeightGraph class
 */
public class EdgeWeightedGraphTests {

    @Test
    public void testAdjacencyLists() {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(4);
        
        graph.addEdge(new Edge(0, 1, 5.0));
        graph.addEdge(new Edge(0, 2, 7.0));
        
        ArrayList<Edge> edgesFromZero = new ArrayList<Edge>();
        
        for(Edge edge: graph.adjacentVertices(0)) {
            edgesFromZero.add(edge);
        }

        assertEquals(0, edgesFromZero.get(0).either());
        assertEquals(2, edgesFromZero.get(0).other(edgesFromZero.get(0).either()));
        assertEquals(7.0, edgesFromZero.get(0).weight(), 0.1);

        assertEquals(0, edgesFromZero.get(1).either());
        assertEquals(1, edgesFromZero.get(1).other(edgesFromZero.get(1).either()));
        assertEquals(5.0, edgesFromZero.get(1).weight(), 0.1);

        ArrayList<Edge> edgesFromOne = new ArrayList<Edge>();
        
        for(Edge edge: graph.adjacentVertices(1)) {
            edgesFromOne.add(edge);
        }

        assertEquals(0, edgesFromOne.get(0).either());
        assertEquals(1, edgesFromOne.get(0).other(edgesFromOne.get(0).either()));
        assertEquals(5.0, edgesFromOne.get(0).weight(), 0.1);

        ArrayList<Edge> edgesFromTwo = new ArrayList<Edge>();
        
        for(Edge edge: graph.adjacentVertices(2)) {
            edgesFromTwo.add(edge);
        }

        assertEquals(0, edgesFromTwo.get(0).either());
        assertEquals(2, edgesFromTwo.get(0).other(edgesFromTwo.get(0).either()));
        assertEquals(7.0, edgesFromTwo.get(0).weight(), 0.1);
    }

    @Test
    public void testNumOfVertices() {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(3);
        
        assertEquals(3, graph.numOfVertices());
    }
    
    @Test
    public void testNumOfEdges() {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(4);
        
        graph.addEdge(new Edge(0, 1, 5.0));
        graph.addEdge(new Edge(0, 2, 7.0));
        
        
        assertEquals(2, graph.numOfEdges());
    }
}
