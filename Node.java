package DijkstraFib;

public class Node {

	private int nodeId;
	private double lat;
	private double lng;
	private Node previous;
	
	public Node(int nodeId, double lat, double lng){
		this.nodeId=nodeId;
		this.lat=lat;
		this.lng=lng;
	}
	
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

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}


}
