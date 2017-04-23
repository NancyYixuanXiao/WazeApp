package waze;

// define the undirected graph class

import java.util.*;

public class UndirectedGraph {
	
	public Map<Node, List<Endpoint>> graph = null;
	
	public UndirectedGraph(List<Edge> edges) {
		graph = new HashMap<> ();
		for (Edge edge : edges) {
			Node[] ends = edge.getNodes();
			for (int i = 0; i < ends.length; i++) {
				if (!graph.containsKey(ends[i])) {
					graph.put(ends[i], new ArrayList<Endpoint>());
				}
				graph.get(ends[i]).add(new Endpoint(ends[i^1], edge.getDistance()));
			}
		}
		System.out.println("The graph is ready.");
	}
	
	// set the length of edge with two nodes node1 and node2 to length
	// and return the original length before change
	public double alterEdge(Node node1, Node node2, double length) {
		double oriLen = 0.0;
		for (Endpoint tmp : this.graph.get(node1)) {
			if (tmp.getNode() == node2) {
				oriLen = tmp.getDistance();
				tmp.setDistance(length);
			}
		}
		for (Endpoint tmp : this.graph.get(node2)) {
			if (tmp.getNode() == node1) {
				tmp.setDistance(length);
			}
		}
		return oriLen;
	}
	
	public double getEdgeWeight(Node node1, Node node2) {
		int i = 0;
		for (i = 0; i < this.graph.get(node1).size(); i++) {
			if (this.graph.get(node1).get(i).getNode() == node2) {
				break;
			}
		}
		return this.graph.get(node1).get(i).getDistance();
	}
}
