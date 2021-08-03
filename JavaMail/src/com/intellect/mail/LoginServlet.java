package com.intellect.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String username=request.getParameter("username");
		String password=request.getParameter("userpass");
		
		HttpSession httpSession=request.getSession(); 
		if(username!=null && password!=null) {
		httpSession.setAttribute("name",username);  
		httpSession.setAttribute("pswrd",password);
		}
		String loginId = (String) httpSession.getAttribute("name");
		String loginPassword = (String) httpSession.getAttribute("pswrd");
		
		
		RequestDispatcher rd=request.getRequestDispatcher("HeaderHtmlServlet");
		rd.include(request,response);
    	System.out.println(loginPassword);
		// for IMAP
        String protocol = "imap";
        String host = "imap.gmail.com";
        String port = "993";
 
        InboxProperties classInbox = new InboxProperties();
        Properties properties = classInbox.getServerProperties(protocol, host, port);
        
        Session session = Session.getDefaultInstance(properties);
 
        try {
        	
            Store store = session.getStore(protocol);
            store.connect(loginId, loginPassword);
 
            
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);
 
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
    		out.println("<h2><u>Inbox</u></h2>");
    		out.println("<form> ");
    		out.println("<a href=\"ComposeServlet\"><i class=\"fa fa-plus\" aria-hidden=\"true\"></i></a>");
    		out.println("</form>");
    			//String spageid=request.getParameter("page"); 
    			int pageid = 1;
    			String spageid=request.getParameter("page"); 
    			if(spageid != null) {
    				pageid = Integer.parseInt(spageid);
    			}
    			int endMail = 5*pageid;
    			int startingMail = endMail-5;
    			
    			System.out.println("startingMail : # "+startingMail);
    			System.out.println("endMail : # "+endMail);
    			/*if(spageid==1) {
    			}else {
    				pageid = Integer.parseInt(spageid);
    			}
    			//int pageid=Integer.parseInt(spageid);
    			//int pageid=1;
    			int total=5;  
    			if(pageid==1){}  
    			else{  
    				//pageid=pageid-1;  
    				pageid=pageid*total+1; 
    				total = pageid+total;
    			}  */
    			out.print("<table>");  
    			out.print("<tr><th>From</th><th>Subject</th><th>Sent Date</th><th>Action</th><tr>");  
            Message[] messages = folderInbox.getMessages();
            System.out.println("\t messages length: " + messages.length);
            int pageId2 = 1;
            String spageid2=request.getParameter("page"); 
			if(spageid2 != null) {
				pageId2 = Integer.parseInt(spageid2);
			}
           
            int start = (messages.length-1) - (5*(pageId2-1));
            int end = (messages.length-1) - (5*(pageId2));
            System.out.println("startingMail : # "+start);
			System.out.println("endMail : # "+end);
            //for ( int i =pageid; pageid < messages.length; i=total) {
            for (int i = start; i > end&& i >= 0; i--) {
            	System.out.println("\t messages length after: " + messages.length);
                Message msg = messages[i];
                Address[] fromAddress = msg.getFrom();
                String from = fromAddress[0].toString();
                String subject = msg.getSubject();
                
                String toList = classInbox.parseAddresses(msg
                        .getRecipients(RecipientType.TO));
                String ccList = classInbox.parseAddresses(msg
                        .getRecipients(RecipientType.CC));
                String sentDate = msg.getSentDate().toString();
 
                String contentType = msg.getContentType();
                String messageContent = "";
 
                if (contentType.contains("text/plain")
                        || contentType.contains("text/html")) {
                    try {
                        Object content = msg.getContent();
                        if (content != null) {
                            messageContent = content.toString();
                        }
                    } catch (Exception ex) {
                        messageContent = "[Error downloading content]";
                        ex.printStackTrace();
                    }
                }
                
 
                System.out.println("Message #" + (i + 1) + ":");
                System.out.println("\t From: " + from);
                System.out.println("\t To: " + toList);
                System.out.println("\t CC: " + ccList);
                System.out.println("\t Subject: " + subject);
                System.out.println("\t Sent Date: " + sentDate);
                System.out.println("\t Message: " + messageContent);
              //  FetchMailUsingCodeJava messageDisplay = new FetchMailUsingCodeJava();
                System.out.println("\t Message: " + classInbox.getTextFromMessage(msg));
                
				out.print("<tr><td>"+from+"</td><td>"+subject+"</td><td>"+sentDate+"</td><td><a href='ViewMail?page=1&id="+1+"'><i class='fa fa-eye' aria-hidden='true'></i></a>&nbsp&nbsp <a href='ViewMail?page=1&id="+1+"'><i class=\"fa fa-times\" aria-hidden=\"true\"></i></a></td></tr>");

				
            }
            
            out.print("</table>");
			out.println("</div>");
			int itemCount = messages.length;
			int noOfRows = 5;
			float pageCount = (float)itemCount/noOfRows; 
			int pageNo= (int) Math.ceil(pageCount);
			out.println("<div id=\"Pagination\">");
			for(int j=1;j<=pageNo;j++){
			
			out.print("<a href='LoginServlet?page="+j+"'>"+j+"</a> ");  
			}
			out.println("</div>");
			/*String status = (String) session.getAttribute("status");
			if(status != null)
			{
				out.println("<div id=\"error\">");
				out.println("<h4>"+status+"</h4>");
				session.setAttribute("status",null);
				out.println("</div>");
			}*/
			out.println("</body>");
			out.println("</html>");
			RequestDispatcher rd3=request.getRequestDispatcher("FooterHtmlServlet");
			rd3.include(request,response);
 
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider for protocol: " + protocol);
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store");
            ex.printStackTrace();
        }
	
        
			/*for(BorrowerDetails m:list){  
				Logic l = new Logic();
				out.print("<tr><td>"+m.getLoan().getBorrowingDate()+"</td><td>"+m.getBorrowerName()+"</td> <td>"+m.getLoan().getPrincipalAmount()+"</td><td>"+m.getLoan().getRateOfInterest()+"</td><td>"+l.getTotalDueAmount(m)+"</td><td><a href='ViewLoanHtmlServlet?page=1&id="+m.getBorrower_id()+"'><i class='fa fa-eye' aria-hidden='true'></i></a>&nbsp&nbsp <a href='CloseLoanHtmlServlet?page=1&id="+m.getBorrower_id()+"'><i class=\"fa fa-times\" aria-hidden=\"true\"></i></a></td></tr>");
			}*/ 
		
	
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
