package Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	
    private String dbURL = "jdbc:mysql://localhost:3306/filesafe";
    private String dbUser = "root";
    private String dbPass = "root";
    
protected void doPost(HttpServletRequest req,HttpServletResponse res) throws UnsupportedEncodingException {
	 ResultSet oldUser = null;
	PrintWriter pw = null;
	
	try {
		pw = res.getWriter();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	res.setContentType("text/html");
	
	String userid = req.getParameter("userid");
	String pass = req.getParameter("pass");
	String count = "";
	String usrpass = "";
	//database check user pass
	try {
	      DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
          Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
          
          String query = "SELECT password from users WHERE userid = ? ;";
          PreparedStatement statement = conn.prepareStatement(query);
          statement.setString(1,userid);
          String query2 = "SELECT COUNT(FILE_NAME) FROM attachment WHERE userid = ?;";
          PreparedStatement statement2 = conn.prepareStatement(query2);
          statement2.setString(1,userid);
         
          oldUser = statement2.executeQuery();
          ResultSet rs = statement.executeQuery();
			// Step 4: Process the ResultSet object.
          while (rs.next()) {
				usrpass = rs.getString("password");
          }
          while (oldUser.next()) {
        	 count = oldUser.getString(1);
          }
	}catch(Exception e){
		e.printStackTrace();
	}
	String hashtext="";
	MessageDigest m;
	try {
		m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(pass.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
	
		
	} catch (NoSuchAlgorithmException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
    
    
	if((hashtext.equals(usrpass))) {
		
		 try {
			 HttpSession session = req.getSession();
			 session.setAttribute("userid", userid);
			
			if(Integer.valueOf(count)>0) {
				getServletContext().getRequestDispatcher("/dashboard.jsp").forward(req,res);
			}
			else{getServletContext().getRequestDispatcher("/FileUpload.jsp").forward(req,res);}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else {
		pw.println("wrong");
	}
	pw.close();
}
}
