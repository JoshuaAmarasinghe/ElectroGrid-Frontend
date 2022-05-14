<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery.min.js"></script>
    <script src="Components/users.js"></script>
	<title>User Profile</title>
	<style>  
	.gradient-custom {
		/* fallback for old browsers */
		background: #f6d365;
		
		/* Chrome 10-25, Safari 5.1-6 */
		background: -webkit-linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1));
		
		/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
		background: linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1))
		}
	</style>
</head>
<%
if (session.getAttribute("Username") != null)
 {
 response.sendRedirect("items.jsp");
 }
%>
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
<section>
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
          <div class="card-body p-4 p-md-5 text-center">
            <h3 class="mb-5 ">User Account Managment</h3>
            <form id="formUser" name="formUser" method="POST" action="register.jsp">
              <div class="row">
                <div class="col-md-6 mb-4">
                  <div class="form-outline">
                    <input type="text" id="accountNo" name="accountNo" class="form-control form-control-lg" placeholder="Account Number" required/>
                  </div>
                </div>
                <div class="col-md-6 mb-4">
                  <div class="form-outline">
                    <input type="text" id="name" name="name" class="form-control form-control-lg" placeholder="Name" required/>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <div class="form-outline datepicker w-100">
                    <input type="text" id="address" name="address" class="form-control form-control-lg"  placeholder="Address" required/>
                  </div>
                </div>
                <div class="col-md-6 mb-4 d-flex align-items-center">
                  <div class="form-outline datepicker w-100">
                    <input type="text" id="NIC" name="NIC" class="form-control form-control-lg"  placeholder="NIC" required/>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6 mb-4 pb-2">

                  <div class="form-outline">
                    <input type="email" id="email" name="email" class="form-control form-control-lg" placeholder="Email" required/>
                  </div>
                </div>
                <div class="col-md-6 mb-4 pb-2">
                  <div class="form-outline">
                    <input type="tel" id="phone" name="phone" class="form-control form-control-lg" placeholder="Phone Number" required/>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-6 mb-4 pb-2">

                  <div class="form-outline">
                    <input type="password" id="password" name="password" class="form-control form-control-lg" placeholder="Password" required/>
                  </div>
                </div>
                <div class="col-md-6 mb-4 pb-2">
                  <div class="form-outline">
					  <input type="text" id="user_role" name="user_role" class="form-control form-control-lg" placeholder="User Role" required/>
                  </div>
                </div>
              </div>
              <div class="mt-4 pt-2">
                <input class="btn btn-primary btn-lg" type="button" id="btnSave" name="btnSave" type="button" value="Register" />
                <input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
              </div>
            </form>
          </div><br>
            <p class="text-center"><a href="./register.jsp">Register New Account</a></p>
        </div>
      </div>
    </div>
  </div>
</section>
<div id="alertSuccess" class="alert alert-success" ></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
				<div id="divUserGrid">
				 	<%
						User user = new User(); 
				 		out.print(user.readUserDetails()); 
				 	%>
			    </div>
</body>
</html>