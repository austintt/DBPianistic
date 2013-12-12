

import java.io.IOException;
import com.google.gson.*;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBServlet
 */
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		DataInteract interact = new DataInteract();
		PrintWriter out =  response.getWriter();
		String req = request.getParameter("type");
		
		List<Piano> pianos = interact.pianoDumpQuery();
		out.println(new Gson().toJson(pianos));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		PrintWriter returnData = response.getWriter();
	
		//get cmd parameter
		String cmd = request.getParameter("cmd");
	

		//addpiano
		if (cmd.equals("add")) 
		{
			//get all parameters
			String id 		= request.getParameter("id");
			String type 	= request.getParameter("type");
			String make 	= request.getParameter("make");
			String model 	= request.getParameter("model");
			String serial 	= request.getParameter("serial");
			String year 	= request.getParameter("year");
			String building = request.getParameter("building");
			String room 	= request.getParameter("room");
			String roomType = request.getParameter("roomType");
			String condition = request.getParameter("condition");

			returnData.println(id + " " + type + " " + make + " " + model + " " + serial);
		}
		else
		{
			returnData.println("Boo");
		}
	}

}
