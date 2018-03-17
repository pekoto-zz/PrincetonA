package com.pekoto.test.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import com.pekoto.algorithms.DepthFirstSearch;
import com.pekoto.datastructures.GraphAsAdjacencyList;

/**
 * Unit tests for the DepthFirstSearch class
 */
public class DepthFirstSearchTests {

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
        
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        
        assertTrue(dfs.pathExists(1));
        assertTrue(dfs.pathExists(2));
        assertTrue(dfs.pathExists(3));
        assertTrue(dfs.pathExists(4));
        assertTrue(dfs.pathExists(5));
        assertTrue(dfs.pathExists(6));
        assertFalse(dfs.pathExists(7));
        assertFalse(dfs.pathExists(8));
        assertFalse(dfs.pathExists(9));
        assertFalse(dfs.pathExists(10));
        assertFalse(dfs.pathExists(11));
        assertFalse(dfs.pathExists(12));
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
        
        DepthFirstSearch dfs = new DepthFirstSearch(graph, 0);
        
        // 1 connects directly to 0
        ArrayList<Integer> pathToOne = new ArrayList<Integer>();
        
        for(Integer vertex : dfs.path(1)) {
            pathToOne.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToOne.get(0));
        
        // 2 connects directly to 0
        ArrayList<Integer> pathToTwo = new ArrayList<Integer>();
        
        for(Integer vertex : dfs.path(2)) {
            pathToTwo.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToTwo.get(0));
        
        // 3 connects via 6, 4
        ArrayList<Integer> pathToThree = new ArrayList<Integer>();
        
        for(Integer vertex : dfs.path(3)) {
            pathToThree.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToThree.get(0));
        assertEquals(Integer.valueOf(6), pathToThree.get(1));
        assertEquals(Integer.valueOf(4), pathToThree.get(2));
        
        // 4 connects via 6
        ArrayList<Integer> pathToFour = new ArrayList<Integer>();
        
        for(Integer vertex : dfs.path(4)) {
            pathToFour.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToFour.get(0));
        assertEquals(Integer.valueOf(6), pathToFour.get(1));
        
        // 5 connects via 4, 6
        ArrayList<Integer> pathToFive = new ArrayList<Integer>();
        
        for(Integer vertex : dfs.path(5)) {
            pathToFive.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToFive.get(0));
        assertEquals(Integer.valueOf(6), pathToFive.get(1));
        assertEquals(Integer.valueOf(4), pathToFive.get(2));

        // 6 connects directly to 0
        ArrayList<Integer> pathToSix = new ArrayList<Integer>();
        
        for(Integer vertex : dfs.path(6)) {
            pathToSix.add(vertex);
        }
        
        assertEquals(Integer.valueOf(0), pathToSix.get(0));
    }
}
