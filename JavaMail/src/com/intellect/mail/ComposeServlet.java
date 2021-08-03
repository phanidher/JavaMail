package com.intellect.mail;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ComposeServlet")
public class ComposeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession(false);  
			if(session!=null){ 
		RequestDispatcher rd1=request.getRequestDispatcher("HeaderHtmlServlet");
		rd1.include(request,response);
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");	
		out.println("<style>");
		out.println("#addBorrower {  margin-top:75px; left : 7%; psition ;:relative}");
		out.println("#add{ text-align: center;}");
		out.println("#borrowerDetails{ box-sizing: border-box;}");
		out.println("#LoanDetails {box-sizing: border-box;} ");
		out.println("#borrowerDetails {float: left; width: 100%; padding: 5px;}");
		out.println(".leftblock { width: 60px; text-align: left;}");
		out.println(".rightblock input { width: 100% ;}");
		out.println(".btstyl { width: 70% ;margin: 0px auto; text-align : end;}");
		out.println("#LoanDetails {float: left; width: 50%; padding: 5px;}");
		out.println("#addBorrower::after {content: ''; clear: both; display: table;}");
		out.println("#addBorrower table {border-collapse: collapse; border-spacing: 0; margin: 0px auto; border: 1px solid #ddd; width: 70%;}");
		out.println("#addBorrower th, td {text-align: left; padding: 4px;}");
		//out.println("#borrowerDetails input {border-radius: 5px; border: 2px solid black; width: 100%; height: 30px;}");
		out.println("#LoanDetails input {border-radius: 5px; border: 2px solid black; padding: 20px; width: 175px; height: 20px;}");
		out.println("#addBorrower caption{text-align: left;}");
		out.println("#error h4{text-align: center;color:red;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form id=\"add\" action='SendMailServlet' method='POST'>");
		out.println("<div id=\"addBorrower\">");
		out.println("<div id=\"borrowerDetails\">");
		out.println("<table>");
		out.println("<caption><h3>Compose Mail<h3></caption>");
		out.println("<tr>");
		
		out.println("<td class ='leftblock'><span>To:</span></td>");
		out.println("<td class = 'rightblock'><input type='text' name='Recepient' required /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td class ='leftblock'><span>Cc:</span></td>");
		out.println("<td class = 'rightblock'><input type='text' name='Cc' /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td class ='leftblock'><span>Bcc:</span></td>");
		out.println("<td class = 'rightblock'><input type='text' name='Bcc' /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td class ='leftblock'><span>Subject:</span></td>");
		out.println("<td class = 'rightblock'><input type='text' name='Subject' /></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td class ='leftblock'><span>Message:</span></td>");
		out.println("<td class = 'rightblock'><input type='text' name='Message' /></td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		out.println("<div class='btstyl'>");
		out.println("<button type=\"submit\" form=\"add\" value=\"Submit\">Send</button >");
		//out.println("<button  type = 'button'><a href='LoanDetailsServlet?page=1' style='color : black; text-decoration :none;'>send</a></button>");
		out.println("<button  type = 'button'><a href='LoanDetailsServlet?page=1' style='color : black; text-decoration :none;'>Cancel</a></button>");
		out.println("</div>");
		
		out.println("</form>");
		String status = (String) session.getAttribute("status");
		if(status != null)
		{
			out.println("<div id=\"error\">");
			out.println("<h4>"+status+"</h4>");
			session.setAttribute("status",null);
			out.println("</div>");
		}
		out.println("</body>");
		out.println("</html>");
		
		RequestDispatcher rd3=request.getRequestDispatcher("FooterHtmlServlet");
		rd3.include(request,response);
		}  
        else{  
            out.print("Please login first");  
            request.getRequestDispatcher("index.html").include(request, response);  
        }
		out.close();
	}

}
