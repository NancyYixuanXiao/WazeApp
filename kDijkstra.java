package DijkstraFib;
import java.util.*;

public final class kDijkstra {
	
	public static Path[] kShortestPaths(UndiGraph<Node> graph, Node source, Node sink, int k){
		
		Path [] A=new Path[k];
		A[0]=Dijkstra.shortestPaths(graph, source, sink);
		
		for (int jj=0;jj<k-1;jj++){

			FibonacciHeap<Path> B=new FibonacciHeap <Path>();
			Map<Path, FibonacciHeap.Entry<Path>> entries = new HashMap<Path, FibonacciHeap.Entry<Path>>();
			Node mid;

			if (jj==0){
				mid=source;
			}
			else{
				mid=graph.findConnection(A[jj].getEdgeList().get(jj-1), A[jj].getEdgeList().get(jj));
			}
			
			for (int i=jj+1;i<=A[jj].getEdgeList().size();i++){

				ArrayList<Edge> rootEdgeList=new ArrayList<Edge>();
				
				for (int ii=0;ii<i-1;ii++){
					rootEdgeList.add(A[jj].getEdgeList().get(ii));
				}
				
				double temp=A[jj].getEdgeList().get(i-1).getDistance();
				A[jj].getEdgeList().get(i-1).setDistance(Double.POSITIVE_INFINITY);
				
				Path spurPath=Dijkstra.shortestPaths(graph, mid, sink);

				if (spurPath.getLength()==0.0){
					
					A[jj].getEdgeList().get(i-1).setDistance(temp);
					
				}
				else{
					
					rootEdgeList.addAll(spurPath.getEdgeList());
					
					Path path=new Path (rootEdgeList);		
					A[jj].getEdgeList().get(i-1).setDistance(temp);
				     
					if (path.getLength()>A[jj].getLength()){
						entries.put(path, B.enqueue(path, path.getLength()));
					}	
				}	
				
				if (i==A[jj].getEdgeList().size())
					break;
				mid=graph.findConnection(A[jj].getEdgeList().get(i-1), A[jj].getEdgeList().get(i));
				
			}
			
			FibonacciHeap.Entry<Path> curr = B.dequeueMin();
			A[jj+1]=curr.getValue();
		}
		return A;
	}
}
