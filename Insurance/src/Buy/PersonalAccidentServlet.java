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


@WebServlet("/PersonalAccidentBuy")
public class PersonalAccidentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Connection con;
    PreparedStatement stmt;
	public void init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("connection....");
			con =DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6404878", "sql6404878", "A1mqd6VZR6");
			stmt =con.prepareStatement("insert into Personal_Accident_Policy(Full_Name, Gross, Amount_Insurance,Occupation,Marital_Status,Insured_Info,DOB,Disability,Actual_bal,Policy,First_Installment) values(?,?,?,?,?,?,?,?,?,?,?);");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		
		String Full_Name = request.getParameter("name");
		Double Gross = Double.parseDouble(request.getParameter("personal"));
		Double Amount_Insurance = Double.parseDouble(request.getParameter("ins"));
		String Occupation = request.getParameter("occu");
		String Marital_Status = request.getParameter("marriage");
		String Insured_Info = request.getParameter("ins");
		
	   
		String DOB= request.getParameter("date");
		
		String Disability = request.getParameter("dia");
		Double Actual_bal = Double.parseDouble(request.getParameter("amount"));
		String Policy = request.getParameter("policy");
		Double First_Installment = Double.parseDouble(request.getParameter("amount"));
		
		try {
			stmt.setString(1, Full_Name);
			stmt.setDouble(2, Gross);
			stmt.setDouble(3, Amount_Insurance);
			stmt.setString(4, Occupation);
			stmt.setString(5, Marital_Status);
			stmt.setString(6, Insured_Info);
			stmt.setString(7, DOB);
			stmt.setString(8, Disability);
			stmt.setDouble(9, Actual_bal);
			stmt.setString(10, Policy);
			stmt.setDouble(11, First_Installment);
			int result = stmt.executeUpdate();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<b>"+result+"Personal Accident Policy Buy");
			
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