package Crud;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.servlet.db.DB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

@WebServlet("/update")
public class FileUpdate extends HttpServlet{
	
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
   
    	HttpSession session = request.getSession();
        String userid =(String) session.getAttribute("userid");
        String filename = request.getParameter("file_name");
        String filedesc= request.getParameter("file_desc");
        String filepass = request.getParameter("file_pass");
        String id = request.getParameter("id");
        
        Connection conn = null; 
         
        try {
            // connects to the database
           /**
            * UPDATE `attachment` SET `ID`=[value-1],`FILE_NAME`=[value-2],`FILE_DATA`=[value-3],`DESCRIPTION`=[value-4],`FILE_PASSWORD`=[value-5],`userid`=[value-6] WHERE 1
            */
            conn = DB.getConnection();
 
           
            String sql = "UPDATE attachment SET FILE_NAME= ? , DESCRIPTION = ? , FILE_PASSWORD = MD5(?) WHERE userid = ? AND ID = ? ;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, filename);
            statement.setString(2,filedesc);
            statement.setString(3,filepass);
            statement.setString(4,userid);
            statement.setString(5,id);
            
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
