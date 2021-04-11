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
 * Servlet implementation class HealthPolicy
 */
@WebServlet("/HealthPolicy")
public class HealthPolicyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement ps;
    public void init() {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6404878", "sql6404878", "A1mqd6VZR6");
			 ps = con.prepareStatement("insert into Health_policy(First_Name,Policy_Start_Date,Policy_End_Date,Cover_Required,Maternity_Exp_New_Born_Baby_cover,Pincode, Zone,DOB,Age,Gender, Marital_Status,Relation,Actual_bal,Policy,First_Installment) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			 System.out.println("connected......");
        }catch(ClassNotFoundException e){
        	e.printStackTrace();
        }catch(SQLException e)
        {
        	e.printStackTrace();
        }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String First_Name = request.getParameter("name");
		String Policy_Start_Date = request.getParameter("policy-start");
		String Policy_End_Date = request.getParameter("policy-end");
		String Cover_Required = request.getParameter("cover");
		String Maternity_Exp_New_Born_Baby_cover = request.getParameter("rate");
		String  Pincode = request.getParameter("pin");
		String  Zone = request.getParameter("zone");
		String DOB = request.getParameter("date");
		int Age = Integer.parseInt(request.getParameter("number"));
		String Gender = request.getParameter("gender");
		String Marital_Status = request.getParameter("Mstatus");
		 String Relation = request.getParameter("relation");
		 double Actual_bal =Double.parseDouble(request.getParameter("amount"));
		String Policy = request.getParameter("policy");
		 double First_Installment = Double.parseDouble(request.getParameter("finalamount"));
		 try {
			 ps.setString(1,First_Name);
			 ps.setString(2, Policy_Start_Date);
			 ps.setString(3, Policy_End_Date);
			 ps.setString(4, Cover_Required);
			 ps.setString(5, Maternity_Exp_New_Born_Baby_cover);
			 ps.setString(6,Pincode );
			 ps.setString(7, Zone);
			 ps.setString(8, DOB);
			 ps.setInt(9, Age);
			 ps.setString(10,Gender);
			 ps.setString(11,Marital_Status );
			 ps.setString(12,Relation);
			 ps.setDouble(13,Actual_bal);
			 ps.setString(14,Policy);
			 ps.setDouble(15, First_Installment);
			 int i=ps.executeUpdate();
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
				if(i>0)
				 out.print("You have successfully enter data......");
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
	}
	public void destroy() {
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}