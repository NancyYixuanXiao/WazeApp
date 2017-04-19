package waze;
/*
 * Define a class to calculate k shortest path based on Yen's algorithm, which is based on 
 * Dijkstra, and output an array of path.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class kDijkstra {
	
	public static List<Path> kShortestPaths(UndiGraph<Node> graph, Node source, Node sink, int k){
		
		List<Path> A = new ArrayList<>();
		//A[0] is the shortest path
		A.add(Dijkstra.shortestPaths(graph, source, sink));
		
		//calculate k-1 shortest path
		for (int jj=0;jj<k-1;jj++){
			//put potential shortest path in Fibonacci heap B
			FibonacciHeap<Path> B = new FibonacciHeap <Path>();
			Map<Path, FibonacciHeap.Entry<Path>> entries = new HashMap<Path, FibonacciHeap.Entry<Path>>();
			Node mid; 
			if (jj == 0){
				mid = source;
			}
			else{
				mid = graph.findConnection(A.get(jj).getEdgeList().get(jj-1), A.get(jj).getEdgeList().get(jj));
			}
			
			//to find another shortest path, keep the first i edges from previous shortest path
			//and run Dijkstra to find another shortest path between that intersection node and 
			//sink, to get a potential shortest path.
			for (int i=jj+1; i<=A.get(jj).getEdgeList().size(); i++){

				ArrayList<Edge> rootEdgeList = new ArrayList<Edge>();
				
				//remain i edges from previous shortest path and add to root path
				for (int ii=0; ii<i-1; ii++){
					rootEdgeList.add(A.get(jj).getEdgeList().get(ii));
				}
				
				//increase one edge distance to infinity to avoid finding the same path
				double temp = A.get(jj).getEdgeList().get(i-1).getDistance();
				A.get(jj).getEdgeList().get(i-1).setDistance(Double.POSITIVE_INFINITY);
				//find a deviation of the previous shortest path
				Path spurPath = Dijkstra.shortestPaths(graph, mid, sink);
				
				//if spurPath does not exist, decrease the edge back to its original and continue on next search
				if (spurPath.getLength() == 0.0){		
					A.get(jj).getEdgeList().get(i-1).setDistance(temp);			
				}
				else{
					//if found another shortest path, add to root path
					rootEdgeList.addAll(spurPath.getEdgeList());			
					Path path = new Path (rootEdgeList);		
					A.get(jj).getEdgeList().get(i-1).setDistance(temp);
					//check if the path is longer than previous one to prevent add same path to B
					if (path.getLength() > A.get(jj).getLength()){
						entries.put(path, B.enqueue(path, path.getLength()));
					}	
				}	
				//break if loop through all edges in previous path
				if (i == A.get(jj).getEdgeList().size())
					break;
				mid=graph.findConnection(A.get(jj).getEdgeList().get(i-1), A.get(jj).getEdgeList().get(i));
				
			}
			//get the shortest path from all potential paths by minimum distance
			if (B.isEmpty()) {
				break;
			}
			FibonacciHeap.Entry<Path> curr = B.dequeueMin();
			A.add(curr.getValue());
		}
		return A;
	}
}

