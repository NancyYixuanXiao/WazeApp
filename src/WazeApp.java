package waze;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WazeApp {
	public static String waze(int node1, int node2, int k) {
		
		if (node1 == node2) {
			return "";
		}
		
		Map<Integer, Node> nodes = GetNodesEdges.getNodes();
		Map<Integer, Edge> edges = GetNodesEdges.getEdges();
		UndirectedGraph g = new UndirectedGraph(new ArrayList<Edge>(edges.values()));

		String res = "";
		Node source = nodes.get(node1);
		Node sink = nodes.get(node2);
		List<Path> paths = Yen.yen(g, source, sink, k);
		System.out.println("Found " + paths.size() + " paths");
		for (int i = 0; i < paths.size(); i++){
			res += String.valueOf(paths.get(i).getDistance(sink));
			res += "/";
			res += paths.get(i).toString();
			res += ",";
		}
		return res;
	}
}

