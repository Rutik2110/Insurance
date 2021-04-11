package Buy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Travel_Policy
 */
@WebServlet("/Travel_Policy")
public class TravelPolicyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
	PreparedStatement stmt;
    public void init()
    {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Connecting data base please wait.");
    		con = DriverManager.getConnection("jdbc:mysql://localhost/insurance","root","root");
    		System.out.println("connection");
			stmt=con.prepareStatement("insert into Travel_Policy(Insured_Last_Name ,First_Name ,Email ,City ,State ,Region ,Pincode ,Passport_Number , Country ,Actual_bal ,Policy ,First_Installment ) values(?,?,?,?,?,?,?,?,?,?,?,?);");
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Insured_Last_Name = request.getParameter("lname");
		String First_Name = request.getParameter("fname");
		String Email_Address = request.getParameter("email");
		String City = request.getParameter("city");
		
		String State = request.getParameter("state");
		String Region = request.getParameter("region");
		String Pincode = request.getParameter("pin");
		String PassportNumber = request.getParameter("pass");
		
		
		String Country = request.getParameter("country");
		Double Actual_bal = Double.parseDouble(request.getParameter("amount"));
		String Policy = request.getParameter("policy");
		Double First_Installment = Double.parseDouble(request.getParameter("amount"));
		
		try {
			stmt.setString(1, Insured_Last_Name);
			stmt.setString(2, First_Name);
			stmt.setString(3, Email_Address);
			stmt.setString(4, City);
			stmt.setString(5, State);
			stmt.setString(6, Region);
			stmt.setString(7, Pincode);
			stmt.setString(8, PassportNumber);
			stmt.setString(9, Country);
			
			stmt.setDouble(10, Actual_bal);
			stmt.setString(11, Policy);
			stmt.setDouble(12, First_Installment);
			int result = stmt.executeUpdate();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<b>"+result+"Travel Policy Buy");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	

}