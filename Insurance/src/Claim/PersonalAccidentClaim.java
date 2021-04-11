package Claim;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersonalAccidentClaim
 */
@WebServlet("/PersonalAccidentClaim")
public class PersonalAccidentClaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement stmt;
    public void init()
    {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Connecting data base please wait.");
    		con = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6404878", "sql6404878", "A1mqd6VZR6");
    		System.out.println("connection");
			//stmt=con.prepareStatement("insert into Travel_Policy(Insured_Last_Name ,First_Name ,Email ,City ,State ,Region ,Pincode ,Passport_Number , Country ,Actual_bal ,Policy ,First_Installment ) values(?,?,?,?,?,?,?,?,?,?,?,?);");
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Policy_Number = Integer.parseInt(request.getParameter("Registration_id"));
		Double Amount = Double.parseDouble(request.getParameter("amount"));

		try {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			//out.print(Amount+" "+Policy_Number);
			String stm= "select Actual_bal from Personal_Accident_Policy where Personal_Policy_ID = '"+Policy_Number+"'";
			Statement s1 = con.createStatement();
			ResultSet r = s1.executeQuery(stm);
			double res = 0;
			if(r.next())
			{
				//out.print(" actual: "+r.getDouble(1)+" "+Amount);
				res=  r.getDouble(1) - Amount;
			}
			
			stmt=con.prepareStatement("update Personal_Accident_Policy set Actual_bal='"+res+"' where Personal_Policy_ID = '"+Policy_Number+"'");
			
			Statement s2 = con.createStatement();
			int result = stmt.executeUpdate();
			
		
			out.print("<b>"+result +" Your claim is done "+"<br/><br/>"+"Thanks for choosing Personal Accident Policy."); 
			String stm2= "select * from Personal_Accident_Policy where Personal_Policy_ID = '"+Policy_Number+"'";
			Statement s3 = con.createStatement();
			ResultSet rs = s3.executeQuery(stm2);
			out.print("<br/><br/><br/><br/>---------------------------------User Details---------------------------------");
			while(rs.next())
			{
				out.print("<br/>Personal_Policy_ID : "+rs.getInt(1)+"<br/>Full_Name : "+rs.getString(2)+"<br/>Gross : "+rs.getDouble(3)+"<br/>Amount_Insurance "+rs.getDouble(4)
				+"<br/>Occupation : "+rs.getString(5)+"<br/>Marital_Status : "+rs.getString(6)+"<br/>Insured_Info :  "+rs.getString(7)+"<br/>DOB : "+rs.getString(8)
				+"<br/>Disability : "+rs.getString(9)+"<br/>Actual_bal : "+rs.getDouble(10)+"<br/>Policy : "+rs.getString(11)
				+"<br/>First_Installment : "+rs.getDouble(12));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
