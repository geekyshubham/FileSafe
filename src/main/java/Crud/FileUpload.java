package Crud;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;
 
@WebServlet("/upload")
@MultipartConfig(maxFileSize = 16177215)    
public class FileUpload extends HttpServlet {
    
    // database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/filesafe";
    private String dbUser = "root";
    private String dbPass = "root";
    
    private String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
    
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
    	
    	HttpSession session = request.getSession();
    	String userid =(String) session.getAttribute("userid");
    	String fileName ="";
        String password = request.getParameter("password");
         String desc = request.getParameter("desc");
       /** private static final String INSERT_FILES_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
    			+ " (?, ?, ?);";

    	private static final String SELECT_FILES_BY_ID = "select id,name,email,country from users where id =?";
    	private static final String SELECT_ALL_FILES = "select * from users";
    	private static final String DELETE_FILES_SQL = "delete from users where id = ?;";
    	private static final String UPDATEFILES_SQL = "update users set name = ?,email= ?, country =? where id = ?;"; **/
      
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("file");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            
           fileName = extractFileName(filePart);
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
        	
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO attachment (FILE_NAME	, FILE_PASSWORD	, FILE_DATA , DESCRIPTION , userid ) values (?, MD5(?), ? , ? ,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,fileName);
            statement.setString(2,password);
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(3, inputStream);
            }
            
            if(desc != null)
            {
            	statement.setString(4,desc);
            }
            else {
            	statement.setString(4,"undefined");
            }
            
            statement.setString(5,userid);
            
            System.out.println(userid);
            System.out.println(password);
            System.out.println(desc);
            
            // sends the statement to the database server
            
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded successfully!";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        }
        
    }
}
