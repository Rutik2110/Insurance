package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement stmt;
	
	public void init(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6404878", "sql6404878", "A1mqd6VZR6");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("em");
		String password = request.getParameter("ps");
		try {
			stmt = con.prepareStatement("select * from users where email='"+email+"' and password='"+password+"';");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				response.sendRedirect("Home.html");
			} else {
				response.sendRedirect("signup.html");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
	}

}
