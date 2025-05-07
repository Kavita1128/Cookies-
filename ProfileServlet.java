package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter printWriter = resp.getWriter();	
		// Get all cookies sent by the browser with this request
		Cookie[] cookies = req.getCookies();//getting the cookie from browser reads the data

        // Variables to store the values of cookies if found
		String usn = null; 
		String pwd = null;
		
		 // Check if cookies are not null (browser has sent some cookies)
		if(cookies != null) {
			for (Cookie cookie : cookies) { //iterating through each cookie 
				// Check if the cookie is named 'UserName' and get its value
				if(cookie.getName().equals("UserName")) {//this username should be the same as in login page data
					usn = cookie.getValue();
				}
				 // Check if the cookie is named 'password' and get its value
				if(cookie.getName().equals("password")) {
					pwd = cookie.getValue();
				}
			}
			//✅ 8. Check if User is Logged In
			if(usn != null && pwd !=null) { 
				//If both username and password were found in cookies:
				printWriter.println("<html><body>");
				printWriter.println("<h1>Now Currently  You Are In The profile Page</h1>");
				printWriter.println("<h1><a href = 'reels'>Reels </a></h1>");
				printWriter.println("<h1><a href = 'logout'>Logout </a></h1>");
			}
//			❌ 9. If Cookies are Missing (User is not logged in)
			else {
				printWriter.println("<html><body><h1> You have been logged out ");
				req.getRequestDispatcher("login.jsp").include(req, resp);
			}
		 }
	}
}
