package com.pekoto.datastructures;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * A minimum spanning tree using a lazy implementation of
 * Prim's algorithm.
 * 
 * Minimum Spanning Tree (MST):
 * The set of edges that touch each vertex one
 * such that the total distance of the edges is minimized.
 * 
 * An MST will contain n-1 edges, given n vertices in the graph.
 * 
 * If all edges in the graph are of unique weight, then
 * there will be 1 unique MST.
 * 
 * If the edges are not unique there may be multiple MSTs.
 * 
 * If the graph is not strongly connected, then you can generate
 * a minimum spanning FOREST -- that is a series of MSTs that connect
 * every component.
 * 
 * The 2 main algorithms to generate an MST are Kruskal's algorithm and Prim's algorithm.
 * 
 * Here we will use Prim's algorithm:
 * 
 * 1. Take a vertex
 * 2. Add all the edges from that vertex to a priority queue
 * 3. Take the minimum edge that that will connect a vertex not already in the tree
 * 
 * Finally you will get all of the minimum edges.
 * 
 * Time: O(e log e) -- add every edge to the priority queue
 * Space: O(e) -- add every edge to the priority queue
 * (Space can be improved using an eager implementation)
 */
public class MinimumSpanningTreeLazyPrim {
	
	private boolean [] visited;
	private ArrayList<Edge> MST;
	private PriorityQueue<Edge> priorityQueue;
	private double weight;
	
	/*
	 * 1. Visit the first vertex
	 * 2. While the PQ !empty && MST < n-1
	 * 3. Get the min edge
	 * 4. If both vertices on this edge have been visited, continue
	 * 5. Otherwise, add this edge to the MST
	 * 6. Visit the unvisited vertices
	 */
	public MinimumSpanningTreeLazyPrim(EdgeWeightedGraph graph) {
		visited = new boolean[graph.numOfVertices()];
		MST = new ArrayList<>();
		priorityQueue = new PriorityQueue<>();
		
		visit(graph, 0);
		
		while(!priorityQueue.isEmpty() && MST.size() < graph.numOfEdges()-1)
		{
			Edge minEdge = priorityQueue.poll();
			int from = minEdge.either();
			int to = minEdge.other(from);
			
			if(visited[from] && visited[to])
			{
				continue;
			}
			
			MST.add(minEdge);
			weight += minEdge.weight();
			
			if(!visited[from]) {
				visit(graph, from);
			}
			
			if(!visited[to]) {
				visit(graph, to);
			}
		}
	}
	
	/*
	 * 1. Set this vertex visited
	 * 2. For each of the edges from this vertex...
	 * 	2.1 Get the vertex connected to from this edge
	 * 	2.2 If it's not in the MST yet
	 * 	2.3 Add this edge to the MST
	 */
	private void visit(EdgeWeightedGraph graph, int vertex) {
		visited[vertex] = true;
		
		for(Edge edge : graph.adjacentVertices(vertex)) {
			int otherVertex = edge.other(vertex);
			
			if(!visited[otherVertex]) {
				priorityQueue.add(edge);
			}
		}
	}
	
	public ArrayList<Edge> getMST() {
		return MST;
	}
	
	public double getWeight() {
		return weight;
	}
}
