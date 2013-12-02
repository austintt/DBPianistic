package servlet;
import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: DBToucher
 *
 */
<<<<<<< HEAD
 public class DBToucher extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DBToucher() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("This is from the server!");
		DataToucher querydb = new DataToucher();
		querydb.run();
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
=======


public class DBToucher extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	static final long serialVersionUID = 1L;
	public DBToucher() { super(); }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("**** Piano INFO ****");
		String type = request.getParameter("type");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		System.out.println(getResult(type)); out.println(getResult(type));
	}
	public String getResult(String type)
	{
		String location = "";
		String id = "";
		String condition = "";
		if(type.equalsIgnoreCase("Grand"))
		{
			location = "Austin, Room 213"; id = "180582087"; condition = "Good";
		} 
		else if(type.equalsIgnoreCase("Accordian"))
		{
			location = "Taylor, Room 123"; id = "180582087"; condition = "Fair";
		} 
		else{ location = "type Number not found"; }
		String result = "<Pianos>";
		result += "<Piano>"; result += "<location>" + location + "</location>";
		result += "<id>" +id + "</id>";
		result += "<condition>" +condition + "</condition>";
		result += "</Piano>"; result += "</Pianos>";
		return result;
	}
>>>>>>> c2be2bb88177aa3fe1b80230091075fe6b6c0a09
}