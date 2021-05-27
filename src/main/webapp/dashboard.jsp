
<%@page import="com.servlet.db.DB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FILESAFE | DASHBOARD</title>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
        
        <style>
            tr,td,th{
                padding: 10px;
                text-align: center;
            }
        </style>

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

<%-- main --%>
    <body>
        <br><br><br>

        <%!
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
        %>
        <table class="table table-hover">
            <tr class="table-dark">
                <th>File Name</th><th>File Description</th><th>EDIT</th><th>DOWNLOAD</th><th>DELETE</th><th>Share Link</th>
            </tr>
            <%
            String userid =(String) session.getAttribute("userid");
            con = DB.getConnection();
            String sql = "select * from attachment where userid= ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,userid);
            rs = ps.executeQuery();
            while (rs.next()) {
            %>
            <tr class="table-light">
                <td><%=rs.getString("FILE_NAME")%></td>
                <td><%=rs.getString("DESCRIPTION")%></td>
                <td><a class="btn btn-primary" href="edit.jsp?filename=<%=rs.getString("ID")%>">Edit</a>   </td>        
                <td><a class="btn btn-success" href="download?filename=<%=rs.getString("ID")%>">Download</a></td>
                <td><a class="btn btn-danger" href="delete?filename=<%=rs.getString("ID")%>">Delete</a></td>
                <td><input class="form-control" type="text" value="http://localhost:8080/FileSafe/downloadConfirm.jsp?filename=<%=rs.getString("ID")%>" id="<%=rs.getString("ID")%>" readonly><button class="btn btn-outline-dark" onclick="copyLink(<%=rs.getString("ID")%>)">Copy Link</button></td>
            </tr>
            <%
                }
            %>
            
        </table><br>
        

    </body>
</html>