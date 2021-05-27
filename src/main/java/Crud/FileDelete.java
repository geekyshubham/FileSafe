package Crud;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

@WebServlet("/delete")
public class FileDelete extends HttpServlet {
 

    private static final int BUFFER_SIZE = 4096;   
     

    private String dbURL = "jdbc:mysql://localhost:3306/filesafe";
    private String dbUser = "root";
    private String dbPass = "root";
     
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    
    	HttpSession session = request.getSession();
        String userid =(String) session.getAttribute("userid");
        String filename = request.getParameter("filename");
        Connection conn = null; 
        try {
         
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // queries the database
            String sql = "DELETE FROM attachment WHERE userid = ? AND ID = ? ;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userid);
            statement.setString(2,filename);
            int result = statement.executeUpdate();
            if (result > 0){
            	response.sendRedirect("dashboard.jsp");      
            } else {
             response.getWriter().print("Something went wrong please try again later..., " + userid);  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.getWriter().print("SQL Error: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            response.getWriter().print("IO Error: " + ex.getMessage());
        } finally {
            if (conn != null) {
              
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }          
        }
    }
}
