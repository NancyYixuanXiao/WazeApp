package waze;

public class Endpoint {
	
	private Node Node;
	private double distance;
	
	public Endpoint(Node node, double distance) {
		this.Node = node;
		this.distance = distance;
	}
	
	public Node getNode() {
		return Node;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
}
