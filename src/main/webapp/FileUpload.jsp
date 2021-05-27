<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FileSafe | File Upload </title>
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
	<div class="container">
        <h3>Upload File</h3>
       
        <form method="post" action="upload" enctype="multipart/form-data">
            <table >
                <tr>
                    <td>File Description: </td>
                    <td><input  class="form-control" type="text" name="desc" size="50" required/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input  class="form-control" type="password" name="password" size="50" required/></td>
                </tr>
                <tr>
                    <td>Upload File</td>
                    <td><input  class="file-control" type="file" name="file" required/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input  class="btn btn-primary" type="submit" value="Upload">
                        
                 
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>