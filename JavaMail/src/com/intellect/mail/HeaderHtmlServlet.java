package com.intellect.mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HeaderHtmlServlet")
public class HeaderHtmlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();  
		HttpSession session=request.getSession(false);  
		if(session!=null){ 
		out.print("<html lang=\"en\">");  
		out.print("<head>");  
		out.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"Mail.css\"> ");  
		out.print("<script src=\"https://use.fontawesome.com/ff8702c92a.js\"></script>");  
		out.print("</head>");  
		out.print("<body>");  
		out.print("<div id=\"header\">");  
	 	out.print("<header>");  
		out.print("<img src = \"8fc37b74b608a622588fbaa361485f32.png\">");  
		out.print("<h1>MAIL IMPLEMENTATION</h1>");  
		out.print("<a href=\"LogoutServlet\"><i class=\"fa fa-sign-out\" aria-hidden=\"true\">&nbspLogout</i></a></button>");  
		out.print("</header>");  
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
