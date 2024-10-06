package com.jdc.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/include")
public class IncludeDemoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().append("""
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>""");
		
		var disp = getServletContext().getNamedDispatcher("bootstrap");
		disp.include(req, resp);
		
		resp.getWriter().append("""
			</head>
			<body>
			
				<div class="container mt-4">""");
		
		resp.getWriter().append("<h3>This is Include Demo Servlet</h3>");
		
		getServletContext().getNamedDispatcher("homebtn").include(req, resp);
		
		resp.getWriter().append("""
				</div>
			
			</body>
			</html>""");
	}
}
