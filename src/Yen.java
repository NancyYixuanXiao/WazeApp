package waze;

import java.util.*;

// implement Yen's algrithm to calculate k shortest path

public class Yen {
	public static List<Path> yen(UndirectedGraph g, Node source, Node sink, int k) {
		List<Path> paths = new ArrayList<>();
		
		// first add the shortest path 
		paths.add(Dijkstra.dijkstra(g, source, sink));
		System.out.println("The 1th shortest path is " + paths.get(0).getDistance(sink));
		//calculate k-1 shortest paths based on the shortest path
		for (int i = 0; i < k - 1; i++) {
			// put potential shortest paths in fibonacci heap, and choose the shortest
			FibonacciHeap<Path> fheap = new FibonacciHeap<> ();
			Map<Path, FibonacciHeap.Entry<Path>> entries = new HashMap<>();
			Path prevPath = paths.get(i);			
			System.out.println("Calculating the " + (i + 2) + "th shortest path: ");
			for (int j = 0; j < prevPath.path.size() - 1; j++) {
				Node spurNode = prevPath.path.get(j);
				// rootPath has the same first j nodes from the previous shortest path
				List<Node> rootlist = new ArrayList<>();
				Map<Node, Double> rootPathLengths = new HashMap<>();
				for (int m = 0; m <= j; m++) {
					Node tmp = prevPath.path.get(m);
					rootlist.add(tmp);
					rootPathLengths.put(tmp, prevPath.getDistance(tmp));
				}
				double removeEdge = g.alterEdge(spurNode, prevPath.path.get(j + 1), Double.POSITIVE_INFINITY);	
//				System.out.println("whether edge removed: " + (g.getEdgeWeight(spurNode, prevPath.path.get(j + 1)) == Double.POSITIVE_INFINITY));
				Path spurPath = Dijkstra.dijkstra(g, source, spurNode);
				if (spurPath == null) {
					g.alterEdge(spurNode, prevPath.path.get(j + 1), removeEdge);
					continue;
				}
				spurPath.path.remove(0);
				double diff = spurPath.lengths.get(spurNode) - rootPathLengths.get(spurNode);
				for (Node node : rootPathLengths.keySet()) {
					rootPathLengths.put(node, rootPathLengths.get(node) + diff);
				}
				for (Node node : spurPath.lengths.keySet()) {
					if (!rootPathLengths.containsKey(node)) {
						rootPathLengths.put(node, spurPath.lengths.get(node));
					}
				}
				rootlist.addAll(spurPath.path);
				Path potentialPath = new Path(rootlist, rootPathLengths);
				if (rootPathLengths.get(sink) > prevPath.getDistance(sink)) {
					entries.put(potentialPath, 
							fheap.enqueue(potentialPath, rootPathLengths.get(sink)));
				}
				g.alterEdge(spurNode, prevPath.path.get(j + 1), removeEdge);
//				System.out.println("whether edge restored: " + (g.getEdgeWeight(spurNode, prevPath.path.get(j + 1)) == removeEdge));
			}
			if (fheap.isEmpty()) {
				break;
			}
			System.out.println("The " + (i + 2) + "th shortest path is " + fheap.min().getPriority());
			paths.add(fheap.dequeueMin().getValue());
		}
		return paths;
	}
}
