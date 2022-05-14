package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;


@WebServlet("/AuthAPI")
public class AuthAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = new User();   

    public AuthAPI() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{
			 HttpSession session = request.getSession();
			 String authStatus = user.login(request.getParameter("txtUsername"),
			 request.getParameter("txtPassword"));
			 String output = "";
			if (authStatus.equals("success"))
			 {
			 output = "{\"status\":\"success\", \"data\": \"\"}";
			 session.setAttribute("Username",
			 request.getParameter("txtUsername"));
			 }
			else
			 {
			 output = "{\"status\":\"error\", \"data\": \"" + authStatus + "\"}";
			 }
			 response.getWriter().write(output);
			}
}
