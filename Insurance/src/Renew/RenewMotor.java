package Renew;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RenewMotor
 */
@WebServlet("/RenewMotor")
public class RenewMotor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection con;
	private PreparedStatement stmt;
  
	public void init() {
				
		try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Connecting data base please wait.");
    		con = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6404878", "sql6404878", "A1mqd6VZR6");
    		System.out.println("connection");
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Policy_ID = Integer.parseInt(request.getParameter("Policy_id"));
		Double Amount = Double.parseDouble(request.getParameter("amount"));
		String Policy = request.getParameter("policy");
	
		
		try {
			stmt=con.prepareStatement("update Motor_Policy set Actual_bal= '"+Amount+"',Policy = '"+Policy+"' where Motor_Policy_ID = '"+Policy_ID+"'");
			
			Statement s = con.createStatement();
			int result = stmt.executeUpdate();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<b>"+result+" Motor Policy Buy Updated Successfully"+"<br/><br/>"+"Thanks for choosing Motor Policy."); 
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}
