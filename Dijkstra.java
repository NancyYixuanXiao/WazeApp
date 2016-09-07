package DijkstraFib;

import java.util.*; 

public final class Dijkstra {
    
    public static Path shortestPaths(UndiGraph<Node> graph, Node source, Node sink) {
        
    	FibonacciHeap<Node> pq = new FibonacciHeap<Node>();

        Map<Node, FibonacciHeap.Entry<Node>> entries = new HashMap<Node, FibonacciHeap.Entry<Node>>();

        ArrayList<Edge> edgelist=new ArrayList<Edge>();

        for (Node node: graph)
            entries.put(node, pq.enqueue(node, Double.POSITIVE_INFINITY));

        pq.decreaseKey(entries.get(source), 0.0);
        	
        while (!pq.isEmpty()) {
            
            FibonacciHeap.Entry<Node> curr = pq.dequeueMin();
            if(curr.getPriority()==Double.POSITIVE_INFINITY){
            	break;
            }
            else{
            	for (Edge arc : graph.edgesFrom(curr.getValue())) {
                    double pathCost = curr.getPriority() + arc.getDistance();
                    FibonacciHeap.Entry<Node> dest = entries.get((curr.getValue()==arc.getEnds().getOne())?arc.getEnds().getTwo():arc.getEnds().getOne());
                    if (pathCost < dest.getPriority()){
                    	pq.decreaseKey(dest, pathCost);
                    	dest.getValue().setPrevious(curr.getValue());
                    }
                        
                }
            }      
        }
    	
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
