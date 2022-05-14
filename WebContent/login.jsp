<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery.min.js"></script>
    <script src="Components/users.js"></script>
	<title>User Login</title>
	<style>
	.login-form {
		width: 340px;
    	margin: 50px auto;
	}
    .login-form form {
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .btn {        
        font-size: 15px;
        font-weight: bold;
    }
</style>
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <!-- Container wrapper -->
  <div class="container-fluid">
    <!-- Toggle button -->
    <button
      class="navbar-toggler"
      type="button"
      data-mdb-toggle="collapse"
      data-mdb-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

    <!-- Collapsible wrapper -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!-- Navbar brand -->
      <a class="navbar-brand mt-2 mt-lg-0" href="#">
        <img
          src="https://assets.justenergy.com/wp-content/uploads/2020/10/je-logo-1.png"
          height="30"
          alt="MDB Logo"
          loading="lazy"
        />
      </a>
      <!-- Left links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="#">Homepage</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Procuments</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Contact Us</a>
        </li>
      </ul>
      <!-- Left links -->
    </div>
    <!-- Collapsible wrapper -->

    <!-- Right elements -->
    <div class="d-flex align-items-center">
      <!-- Avatar -->
      <div class="dropdown">
		<a href="./profile">
          <img
            src="https://www.pinclipart.com/picdir/big/133-1332476_crowd-of-users-transparent-user-icon-png-clipart.png"
            class="rounded-circle"
            height="25"
            alt="Black and White Portrait of a Man"
            loading="lazy"
          />
        </a>  
      </div>
    </div>
    <!-- Right elements -->
  </div>
  <!-- Container wrapper -->
</nav>
<!-- Navbar -->
<section class="vh-100" >
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">
            <h3 class="mb-5">Sign in</h3>
            <div class="form-outline mb-4">
              <input type="email" id="txtUsername" name="txtUsername" class="form-control form-control-lg" placeholder="Email"/>
            </div>
            <div class="form-outline mb-4">
              <input type="password" id="txtPassword" name="txtPassword" class="form-control form-control-lg" placeholder="Password"/>
            </div><br>
            <button id="btnLogin" name="btnLogin" class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
          </div>
          <p class="text-center"><a href="./register.jsp">Create an Account</a></p>
        </div>
      </div>
    </div>
  </div>
</section>
</body>
</html>