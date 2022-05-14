package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;
import model.User;

@WebServlet("/UsersAPI")
public class UsersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = new User();
       
    public UsersAPI() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = user.insertUser(request.getParameter("accountNo"), 
			   							request.getParameter("name"), 
			   							request.getParameter("address"), 
			   							request.getParameter("NIC"),
			   							request.getParameter("email"),
			   							request.getParameter("phone"),
			   							request.getParameter("password"),
			   							request.getParameter("user_role")); 
		
		response.getWriter().write(output);

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map paras = getParasMap(request); 
		
		String output = user.updateUser(paras.get("hidUserIDSave").toString(), 
									   paras.get("name").toString(), 
									   paras.get("address").toString(), 
									   paras.get("NIC").toString(), 
									   paras.get("email").toString(),
									   paras.get("phone").toString()); 
		
		response.getWriter().write(output); 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map paras = getParasMap(request); 
		
		String output = user.deleteUser(paras.get("userId").toString()); 
		
		response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request){ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try{ 
		 Scanner scanner = new Scanner(request.getInputStream(),"UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 
		 for (String param : params){
			 String[] p = param.split("=");
			 map.put(p[0], p[1]); } 
		 
		 }catch (Exception e){ 
				
		 }
		
			return map; 
			
		}
}	