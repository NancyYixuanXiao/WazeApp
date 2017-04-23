package waze;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WazeServlet
 */
@WebServlet("/WazeServlet")
public class WazeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		int start = Integer.parseInt(request.getParameter("startNodeId"));
		int end = Integer.parseInt(request.getParameter("endNodeId"));
		int k = Integer.parseInt(request.getParameter("numPaths"));
		
		System.out.println("startid = " + start);
		System.out.println("endid = " + end);
		System.out.println("k = " + k);
		PrintWriter writer = response.getWriter();
		try {
			String res = WazeApp.waze(start, end, k);
			writer.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
}
