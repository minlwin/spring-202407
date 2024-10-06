package com.jdc.spring.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Initialize Hello Servlet");
		var imagePath = getInitParameter("image-path");
		System.out.printf("Image Path : %s%n", imagePath);
		
		var contextParam = getServletContext().getInitParameter("common-wait");
		System.out.printf("Common Wait : %s%n", contextParam);
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var message = req.getParameter("message");
		
		resp.getWriter().append("""
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
			</head>
			<body>
			
				<h1>Response from Hello Servlet</h1>
				
				<p>Message : %s</p>
			
			</body>
			</html>""".formatted(message));
	}
}
