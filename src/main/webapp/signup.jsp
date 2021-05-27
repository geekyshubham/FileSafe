<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FileSafe | Sign Up</title>

</head> <link href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/cyborg/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"><script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>

<div class="sidenav">
         <div class="login-main-text">
            <h2>FILE SAFE<br><b>SIGN UP</b></h2>
            <p>Register now and Start sharing your files.</p>
         </div>
      </div>
      <div class="main">
         <div class="col-md-6 col-sm-12">
            <div class="login-form">
               <form action="signup" method="POST">
                  <div class="form-group">
                     <label>USER ID</label>
                     <input type="text" id="usr_id" name="userid" class="form-control" placeholder="User ID">
                  </div>
                 
                  <div class="form-group">
                     <label for="username">User Name</label>
    <input type="text" id="usr_name" name="username" class="form-control" placeholder="Username">
                  </div>
                   <div class="form-group">
                     <label>Password</label>
                     <input type="password" id="usr_pass" name="pass" class="form-control" placeholder="Password">
                  </div>
                    <div class="form-group">
                     <label> Confirm Password</label>
                    <input type="password" id="cfr_pass" class="form-control" placeholder="Confirm Password">
                  </div>
                  
                  <button type="submit" class="btn btn-dark">Sign Up</button>
               </form>
            </div>
         </div>
      </div>


 
</body>
</html>