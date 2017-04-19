package waze;

/*
 * Define a class of a pair of nodes.The nodes in a pair point to each other, thus they do not
 * have a sequence. It is used for constructing an edge since our graph is undirected, 
 * the pair of nodes for each edge do not have any order.
 * 
 */

public class NodePair {
	
	private Node one;
	private Node oneNext;
	private Node two;
	private Node twoNext;
	
	//Constructor
	public NodePair(Node one, Node two){
		this.one=one;
		this.two=two;
		this.oneNext=two;
		this.twoNext=one;
		
	}
	
	//getters and setters
	public Node getOne() {
		return one;
	}

	public void setOne(Node one) {
		this.one = one;
	}

	public Node getTwo() {
		return two;
	}

	public void setTwo(Node two) {
		this.two = two;
	}

	public Node getOneNext() {
		return oneNext;
	}

	public void setOneNext(Node oneNext) {
		this.oneNext = oneNext;
	}

	public Node getTwoNext() {
		return twoNext;
	}

	public void setTwoNext(Node twoNext) {
		this.twoNext = twoNext;
	}
}

