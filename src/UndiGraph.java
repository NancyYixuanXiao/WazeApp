package waze;

/*
 * Define a class of undirected graph. It is constructed from Nodes and Edges.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public final class UndiGraph<T> implements Iterable<T> {
    
	//create a graph which is a map of node to edges
    private final Map<Node, ArrayList<Edge>> mGraph = new HashMap<Node, ArrayList<Edge>>();
    
    //add node to graph
    public boolean addNode(Node node) {
    	//if graph already has this node, do nothing
        if (mGraph.containsKey(node))
            return false;

        mGraph.put(node, new ArrayList<Edge>());
        return true;
    }
    //add edge to graph
    public void addEdge(Edge edge) {
    	
    	//if the nodes of this edge is not in graph, throw exception
        if (!mGraph.containsKey(edge.getEnds().getOne()) || !mGraph.containsKey(edge.getEnds().getTwo()))
            throw new NoSuchElementException("Both nodes must be in the graph.");
        //add edge and map it to its nodes
        mGraph.get(edge.getEnds().getOne()).add(edge);   
        mGraph.get(edge.getEnds().getOneNext()).add(edge);
    }
    
    //return an edge with given nodes
    public Edge getEdge(Node one, Node two){
    	
    	if (!mGraph.containsKey(one))
            throw new NoSuchElementException("node"+one.getNodeId()+" is not in the graph");
    	
    	if (!mGraph.containsKey(two))
            throw new NoSuchElementException("node"+two.getNodeId()+" is not in the graph");
    	
    	for (Edge edge:mGraph.get(one)){
    		//System.out.println("incident edge on node "+one.getNodeId()+": "+edge.getEdgeId());
    		Node node=(one==edge.getEnds().getOne())?edge.getEnds().getTwo():edge.getEnds().getOne();
    		if (node==two){
    			//System.out.println("Found edge "+edge.getEdgeId());
    			return edge;
    		}
    			
    	}
		return null; 	
    }
    
    //return edges from a given node
    public ArrayList<Edge> edgesFrom(Node node) {

        ArrayList<Edge> arcs = mGraph.get(node);
        if (arcs == null)
            throw new NoSuchElementException("No edge from this source node.");

        return arcs;
    }
    
    //return the intersection node of two given edges
    public Node findConnection(Edge edge1,Edge edge2){
		NodePair pair1=edge1.getEnds();
		int pair1One=pair1.getOne().getNodeId();
		int pair1Two=pair1.getOneNext().getNodeId();
		//System.out.println("edge "+edge1.getEdgeId()+": node"+pair1One+"-node"+pair1Two);
		NodePair pair2=edge2.getEnds();
		int pair2One=pair2.getOne().getNodeId();
		int pair2Two=pair2.getOneNext().getNodeId();
		//System.out.println("edge "+edge2.getEdgeId()+": node"+pair2One+"-node"+pair2Two);
		
		if ((pair1One==pair2One)||(pair1One==pair2Two)){
			//System.out.println("enter if");
			return pair1.getOne();
		}
		else if((pair1Two==pair2One)||(pair1Two==pair2Two)){
			//System.out.println("enter else if");
			return pair1.getOneNext();
		}
		else {
			//System.out.println("two edges do not connect");
			return null;
		}
	}

    @SuppressWarnings("unchecked")
	public Iterator<T> iterator() {
        return (Iterator<T>) mGraph.keySet().iterator();
    }
    
    public boolean containsNode(Node node) {
        return mGraph.containsKey(node);
    }
    
    public boolean containsEdge(Edge edge)
    {
    	return mGraph.containsValue(edge);
    }
    
    public int size() {
        return mGraph.size();
    }
    
    public boolean isEmpty() {
        return mGraph.isEmpty();
    }
}

