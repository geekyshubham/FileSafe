<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FileSafe | Login</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>

<div class="sidenav">
         <div class="login-main-text">
            <h2>FILE SAFE<br><b>LOGIN</b></h2>
            <p>Login or register from here to access.</p>
         </div>
      </div>
      <div class="main">
         <div class="col-md-6 col-sm-12">
            <div class="login-form">
               <form action="login" method="post">
                  <div class="form-group">
                     <label>USER ID</label>
                     <input type="text" id="usr_id" name="userid" class="form-control" placeholder="User ID">
                  </div>
                  <div class="form-group">
                     <label>Password</label>
                     <input type="password" id="usr_pass" name="pass" class="form-control" placeholder="Password">
                  </div>
                  <button type="submit" class="btn btn-dark ">Login</button>
                  <a href="signup.jsp" class="btn btn-secondary">Create New Account</a>
               </form>
            </div>
         </div>
      </div>
<form >
 
</body>
</html>