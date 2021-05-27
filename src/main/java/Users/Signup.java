package Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup")
public class Signup extends HttpServlet {
	 	private String dbURL = "jdbc:mysql://localhost:3306/filesafe";
	    private String dbUser = "root";
	    private String dbPass = "root";
	    

	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		String userid = req.getParameter("userid");
		String username = req.getParameter("username");
		String pass = req.getParameter("pass");
		
		PrintWriter out = res.getWriter();
		
		Connection conn=null;
		
		String success = "";
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn=DriverManager.getConnection(dbURL,dbUser,dbPass);
			String sql = "INSERT INTO users (userid,username,password) values (?,?,MD5(?))";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,userid);
			statement.setString(2,username);
			statement.setString(3,pass);
			
			int row = statement.executeUpdate();
			if (row > 0) success ="Welcome "+username+", You've successfully signed up !! Please Login to proceed." ;
			res.sendRedirect("index.jsp");
			
		}catch (SQLException e) {
			out.println(e.getMessage());
			e.printStackTrace();
			out.println("Please use another userID");
		}finally {
			if (conn != null) {
				try {
					conn.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
			}

}
