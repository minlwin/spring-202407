package com.jdc.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
	urlPatterns = "/hello",
	loadOnStartup = 0,
	initParams = {
		@WebInitParam(name = "image-path", value = "/storage/image")	
	}
)
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		System.out.printf("""
				Initializing Hello Servlet
				----------------------------
				Servlet Param : %s
				Context Param : %s
				""",
				getInitParameter("image-path"),
				getServletContext().getInitParameter("max-wait"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.getWriter().append("""
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Hello Servlet</title>
			</head>
			<body>
			
			  <h3>Request Information</h3>	
			  
			  <ul>						
				""");
		
		resp.getWriter().append("<li>%s : %s</li>".formatted("getServletPath", req.getServletPath()));
		resp.getWriter().append("<li>%s : %s</li>".formatted("getContextPath", req.getContextPath()));
		
		var headers = req.getHeaderNames();
		while(headers.hasMoreElements()) {
			var header = headers.nextElement();
			resp.getWriter().append("<li>%s : %s</li>".formatted(header, req.getHeader(header)));
		}
		
		var params = req.getParameterNames();
		while(params.hasMoreElements()) {
			var param = params.nextElement();
			resp.getWriter().append("<li>%s : %s</li>".formatted(param, req.getParameter(param)));
		}
		
		
		resp.getWriter().append("""
			</ul>
		</body>
		</html>""");
	}
}
