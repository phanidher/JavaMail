package com.intellect.mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FooterHtmlServlet")
public class FooterHtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);  
		if(session!=null){				
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"en\">");
		out.print("<head>");
		out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"LenderApp.css\"> ");
		out.print("<script src=\"https://use.fontawesome.com/ff8702c92a.js\"></script>");
		out.print("</head>");
		out.print("<body>");
		out.print("<div id=\"footer\">");
		out.print("<h3><i class=\"fa fa-copyright\" aria-hidden=\"true\"></i>&nbspintellectinfo</h3>");
		out.print("<span>Mobile No:9876543210<br>Reach Us:help@company.com</span>");
		out.print("</div>");
		out.print("</body>");
		out.print("</html>");
		}
		else{  
        out.print("Please login first");  
        request.getRequestDispatcher("index.html").include(request, response);  
    } 
	}

}
