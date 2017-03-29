package WazeApp;

import java.io.*;
import java.util.ArrayList;

public class WazeApp{

	public Path[] WazeCal (int n1, int n2, int k) throws FileNotFoundException {
		// TODO Auto-generated method stub

		UndiGraph<Node> graph = new UndiGraph<Node> ();
		
		ArrayList<Node> nodeList = GetNodeEdge.getNodes();
		ArrayList<Edge> edgeList = GetNodeEdge.getEdges();
		
		for (int i=0;i<nodeList.size();i++){
			graph.addNode(nodeList.get(i));
		}
		
		for (int i=0;i<edgeList.size();i++){
			graph.addEdge(edgeList.get(i));
		}
		
		Node source = nodeList.get(n1);
		Node sink = nodeList.get(n2);
		
		Path [] path=KDijkstra.kShortestPaths(graph, source, sink, k);
		
		for (int i=0;i<k;i++){
			
			String fileName = "route" + (i+1) + ".txt";
			PrintWriter writer = new PrintWriter(fileName);
			for (int j=0;j<path[i].getEdgeList().size();j++){
				
				writer.print(path[i].getEdgeList().get(j).getEdgeId());
			    writer.print(" ");
			}

			writer.close();
		}
		return path;
	}
}

