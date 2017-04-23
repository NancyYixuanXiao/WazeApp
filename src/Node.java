package waze;

// define the Node class
public class Node {
	
	private int nodeId;
	private Node previous;
	
	public Node(int nodeId) {
		this.nodeId = nodeId;
	}


	public int getNodeId() {
		return this.nodeId;
	}
	
	public void setPrevious(Node node) {
		this.previous = node;
	}
	
	public Node getPrevious() {
		return this.previous;
	}
}
