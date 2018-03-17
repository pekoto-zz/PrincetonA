package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.algorithms.BreadthFirstSearch;
import com.pekoto.datastructures.GraphAsAdjacencyList;

/**
 * Unit tests for the BreadthFirstSearch class
 */
public class BreadthFirstSearchTests {

    @Test
    public void testPathExists() {
        GraphAsAdjacencyList graph = new GraphAsAdjacencyList(13);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(6, 4);
        graph.addEdge(4, 3);
        graph.addEdge(5, 3);
        graph.addEdge(7, 8);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);
        graph.addEdge(9, 11);
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);
        
        assertTrue(bfs.pathExists(1));
        assertTrue(bfs.pathExists(2));
        assertTrue(bfs.pathExists(3));
        assertTrue(bfs.pathExists(4));
        assertTrue(bfs.pathExists(5));
        assertTrue(bfs.pathExists(6));
        assertFalse(bfs.pathExists(7));
        assertFalse(bfs.pathExists(8));
        assertFalse(bfs.pathExists(9));
        assertFalse(bfs.pathExists(10));
        assertFalse(bfs.pathExists(11));
        assertFalse(bfs.pathExists(12));
    }
    
    @Test
    public void testPaths() {
        GraphAsAdjacencyList graph = new GraphAsAdjacencyList(13);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(6, 4);
        graph.addEdge(4, 3);
        graph.addEdge(5, 3);
        graph.addEdge(7, 8);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);
        graph.addEdge(9, 11);
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);
        
        // 1 connects directly to 0
        ArrayList<Integer> pathToOne = new ArrayList<Integer>();
        
        for(Integer vertex : bfs.path(1)) {
            pathToOne.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToOne.get(0));
        
        // 2 connects directly to 0
        ArrayList<Integer> pathToTwo = new ArrayList<Integer>();
        
        for(Integer vertex : bfs.path(2)) {
            pathToTwo.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToTwo.get(0));
        
        // 3 connects via 5
        ArrayList<Integer> pathToThree = new ArrayList<Integer>();
        
        for(Integer vertex : bfs.path(3)) {
            pathToThree.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToThree.get(0));
        assertEquals(Integer.valueOf(5), pathToThree.get(1));
        
        // 4 connects via 6
        ArrayList<Integer> pathToFour = new ArrayList<Integer>();
        
        for(Integer vertex : bfs.path(4)) {
            pathToFour.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToFour.get(0));
        assertEquals(Integer.valueOf(6), pathToFour.get(1));
        
        // 5 connects directly to 0
        ArrayList<Integer> pathToFive = new ArrayList<Integer>();
        
        for(Integer vertex : bfs.path(5)) {
            pathToFive.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToFive.get(0));

        // 6 connects directly to 0
        ArrayList<Integer> pathToSix = new ArrayList<Integer>();
        
        for(Integer vertex : bfs.path(6)) {
            pathToSix.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToSix.get(0));
    }
    
    @Test
    public void testDistance() {
        GraphAsAdjacencyList graph = new GraphAsAdjacencyList(13);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(6, 4);
        graph.addEdge(4, 3);
        graph.addEdge(5, 3);
        graph.addEdge(7, 8);
        graph.addEdge(9, 10);
        graph.addEdge(9, 12);
        graph.addEdge(9, 11);
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, 0);
        
        assertEquals(1, bfs.distance(1));
        assertEquals(1, bfs.distance(2));
        assertEquals(2, bfs.distance(3));
        assertEquals(2, bfs.distance(4));
        assertEquals(1, bfs.distance(5));
        assertEquals(1, bfs.distance(6));
    }
}
