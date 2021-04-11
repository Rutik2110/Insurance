package Buy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Connector")
public class MotorPolicyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	private Connection con;
	private PreparedStatement stmt;
  
	public void init() {
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("connected1");
			con =DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6404878", "sql6404878", "A1mqd6VZR6");
			System.out.println("connected");
			stmt =con.prepareStatement("insert into Motor_Policy(Full_Name, Vehical_Registered, Renewal_Policy,State,Class,Model,Manufacturer,Body_Type,Make_Month,Actual_bal,Policy,First_Installment) values(?,?,?,?,?,?,?,?,?,?,?,?)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String full_Name = request.getParameter("name");
		String vehical_Registered = request.getParameter("vehi");
		String renewal_Policy = request.getParameter("rp");
		String state = request.getParameter("state");
		String class1 = request.getParameter("vc");
		String model = request.getParameter("model");
		String manufacturer = request.getParameter("manu");
		String body_Type = request.getParameter("body");
		String make_Month = request.getParameter("month");
		double actual_bal = Double.parseDouble(request.getParameter("amount"));
		String policy = request.getParameter("policy");
		double first_Installment = Double.parseDouble(request.getParameter("firstAmount"));
		try {
			stmt.setString(1, full_Name);
			stmt.setString(2, vehical_Registered);
			stmt.setString(3, renewal_Policy);
			stmt.setString(4, state);
			stmt.setString(5, class1);
			stmt.setString(6, model);
			stmt.setString(7, manufacturer);
			stmt.setString(8, body_Type);
			stmt.setString(9, make_Month);
			stmt.setDouble(10, actual_bal);
			stmt.setString(11, policy);
			stmt.setDouble(12, first_Installment);
			
			stmt.executeUpdate();
			System.out.println("executed");
			
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