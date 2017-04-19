package waze;

/*
 * Define a class of Node. Each node has node Id, latitude and longitude.
 * It is used to construct a graph.
 */

public class Node {

	private int nodeId;
	private double lat;
	private double lng;
	private Node previous;
	
	//Constructor
	public Node(int nodeId, double lat, double lng){
		this.nodeId=nodeId;
		this.lat=lat;
		this.lng=lng;
	}
	
	//getters and setters
	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	//set a pointer points to the previous node of current node in path
	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

}

