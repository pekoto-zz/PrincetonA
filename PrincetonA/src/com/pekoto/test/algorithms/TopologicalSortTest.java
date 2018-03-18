package com.pekoto.test.algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import com.pekoto.algorithms.TopologicalSort;
import com.pekoto.datastructures.DirectedGraph;

/**
 * Unit tests for the TopologicalSort class
 */
public class TopologicalSortTest {

    @Test
    public void testSort() {
        DirectedGraph graph = new DirectedGraph(7);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(5, 0);
        
        TopologicalSort sort = new TopologicalSort(graph);
        
        ArrayList<Integer> inorder = sort.getTopologicalOrdering();
        
        assertEquals(Integer.valueOf(5), inorder.get(0));
        assertEquals(Integer.valueOf(0), inorder.get(1));
        assertEquals(Integer.valueOf(1), inorder.get(2));
        assertEquals(Integer.valueOf(3), inorder.get(3));
        assertEquals(Integer.valueOf(4), inorder.get(4));
        assertEquals(Integer.valueOf(6), inorder.get(5));
        assertEquals(Integer.valueOf(2), inorder.get(6));
    }
    
    @Test
    public void testCycleDetected() {
        DirectedGraph graph = new DirectedGraph(7);
        
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
        graph.addEdge(5, 0);
        
        // Create cycle between 6 and 3
        graph.addEdge(6, 3);
        
        try {
            new TopologicalSort(graph);
            Assertions.fail("Failed to throw exception when performing topological sort with non-DAG");
        } catch(IllegalArgumentException iae) {
            Assertions.assertTrue(true);
        }
    }
}
