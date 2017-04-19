<%@ page import="java.sql.*"%>  
  
<%  
String s = request.getParameter("edge_id"); 
if (s != null && !s.trim().equals("")) 
{   
	int id = Integer.parseInt(s); 
	System.out.println("query nodepair for edge " + id);
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/waze?useSSL=false","root","123456");  
		PreparedStatement ps = con.prepareStatement("select start_id, end_id from edges where edge_id = ?");  
		ps.setInt(1, id);  
		ResultSet rs = ps.executeQuery();  
		while(rs.next()){ 
			System.out.println(rs.getInt("start_id") + " " + rs.getInt("end_id"));
			out.println(rs.getInt("start_id") + " " + rs.getInt("end_id"));
		}  
		con.close();  
	}
	catch(Exception e) {
		e.printStackTrace();
	}  
}  
%>  