/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notearena.emailwebapp;

/**
 *
 * @author Sourav
 */
import java.io.*;  
import javax.servlet.ServletException;  
import javax.servlet.http.*; 
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*; 
  
public class SendEmail extends HttpServlet {  
@Override
public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
  
response.setContentType("text/html");  
PrintWriter out = response.getWriter();  
          
String name=request.getParameter("name");  
String toEmails=request.getParameter("email");  
String message=request.getParameter("message");  

String subject = "Test email";

try{  
    System.out.println("Data: \n "+"Name: "+name+" email: "+toEmails+" message: "+message); 
    sendFromGMail("youremail@gmail.com", "yourPassword", toEmails, subject, message);
    out.print("Message sent successfully");    
}catch (Exception e2) {
    System.out.println(e2);
    out.print("Message cannot send! Check setting"); 
}  
          
out.close();  
}

public void sendFromGMail(final String from,final String password,String to,String sub,String msg){  
          //Get properties object    
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(sub);    
           message.setText(msg);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    }  
  
}  
