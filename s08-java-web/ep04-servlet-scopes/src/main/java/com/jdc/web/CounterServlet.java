package com.jdc.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Counter reqCounter = (Counter)req.getAttribute("counter");
		
		if(null == reqCounter) {
			reqCounter = new Counter();
			req.setAttribute("counter", reqCounter);
		}
		
		reqCounter.countUp();
		
		HttpSession session = req.getSession(true);
		Counter sesCounter = (Counter) session.getAttribute("counter");
		
		if(null == sesCounter) {
			sesCounter = new Counter();
			session.setAttribute("counter", sesCounter);
		}
		
		sesCounter.countUp();
		
		Counter appCounter = (Counter) getServletContext().getAttribute("counter");
		
		if(null == appCounter) {
			appCounter = new Counter();
			getServletContext().setAttribute("counter", appCounter);
		}
		
		appCounter.countUp();
		
		getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
	}

}
