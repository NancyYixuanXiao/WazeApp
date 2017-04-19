package waze;
/*
 * Define a class to read txt file and return ArrayLists of Nodes and Edges
 */
import java.util.*;
import java.sql.*;

public class GetNodeEdge {
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/waze?useSSL=false";

	//  Database credentials
	static final String USER = "root";
    static final String PASS = "123456";
   
	private static ArrayList <Node> nodeList;
	
	public static ArrayList<Node> getNodes(){
		nodeList = new ArrayList<Node>();
	    Connection conn = null;
	    Statement stmt = null;
	    try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT node_id, latitude, longitude FROM nodes";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("node_id");
	         double latitude = rs.getDouble("latitude");
	         double longitude = rs.getDouble("longitude");
	         nodeList.add(new Node(id, latitude, longitude));
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   return nodeList;
	}
	
	public static ArrayList<Edge> getEdges(){

		ArrayList<Edge> edgeList = new ArrayList<>();
		Connection conn = null;
	    Statement stmt = null;
	    try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      //STEP 4: Execute a query
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT edge_id, start_id, end_id, distance FROM edges";
	      ResultSet rs = stmt.executeQuery(sql);

	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int id  = rs.getInt("edge_id");
	         int start_id = rs.getInt("start_id");
	         int end_id = rs.getInt("end_id");
	         double distance = rs.getDouble("distance");
	         NodePair curr = new NodePair(nodeList.get(start_id), nodeList.get(end_id));
	         edgeList.add(new Edge(id, curr, distance));
	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   return edgeList;
	}
}

