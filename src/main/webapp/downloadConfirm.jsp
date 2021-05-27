<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FileSafe | Confirm File Download</title>
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
  </div>
</nav>
<body>
 <div class="container">
<%	String fileid = request.getParameter("filename"); System.out.println(fileid);%>
<h3>Enter Password to download file</h3>
<form  method="post" action="verifyDownload">
<input type="hidden" name="id" value="<%=fileid %>">
File password:<br>
<input type="password"  class="form-control" name="file_pass" placeholder="enter new password" required>
<br>
<br>
<input type="submit"  class="btn btn-primary" value="submit">
</form>
</div>
</body>
</html>