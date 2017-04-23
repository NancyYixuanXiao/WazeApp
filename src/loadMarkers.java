package waze;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class loadMarkers {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/waze?useSSL=false";

	//  Database credentials
	static final String USER = "root";
    static final String PASS = "123456";
	public static void main(String argv[]) {
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("markers");
			doc.appendChild(rootElement);

			Connection conn = null;
		    Statement stmt = null;
		    try {
		        //STEP 2: Register JDBC driver
		    	Class.forName("com.mysql.jdbc.Driver");

		        //STEP 3: Open a connection
		        conn = DriverManager.getConnection(DB_URL,USER,PASS);

		        //STEP 4: Execute a query
		        stmt = conn.createStatement();
		        String sql;
		        sql = "SELECT node_id, longitude, latitude FROM nodes";
		        ResultSet rs = stmt.executeQuery(sql);

		        //STEP 5: Extract data from result set
		        while (rs.next()) {
		        	//Retrieve by column name
		            int id  = rs.getInt("node_id");
		            double lng = rs.getDouble("longitude");
		            double lat = rs.getDouble("latitude");
		            Element child = doc.createElement("marker");
		            rootElement.appendChild(child);
		            child.setAttribute("id", String.valueOf(id));
		            child.setAttribute("lat", String.valueOf(lat));
		            child.setAttribute("lng", String.valueOf(lng));
		        }
		        
		        //STEP 6: Clean-up environment
		        rs.close();
		        stmt.close();
		        conn.close();
		    } catch (SQLException se) {
		    	//Handle errors for JDBC
		        se.printStackTrace();
		    } catch (Exception e) {
		    	//Handle errors for Class.forName
		        e.printStackTrace();
		    } finally {
		      //finally block used to close resources
		    try {
		    	if (stmt!=null) {
		    		stmt.close(); 
		        }
		    } catch(SQLException se2) {
		    }// nothing we can do
		    try {
		    	if (conn!=null) {
		    		conn.close();
		        }
		    } catch (SQLException se) {
		    	se.printStackTrace();
		    }//end finally try
		    }//end try
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("WebContent/sample.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
}
