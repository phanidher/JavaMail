package com.intellect.mail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SendMailServlet")
public class SendMailServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 
		HttpSession httpSession=request.getSession(false); 
			if(httpSession!=null){ 
				String ccMail = null;
				String recepient=request.getParameter("Recepient");
				ccMail=request.getParameter("Cc");
				System.out.println("ccMAil =="+ccMail);
				String bcc=request.getParameter("Bcc");
				String subject=request.getParameter("Subject");
				String body=request.getParameter("Message");
				
				
				
				
				System.out.println("Preparing to send mail");

				Properties properties =new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				
				/*Scanner scan = new Scanner(System.in);
				System.out.println("Enter login Address");
				String myAccountEmail = scan.nextLine();
				System.out.println("Enter password");
				String password = scan.nextLine();*/
				
				String myAccountEmail = (String) httpSession.getAttribute("name");
				String password = (String) httpSession.getAttribute("pswrd");
				
				System.out.println(myAccountEmail +" and "+ password);
				//String myAccountEmail = "phanidhert@gmail.com";
				//String password = "Phani@7813";
						
				Session session = Session.getInstance(properties, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(myAccountEmail, password);
					}
				});
				
				//Message message = prepareMessage(session,myAccountEmail,recepient,ccMail);
				
				
				Message message = new MimeMessage(session);
				try {
					message.setFrom(new InternetAddress(myAccountEmail));
					
					
					
					String recipients = recepient;
					String[] recipientList = recipients.split(",");
					InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
					int counter = 0;
					for (String recipient : recipientList) {
					    recipientAddress[counter] = new InternetAddress(recipient.trim());
					    counter++;
					}
					message.setRecipients(Message.RecipientType.TO, recipientAddress);
					
					
					String cc = ccMail;
					System.out.println("cc =="+cc);

//					if((cc!=null) && !(cc.equals(" "))) {
//					String[] ccList = cc.split(",");
//					System.out.println("ccList =="+ccList);
//
//					InternetAddress[] ccAddress = new InternetAddress[ccList.length];
//					int count = 0;
//					for (String ccMailAdd : ccList) {
//						ccAddress[count] = new InternetAddress(ccMailAdd.trim());
//						count++;
//					}
					
					message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(ccMail));
					//message.setRecipients(Message.RecipientType.CC, ccAddress);
					System.out.println("ccAddress =="+ccMail);

					//}
					message.setSubject(subject);
					message.setText(body);
//					Transport.send(message);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
				
				
				
					try {
						Transport.send(message);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				
				System.out.println("Mail sent successfully");
				
				response.sendRedirect("LoginServlet?page=1"); 
				
	        }else{  
	            out.print("Please login first");  
	            request.getRequestDispatcher("index.html").include(request, response);  
	        }
				
		out.close();
		}
	}


