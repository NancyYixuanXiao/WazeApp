package WazeApp;

import java.util.*; // For HashMap

public final class UndiGraph<T> implements Iterable<T> {
    
    private final Map<Node, ArrayList<Edge>> mGraph = new HashMap<Node, ArrayList<Edge>>();

    public boolean addNode(Node node) {

        if (mGraph.containsKey(node))
            return false;

        mGraph.put(node, new ArrayList<Edge>());
        return true;
    }
    
    public void addEdge(Edge edge) {

        if (!mGraph.containsKey(edge.getEnds().getOne()) || !mGraph.containsKey(edge.getEnds().getTwo()))
            throw new NoSuchElementException("Both nodes must be in the graph.");
        
        mGraph.get(edge.getEnds().getOne()).add(edge);
        
        mGraph.get(edge.getEnds().getOneNext()).add(edge);
        
    }
    
    public Edge getEdge(Node one, Node two){
    	
    	if (!mGraph.containsKey(one))
            throw new NoSuchElementException("node"+one.getNodeId()+" is not in the graph");
    	
    	if (!mGraph.containsKey(two))
            throw new NoSuchElementException("node"+two.getNodeId()+" is not in the graph");

    	for (Edge edge:mGraph.get(one)){
    		Node node=(one==edge.getEnds().getOne())?edge.getEnds().getTwo():edge.getEnds().getOne();
    		if (node==two){
    			return edge;
    		}
    			
    	}
    	return null;
    	
    }

    public void removeEdge(Edge edge) {

        if (!mGraph.containsKey(edge.getEnds().getOne()) || !mGraph.containsKey(edge.getEnds().getOneNext()))
            throw new NoSuchElementException("Both nodes must be in the graph.");

        mGraph.get(edge.getEnds().getOne()).remove(edge);
        mGraph.get(edge.getEnds().getOneNext()).remove(edge);
    }

    public ArrayList<Edge> edgesFrom(Node node) {

        ArrayList<Edge> arcs = mGraph.get(node);
        if (arcs == null)
            throw new NoSuchElementException("No edge from this source node.");

        return arcs;
    }
    
    public Node findConnection(Edge edge1,Edge edge2){
		NodePair pair1=edge1.getEnds();
		int pair1One=pair1.getOne().getNodeId();
		int pair1Two=pair1.getOneNext().getNodeId();
		NodePair pair2=edge2.getEnds();
		int pair2One=pair2.getOne().getNodeId();
		int pair2Two=pair2.getOneNext().getNodeId();
		
		if ((pair1One==pair2One)||(pair1One==pair2Two)){
			return pair1.getOne();
		}
		else if((pair1Two==pair2One)||(pair1Two==pair2Two)){
			return pair1.getOneNext();
		}
		else {
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

