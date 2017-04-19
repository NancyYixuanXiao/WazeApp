<%@ page import="java.sql.*"%>  
  
<%  
String s = request.getParameter("node_id");  
if (s != null && !s.trim().equals("")) 
{   
	int id = Integer.parseInt(s);  
	System.out.println("query latlng for node " + id);
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/waze?useSSL=false","root","123456");  
		PreparedStatement ps = con.prepareStatement("select latitude, longitude from nodes where node_id = ?");  
		ps.setInt(1, id);  
		ResultSet rs = ps.executeQuery();  
		while(rs.next()) {  
			System.out.println(rs.getDouble("latitude") + " " + rs.getDouble("longitude"));
			out.println(rs.getDouble("latitude") + " " + rs.getDouble("longitude"));
		}  
		con.close();  
	}
	catch(Exception e) {
		e.printStackTrace();
	}  
}  
%>  