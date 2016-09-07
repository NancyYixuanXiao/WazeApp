package DijkstraFib;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.*;

public class GetNodeEdge {

	private final static ArrayList <Node> nodeList = new ArrayList<Node>();
	public static ArrayList <Node> getNodes(){
		
	String nodeFile = "cal.cnode.txt";
		
	try
    {
        InputStream is = new FileInputStream(nodeFile);
        Reader r = new BufferedReader(new InputStreamReader(is));
        StreamTokenizer st = new StreamTokenizer(r);
        
      for (int i = 0; i < 21048; i++)
        {
            st.nextToken();
            int nodeid=(int)st.nval;
            st.nextToken();
            double lat=st.nval;
            st.nextToken();
            double lng=st.nval;
            Node node = new Node(nodeid,lng,lat);
            nodeList.add(node);
        }

    }
    catch (FileNotFoundException e1)
    {
        System.err.println("File not found.");
    }
    catch (IOException e)
    {
        System.err.println("Cannot access file.");
    }

	       
    return nodeList;
	}
	
	public static ArrayList<Edge> getEdges(){

		ArrayList<Edge> edgeList = new ArrayList<>();
 
		String edgeFile = "cal.cedge.txt";
			
		try
	    {
	        InputStream is = new FileInputStream(edgeFile);
	        Reader r = new BufferedReader(new InputStreamReader(is));
	        StreamTokenizer st = new StreamTokenizer(r);
	        
	      for (int i = 0; i < 21693; i++)
	        {
	            st.nextToken();
	            int edgeid=(int)st.nval;
	            st.nextToken();
	            int nod1=(int)st.nval;
	            st.nextToken();
	            int nod2=(int)st.nval;
	            Node node1=nodeList.get(nod1);
	            Node node2=nodeList.get(nod2);
	            NodePair ends=new NodePair (node1,node2);
	            st.nextToken();
	            double distance=st.nval;
	            Edge edge=new Edge(edgeid,ends,distance);
	            edgeList.add(edge);
	        }

	    }
	    catch (FileNotFoundException e1)
	    {
	        System.err.println("File not found.");
	    }
	    catch (IOException e)
	    {
	        System.err.println("Cannot access file.");
	    }

		       
	    return edgeList;
		}
}
