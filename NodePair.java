package WazeApp;

public class NodePair {
	
	private Node one;
	private Node oneNext;
	private Node two;
	private Node twoNext;

	public NodePair(Node one, Node two){
		this.one=one;
		this.two=two;
		this.oneNext=two;
		this.twoNext=one;
		
	}

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
