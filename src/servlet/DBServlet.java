package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class for Servlet: DBToucher
 *
 */
public class DBServlet extends HttpServlet
{
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		//response.setContentType("application/json");
		response.setContentType("text/plain");
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		out.println(getResult(type));
		
		public String getResult(String type)
		{
			String location = "";
			String id = "";
			String condition = "";
			if(type.equalsIgnoreCase("Grand"))
			{
				location = "Austin, Room 213"; id = "180582087"; condition = "Good";
				System.out.println(location + " " + id + " " + condition);
			} 
			else if(type.equalsIgnoreCase("Accordian"))
			{
				location = "Taylor, Room 123"; id = "180582087"; condition = "Fair";
				System.out.println(location + " " + id + " " + condition);
			} 
			else{ location = "type Number not found"; }
			String result = "<Pianos>";
			result += "<Piano>"; result += "<location>" + location + "</location>";
			result += "<id>" +id + "</id>";
			result += "<condition>" +condition + "</condition>";
			result += "</Piano>"; result += "</Pianos>";
			System.out.println("We ain't got no pianos!"	);
			return result;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("**** Piano INFO ****");
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		System.out.println(getResult(type)); out.println(getResult(type));
	}
}