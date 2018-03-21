package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.algorithms.DijkstrasAlgorithm;
import com.pekoto.datastructures.DirectedEdge;
import com.pekoto.datastructures.EdgeWeightedDirectedGraph;

/**
 * Unit tests for the DisjkstrasAlgorithm class.
 */
public class DijkstrasAlgorithmTests {

    @Test
    public void testPaths() {
        
        EdgeWeightedDirectedGraph graph = new EdgeWeightedDirectedGraph(6);
        
        graph.addEdge(new DirectedEdge(0, 1, 5.0));
        graph.addEdge(new DirectedEdge(0, 4, 8.0));
        graph.addEdge(new DirectedEdge(0, 3, 9.0));
        graph.addEdge(new DirectedEdge(1, 2, 12.0));
        graph.addEdge(new DirectedEdge(4, 2, 7.0));
        graph.addEdge(new DirectedEdge(3, 5, 4.0));
        graph.addEdge(new DirectedEdge(5, 2, 1.0));
    
        DijkstrasAlgorithm pathFinder = new DijkstrasAlgorithm(graph, 0);
        
        ArrayList<Integer> pathToOne = new ArrayList<Integer>();
        
        for(int vertex: pathFinder.shortestPathTo(1)) {
            pathToOne.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToOne.get(0));
        
        ArrayList<Integer> pathToTwo = new ArrayList<Integer>();
        
        for(int vertex: pathFinder.shortestPathTo(2)) {
            pathToTwo.add(vertex);
        }
        
        assertEquals(Integer.valueOf(5), pathToTwo.get(0));
        assertEquals(Integer.valueOf(3), pathToTwo.get(1));
        assertEquals(Integer.valueOf(0), pathToTwo.get(2));
        
        ArrayList<Integer> pathToThree = new ArrayList<Integer>();
        
        for(int vertex: pathFinder.shortestPathTo(3)) {
            pathToThree.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToThree.get(0));
        
        ArrayList<Integer> pathToFour = new ArrayList<Integer>();
        
        for(int vertex: pathFinder.shortestPathTo(4)) {
            pathToFour.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToFour.get(0));
        
        ArrayList<Integer> pathToFive = new ArrayList<Integer>();
        
        for(int vertex: pathFinder.shortestPathTo(5)) {
            pathToFive.add(vertex);
        }
        
        assertEquals(Integer.valueOf(3), pathToFive.get(0));
        assertEquals(Integer.valueOf(0), pathToFive.get(1));
    }
}
