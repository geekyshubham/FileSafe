

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="com.servlet.db.DB"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>FileSafe | Edit File</title>
 <link href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cyborg/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>

<%-- navbar --%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">FileSafe</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor02">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="dashboard.jsp">Home
            <%-- <span class="visually-hidden">(current)</span> --%>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="FileUpload.jsp">Upload Files</a>
        </li>
       
        <li class="nav-item">
          <a class="nav-link" href="about.jsp">About</a>
        </li>

      </ul>
     <div class="d-flex">
        <a class="btn btn-secondary my-2 my-sm-0" href="index.jsp">Logout</a>
      </div>
    </div>
  </div>
</nav>
<body>
 <%!
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
%>
        
<%
try{
	String userid =(String) session.getAttribute("userid");
	 String id = request.getParameter("filename");
	 System.out.println("receivd id: "+id);
	 con = DB.getConnection();
	 String sql = "select * from attachment where userid= ? AND ID = ?";
	 ps = con.prepareStatement(sql);
	 ps.setString(1,userid);
	 ps.setString(2,id);
	 rs = ps.executeQuery();
	 while(rs.next()){
%>
<div class="container">

<h3>Update File Details</h3>
<form method="post" action="update">
<input type="hidden" name="id" value="<%=rs.getString("id") %>">
<br>
File name:<br>
<input  class="form-control" type="text" name="file_name" value="<%=rs.getString("FILE_NAME") %>" required>
<br>
File Description:<br>
<input class="form-control" type="text" name="file_desc" value="<%=rs.getString("DESCRIPTION") %>" required>
<br>
File password:<br>
<input  class="form-control" type="password" name="file_pass" placeholder="enter new password" required>
<br>
<br>
<input  class="btn btn-primary" type="submit" value="Update">
</form>
</div>
<%
}
con.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</body>
</html>