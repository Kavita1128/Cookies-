package com.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String usn = req.getParameter("name"); //taking input from user
	String pwd = req.getParameter("password");
	
	if(usn.equalsIgnoreCase("admin")&& pwd.equalsIgnoreCase("admin123")) {
		Cookie userCookie = new Cookie("UserName",usn); //creating cookie and storing username and password
		Cookie passwordCookie = new Cookie("password",pwd);

		
		userCookie.setMaxAge(60*60*24);//✅ Setting Cookie Expiry
		passwordCookie.setMaxAge(60*60*24);
		
		resp.addCookie(userCookie);//✅ Sending Cookies to Browser
		resp.addCookie(passwordCookie);
		resp.sendRedirect("profile");//✅ Redirecting to Profile
	}
	else {
		resp.getWriter().println("<html> <body> <h1> Invalid Credentials</h1></body></html>");
		req.getRequestDispatcher("login.jsp").include(req, resp);
	}

}
}
