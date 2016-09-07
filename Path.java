package DijkstraFib;
import java.util.*;

public class Path {

	private ArrayList<Edge> edgeList;
	private double length;
	
	public Path(ArrayList<Edge> edgeList){
		this.edgeList=edgeList;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}
	public void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}
	
	public double getLength(){
		double length=0;
		for (int i=0;i<edgeList.size();i++){
			length=length+edgeList.get(i).getDistance();
		}
		return length;
	}
}
