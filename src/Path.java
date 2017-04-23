package waze;

import java.util.*;

// define the path class

public class Path {
	
	public List<Node> path;
	public Map<Node, Double> lengths; // distance from source to each node in the graph
	
	public Path(List<Node> nodelist, Map<Node, Double> lengths) {
		path = new ArrayList<>(nodelist);
		this.lengths = new HashMap<>(lengths);
	}
	
	public Path(Node source, Node sink, Map<Node, Double> lengths) {
		this.lengths = new HashMap<>(lengths);
		path = new ArrayList<>();
		Node curr = sink;
		while (curr.getPrevious() != null && curr != source) {
			path.add(curr);
			curr = curr.getPrevious();
		}
		if (curr == source) {
			path.add(source);
		} else {
			path = null;
		}
	}
	
	public double getDistance(Node node) {
		return lengths.get(node);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node node : this.path) {
			sb.append(node.getNodeId() + " ");
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
