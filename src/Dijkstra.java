package waze;

import java.util.*;

// implement Dijkstra's algrithm to calculate shortest path
public class Dijkstra {
	public static Path dijkstra(UndirectedGraph g, Node source, Node sink) {
		
		FibonacciHeap<Node> fheap = new FibonacciHeap<>();
		Map<Node, FibonacciHeap.Entry<Node>> entries = new HashMap<>();
		Map<Node, Double> lengths = new HashMap<>();
		
		// Initialization
		for (Node node : g.graph.keySet()) {
			entries.put(node, fheap.enqueue(node, Double.POSITIVE_INFINITY));
		}
		fheap.decreaseKey(entries.get(source), 0.0);
		FibonacciHeap.Entry<Node> curr = null;
		while (!fheap.isEmpty()) {
			double min = fheap.min().getPriority();
			curr = fheap.dequeueMin();
			lengths.put(curr.getValue(), min);
			// if current node's priority is infinity, then there is no path between source and sink
			if (curr.getPriority() == Double.POSITIVE_INFINITY) {
				break;
			}
			// relaxation for each node the curr is connected to
			boolean relaxed = false;
			for (Endpoint end : g.graph.get(curr.getValue())) {
				double cost = curr.getPriority() + end.getDistance();
				FibonacciHeap.Entry<Node> dest = entries.get(end.getNode());
				if (cost < dest.getPriority()) {
					relaxed = true;
					fheap.decreaseKey(dest, cost);
					dest.getValue().setPrevious(curr.getValue());
				}
			}
		}
		// if the heap is empty, then we found a path, otherwise there is no path
		if (fheap.isEmpty()) {
			Path path = new Path(source, sink, lengths);
			return path;
		}
		return null;
	}
}
