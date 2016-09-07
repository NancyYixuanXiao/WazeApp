package DijkstraFib;

public class Edge {

	private int edgeId;
	private NodePair ends;
	private double distance;
		
	public Edge(int edgeId, NodePair ends, double distance){
		this.edgeId=edgeId;
		this.ends=ends;
		this.distance=distance;
	}
//	
//	public Edge(Node one, Node two, double distance){
//		NodePair ends=new NodePair(one,two);
//		this.ends=ends;
//		this.distance=distance;
//	}
	
	public int getEdgeId() {
		return edgeId;
	}
	public void setEdgeId(int edgeId) {
		this.edgeId = edgeId;
	}
	public NodePair getEnds(){
		return ends;
	}
	public void setEnds(NodePair ends){
		this.ends=ends;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}
