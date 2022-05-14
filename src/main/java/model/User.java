package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.UserBean;
import util.DBConnection;
import util.UserValidation;

public class User {

	Connection con = null;

	public User() {
		con = DBConnection.connect();
	}

	//Insert
	public String insertUser(String accountNo, String name, String address, String NIC,
			String email, String phone, String password, String user_role) {
		
		System.out.println(NIC);
		String output = "";

		try {
			Connection con = DBConnection.connect();
			
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// create a prepared statement
			String query = "insert into user(`accountNo`,`name`,`address`,`NIC`,`email`,`phone`,`password`,`user_role`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			//binding values
			preparedStmt.setString(1, accountNo);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, NIC);
			preparedStmt.setString(5, email); 
			preparedStmt.setString(6, phone);
			preparedStmt.setString(7, password); 
			preparedStmt.setString(8, user_role);
		
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUserDetails(); 
			output = "{\"status\":\"success\",\"data\":\""+newUsers+"\"}"; 

		} catch (SQLException e) {
			output = "Please check the input values";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//Read all
	public String readUserDetails() {

		String output = "";

		try{
			Connection con = DBConnection.connect();
			if (con == null){
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Account Number</th>"
					+"<th>Name</th><th>Address</th><th>NIC</th>"
					+ "<th>Email</th>"
					+ "<th>Phone</th>"
					+ "<th>User Role</th>";
			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()){
				String userID = Integer.toString(rs.getInt("userID"));
				String accountNo = rs.getString("accountNo");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String NIC = rs.getString("NIC");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String user_role = rs.getString("user_role");

				// Add a row into the html table
				output += "<tr><td>" + accountNo + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + email + "</td>"; 
				output += "<td>" + phone + "</td>";
				output += "<td>" + user_role + "</td>";
				
				// buttons
				output +=  "<td><input name='btnUpdate' type='button' value='Update' ";
				output += "class='btnUpdate btn btn-warning' data-userid='" + userID + "'></td>";
				output += "<td><input name='btnRemove' type='button' value='Remove' ";
				output += "class='btnRemove btn btn-danger' data-userid='" + userID + "'></td>";
				output += "</tr>"; 		  
			}

			con.close();
			// Complete the html table
			output += "</table>";
		}

		catch (Exception e){
			output = "Error while reading the user details"
					+ ".";
			System.err.println(e.getMessage());
		}

		return output;
	}


	//Update Customer
	public String updateUser(String userId, String accountNo, String name, String address, String NIC, 
			String email, String phone, String password, String user_role){
		
	    String output = "";

	    try{
			Connection con = DBConnection.connect();
			
		   if (con == null){
			   return "Error while connecting to the database for updating"; 
		   }
		   // create a prepared statement
		   String query = "UPDATE user SET accountNo=?,name=?,address=?,NIC=?,email=?,phone=?, password=?, user_role=? WHERE userId=?";
		   PreparedStatement preparedStmt = con.prepareStatement(query);
			
		   // binding values
			preparedStmt.setString(1, accountNo);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, NIC);
			preparedStmt.setString(5, email); 
			preparedStmt.setString(6, phone);
			preparedStmt.setString(7, password); 
			preparedStmt.setString(8, user_role);
			preparedStmt.setInt(9, Integer.parseInt(userId));
			
		    // execute the statement		   
		    preparedStmt.executeUpdate();

		    con.close();
		    
		    String newUsers = readUserDetails(); 
			output = "{\"status\":\"success\",\"data\":\""+newUsers+"\"}"; 
		   
		}
	    catch (Exception e){
	    	output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}"; 
			System.err.println(e.getMessage());
		}

	    return output;
	}
				
	//Delete 
	public String deleteUser(String UserID){
		
		String output = "";

		try {
			Connection con = DBConnection.connect();
			if (con == null){
				 return "Error while connecting to the database for deleting."; 
			}
			 
			// create a prepared statement
			String query = "delete from user where userId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(UserID));
			
			// execute the statement
			preparedStmt.executeUpdate();

			con.close();
		    String newUsers = readUserDetails(); 
			output = "{\"status\":\"success\",\"data\":\""+newUsers+"\"}"; 
			 
		}

		 catch (Exception e){
		    	output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}"; 
				System.err.println(e.getMessage());
		}
		
		 return output;
	}

	//login
	public String login(String email, String password) {
		
		UserBean userBean = new UserBean();
		
		String output = " ";
		
		try {
			Connection con = DBConnection.connect();
			
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "SELECT * FROM user WHERE email=? and password=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, userBean.getEmail());
			preparedStmt.setString(2, userBean.getPassword());
			ResultSet results = preparedStmt.executeQuery();
			
			if (!results.isBeforeFirst() ) {        
			    output = "Invalid User Credentials!";
			}

			// iterate through the rows in the result set
			while (results.next()) {
				output = "Valid User, Welcome "+results.getString("name")+" , User Role: "+results.getString("user_role");				   
			}

		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}


}