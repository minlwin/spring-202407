package com.jdc.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/forward")
public class ForwardDemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("Forward Servlet");
		
		req.setAttribute("message", "Hello from Forward Servlet");
		getServletContext().getRequestDispatcher("/forwarded.jsp").forward(req, resp);
	}

}
