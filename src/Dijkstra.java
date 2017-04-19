package waze;
/*
 * Define a class Dijkstra to calculate single source shortest path by Dijkstra's algorithm, and
 * output a path.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Dijkstra {
    
    public static Path shortestPaths(UndiGraph<Node> graph, Node source, Node sink) {
        
    	FibonacciHeap<Node> pq = new FibonacciHeap<Node>();

        Map<Node, FibonacciHeap.Entry<Node>> entries = new HashMap<Node, FibonacciHeap.Entry<Node>>();

        ArrayList<Edge> edgelist=new ArrayList<Edge>();
        
        //initialization, key source node 0 and all others infinity 
        for (Node node: graph)
            entries.put(node, pq.enqueue(node, Double.POSITIVE_INFINITY));
        pq.decreaseKey(entries.get(source), 0.0);
        
        //main algorithm. Every time through the loop, extract minimum node and relax its edges until heap is empty.
        while (!pq.isEmpty()) {         
            FibonacciHeap.Entry<Node> curr = pq.dequeueMin();
            //check if current minimum node has infinite priority, if so, there is no path and break.
            if(curr.getPriority()==Double.POSITIVE_INFINITY){
            	break;
            }
            else{
            	for (Edge arc : graph.edgesFrom(curr.getValue())) {
                    double pathCost = curr.getPriority() + arc.getDistance();
                    FibonacciHeap.Entry<Node> dest = entries.get((curr.getValue()==arc.getEnds().getOne())?arc.getEnds().getTwo():arc.getEnds().getOne());
                    //relaxation, if edge get relaxed, decrease key and set it to point to current node
                    if (pathCost < dest.getPriority()){
                    	pq.decreaseKey(dest, pathCost);
                    	dest.getValue().setPrevious(curr.getValue());
                    }
                        
                }
            }      
        }
    	
        //use pointers to get resulting edges for shortest path
        //if heap is not empty, it means there is no path; otherwise heap is empty, 
        //and there is a shortest path from source to sink
    	if (pq.isEmpty()){
    		while ((sink.getPrevious()!=source)&&(sink.getPrevious()!=null)){
    			edgelist.add(0, graph.getEdge(sink.getPrevious(), sink));
        		sink=sink.getPrevious();
        	}
        	if (graph.getEdge(source, sink)!=null){
        		edgelist.add(0, graph.getEdge(source, sink));
        	}
    	}
    	
        Path path=new Path (edgelist);
        return path;
    }

}

