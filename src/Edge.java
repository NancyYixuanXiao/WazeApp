package waze;

// define the Edge class
public class Edge {
	private int edgeId;
	private Node start;
	private Node end;
	private double distance;
	
	public Edge(int edgeId, Node start, Node end, double distance) {
		this.edgeId = edgeId;
		this.start = start;
		this.end = end;
		this.distance = distance;
	}
	
	public int getEdgeId() {
		return this.edgeId;
	}
	
	public Node[] getNodes() {
		return new Node[] {start, end};
	}
	
	public double getDistance() {
		return this.distance;
	}
	
}
