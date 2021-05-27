package Crud;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javax.servlet.GenericServlet ;

@WebServlet("/verifyDownload")
public class ProtectedFileDownload  extends HttpServlet {

    private static final int BUFFER_SIZE = 4096;   
     
    // database connection settings
    private String dbURL = "jdbc:mysql://localhost:3306/filesafe";
    private String dbUser = "root";
    private String dbPass = "root";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String filename = request.getParameter("id");
        String pass = request.getParameter("file_pass");
        Connection conn = null; 
        
        String hashtext="";
    	MessageDigest m;
    	try {
    		m = MessageDigest.getInstance("MD5");
    		m.reset();
    		m.update(pass.getBytes());
    		byte[] digest = m.digest();
    		BigInteger bigInt = new BigInteger(1,digest);
    		hashtext = bigInt.toString(16);
    	
    		while(hashtext.length() < 32 ){
    		  hashtext = "0"+hashtext;
    		}
    	
    		
    	} catch (NoSuchAlgorithmException e1) {

    		e1.printStackTrace();
    	}
    	
    		
         
        try {
          
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
     
            String sql = "SELECT FILE_NAME, FILE_DATA FROM attachment WHERE FILE_PASSWORD = ? AND ID = ? ;";
            PreparedStatement statement = conn.prepareStatement(sql);
            System.out.println("user:"+filename+" pass:"+hashtext);
            statement.setString(1, hashtext);
            statement.setString(2,filename);
            ResultSet result = statement.executeQuery();
            
            
            
            if (result.next()) {
            
                String fileName = result.getString("FILE_NAME");
                Blob blob = result.getBlob("FILE_DATA");
                InputStream inputStream = blob.getBinaryStream();
                int fileLength = inputStream.available();
                 
                System.out.println("fileLength = " + fileLength);
 
                ServletContext context = getServletContext();
 
      
                String mimeType = context.getMimeType(fileName);
                if (mimeType == null) {        
                    mimeType = "application/octet-stream";
                }              
                 
    
                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                response.setHeader(headerKey, headerValue);
 

                OutputStream outStream = response.getOutputStream();
                 
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                 
                inputStream.close();
                outStream.close();             
            } else {

                response.getWriter().print("File not found for the id: " + filename);  
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
