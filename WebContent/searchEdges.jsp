<%@ page import="java.sql.*"%>  
  
<%  
String s = request.getParameter("id");  
if (s == null || s.trim().equals("")) 
{  
	out.print("Please enter id");  
}
else
{  
	int id = Integer.parseInt(s);  
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/waze?useSSL=false","root","123456");  
		PreparedStatement ps = con.prepareStatement("select edge_id from edges where start_id = ? or end_id = ?");  
		ps.setInt(1,id);  
		ps.setInt(2, id);
		ResultSet rs = ps.executeQuery();  
		while(rs.next()){  
			out.print(rs.getInt("edge_id") + " ");
		}  
		con.close();  
	}
	catch(Exception e)
		{
		e.printStackTrace();
	}  
}  
%>  