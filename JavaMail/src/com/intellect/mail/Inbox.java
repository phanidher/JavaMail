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

/**
 * Servlet implementation class Inbox
 */
@WebServlet("/Inbox")
public class Inbox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter(); 
		HttpSession session=request.getSession(false);
		//if(session!=null){ 
		if(true) {
		RequestDispatcher rd=request.getRequestDispatcher("HeaderHtmlServlet");
		rd.include(request,response);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"lenderApp.css\"> ");
		out.println("<script src=\"https://use.fontawesome.com/ff8702c92a.js\"></script>");
		out.println("<style>");
		out.println("#Loan_Details {margin-top: 150px;margin-left: 350px;}");
		out.println("#Loan_Details table{border: 1px solid black;}");
		out.println("#Loan_Details td{border: 1px solid black;padding: 4px;text-align: center;}");
		out.println("#Loan_Details th{ background-color: CornflowerBlue;border: 1px solid black;padding: 4px;text-align: center;}");
		out.println("#Loan_Details form{margin-left: 62%;padding-bottom: 20px;}");
		out.println("#Loan_Details h2{margin-left: 27%;}");
		out.println("#error h4{text-align: center;color:red;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"Loan_Details\">");
		out.println("<h2><u>Loan Details</u></h2>");
		out.println("<form> ");
		out.println("<a href=\"AddLoanHtmlServlet\"><i class=\"fa fa-plus\" aria-hidden=\"true\"></i></a>");
		out.println("</form>");
			String spageid=request.getParameter("page"); 
			//int pageid=Integer.parseInt(spageid);
			int pageid=1;
			int total=5;  
			if(pageid==1){}  
			else{  
				pageid=pageid-1;  
				pageid=pageid*total+1;  
			}  
			out.print("<table>");  
			out.print("<tr><th>Borrowing Date</th><th>Borrower Name</th><th>Principal Amount</th><th>Rate Of Intrest</th><th>Total Due Amount</th><th>Action</th><tr>");  
			/*for(BorrowerDetails m:list){  
				Logic l = new Logic();
				out.print("<tr><td>"+m.getLoan().getBorrowingDate()+"</td><td>"+m.getBorrowerName()+"</td> <td>"+m.getLoan().getPrincipalAmount()+"</td><td>"+m.getLoan().getRateOfInterest()+"</td><td>"+l.getTotalDueAmount(m)+"</td><td><a href='ViewLoanHtmlServlet?page=1&id="+m.getBorrower_id()+"'><i class='fa fa-eye' aria-hidden='true'></i></a>&nbsp&nbsp <a href='CloseLoanHtmlServlet?page=1&id="+m.getBorrower_id()+"'><i class=\"fa fa-times\" aria-hidden=\"true\"></i></a></td></tr>");
			}*/ 
		out.print("</table>");
		out.println("</div>");
		int itemCount = 8;
		int noOfRows = 5;
		float pageCount = (float)itemCount/noOfRows; 
		int pageNo= (int) Math.ceil(pageCount);
		out.println("<div id=\"Pagination\">");
		for(int i=1;i<=pageNo;i++){
		
		out.print("<a href='LoanDetailsServlet?page="+i+"'>"+i+"</a> ");  
		}
		out.println("</div>");
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
		response.sendRedirect("index.html");   
    }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
