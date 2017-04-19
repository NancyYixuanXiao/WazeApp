package waze;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WazeApp {
	public static String demo(int node1, int node2, int k) throws FileNotFoundException {
		if (node1 == node2) {
			return "";
		}
		UndiGraph<Node> graph = new UndiGraph<Node> ();
		
		ArrayList<Node> nodeList = GetNodeEdge.getNodes();
		ArrayList<Edge> edgeList = GetNodeEdge.getEdges();
		
		for (int i=0; i<nodeList.size(); i++){
			graph.addNode(nodeList.get(i));
		}
		
		for (int i=0; i<edgeList.size(); i++){
			graph.addEdge(edgeList.get(i));
		}

		Node source = nodeList.get(node1);
		Node sink = nodeList.get(node2);
		
		String finalResult = null;
		StringBuilder edgeResult = new StringBuilder();
		List<Path> path = kDijkstra.kShortestPaths(graph, source, sink, k);
		for (int i=0; i<path.size(); i++){
			System.out.println("The "+(i+1)+"th shortest path: "+path.get(i).getLength());	
			for (int j=0; j<path.get(i).getEdgeList().size(); j++){
			    edgeResult.append(path.get(i).getEdgeList().get(j).getEdgeId());
			    if (j < path.get(i).getEdgeList().size() - 1) {
			    	edgeResult.append(" ");
			    }
			}
			if (i < path.size() - 1) {
				edgeResult.append(",");
			}
			System.out.println();
		}
		finalResult = edgeResult.toString();
		return finalResult;
	}
}

