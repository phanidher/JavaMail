package com.intellect.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ViewMail")
public class ViewMail extends HttpServlet {
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
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"lenderApp.css\"> ");
		out.println("<style>");
		out.println("#addBorrower { margin-left:-5px; margin-right:-5px; margin-top:125px; }");
		out.println("#addBorrower{ text-align: center;}");
		out.println("#borrowerDetails{ box-sizing: border-box;}");
		out.println("#LoanDetails {box-sizing: border-box;} ");
		out.println("#borrowerDetails {float: left; width: 50%; padding: 5px; padding-left: 100px;}");
		out.println("#LoanDetails {float: left; width: 50%; padding: 5px;}");
		out.println("#addBorrower::after {content: ''; clear: both; display: table;}");
		out.println("#addBorrower table {border-collapse: collapse; border-spacing: 0; width: 70%; border: 1px solid #ddd;}");
		out.println("#addBorrower th, td {text-align: left; padding: 10px;}");
		out.println("#borrowerDetails input {border-radius: 5px; border: 2px solid black; width: 200px; height: 30px;}");
		out.println("#LoanDetails input {border-radius: 5px; border: 2px solid black; padding: 20px; width: 150px; height: 20px;}");
		out.println("#addBorrower caption{text-align: left;}");
		out.println("#error h4{text-align: center;color:red;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		String sid=request.getParameter("id");  
		 int id=Integer.parseInt(sid); 
		 		String spageid=request.getParameter("page"); 
				int pageid=Integer.parseInt(spageid);  
				int total=5;  
				if(pageid==1){}  
				else{  
					pageid=pageid-1;  
					pageid=pageid*total+1;  
				} 
		out.println("<div id=\"addBorrower\">");
		out.println("<h2><u>View Borrower Form</u></h2>");
		out.println("<div id=\"borrowerDetails\">");
		out.println("<table>");
		out.println("<caption><h3>Borrower Details<h3></caption>");
		//Logic logic = new Logic();
		
		out.println("<tr>");
		out.println("<td><span>Borrower Name:</span></td>");
		out.println("<td>"+12+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Age</td>");
		out.println("<td>"+12+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Gender</td>");
		out.println("<td>"+12+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Mobile.no</td>");
		out.println("<td>"+12+"</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Aadhar no</td>");
		out.println("<td>"+12+"</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</div>");

	
			int itemCount = 2;
			int noOfRows = 5;
			float pageCount = (float)itemCount/noOfRows; 
			int pageNo= (int) Math.ceil(pageCount);
			for(int i=1;i<=pageNo;i++){
			
			out.print("<a href='ViewLoanHtmlServlet?page="+i+"&id="+1+"'>"+i+"</a> ");  
			}	
		out.println("</div>");
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
