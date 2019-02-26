package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.pekoto.datastructures.Edge;
import com.pekoto.datastructures.EdgeWeightedGraph;
import com.pekoto.datastructures.MinimumSpanningTreeLazyPrim;

public class MinimumSpanningTreeLazyPrimTests {

	@Test
	public void testMst() {
		
		// Set up the graph
		//      1
		// 0----------1 
		// | \        |
		// |   \3     | 2
		// |4    \    |
		// |        \ |
		// 2----------3
		//     5
		EdgeWeightedGraph graph = new EdgeWeightedGraph(4);
		graph.addEdge(new Edge(0, 1, 1.0));
		graph.addEdge(new Edge(0, 2, 4.0));
		graph.addEdge(new Edge(0, 3, 3.0));
		graph.addEdge(new Edge(1, 3, 2.0));
		graph.addEdge(new Edge(2, 3, 5.0));
		
		MinimumSpanningTreeLazyPrim mst = new MinimumSpanningTreeLazyPrim(graph);
		
		ArrayList<Edge> edgeList = mst.getMST();
		
		assertEquals(3, edgeList.size());
		assertEquals(7.0, mst.getWeight(), 0.01);
	}
}
