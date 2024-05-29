package com.mastek.services;
import java.io.File;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class MailServices {
	
	
	public boolean sendEmail(String to, String agentmail, String tenantname,String subject, String attachmentPath) {
        boolean flag = false;

        //logic
        //smtp properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.outlook.com");
        properties.put("mail.smtp.port", "587");

//        String username = "propertylords12345@outlook.com";
//        String password = "mastek@1234";
        
        String username = "mohijeet09@outlook.com";
        String password = "Mohijeetsinh@6353";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
        	
        	Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress("mohijeet09@outlook.com"));
            message.setSubject(subject);

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String bydefaultmsg = "Dear "+tenantname 
            		+ ",\r\n"
            		+ "We are pleased to inform you that your rental application has been accepted! Congratulations on becoming a tenant in our property.\r\n"
            		+ "\r\n"
            		+ "As part of the leasing process, we have created the lease document for you to review and sign. Please find the attached lease document. Kindly review the terms and conditions carefully, and if everything looks good, please sign the document at your earliest convenience.\r\n"
            		+ "\r\n"
            		+ "Additionally, we would like to remind you that the first month's rent and deposit are due. You can make the payment physically by contacting your agent. Please ensure that the payment is made on or before the due date to avoid any delays in the leasing process.\r\n"
            		+ "\r\n"
            		+ "If you have any questions or need further assistance, please don't hesitate to contact us. We are here to help!\r\n"
            		+ "\r\n"
            		+ "Thank you for choosing to rent with us. We look forward to having you as our tenant.\r\n"
            		+ "\r\n"
            		+ "Best regards,\r\n"
            		+ "Property Lords The Real Estate Site\r\n"
            		+ "Your Agent Mail Address: \r\n"+ agentmail;
            messageBodyPart.setText(bydefaultmsg);

            // Create the attachment part
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));

            // Create Multipart
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Set the multipart message to the email message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            flag = true;
            System.out.println("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
	
	
	
	public boolean sendAppointmentStatusEmail(String to,String appDate, String agentmail, String tenantname) throws ParseException {
        boolean flag = false;
        
        String subject = "Confirmation of Approved Real Estate Property Viewing Appointment";
        
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime appDateTime = LocalDateTime.parse(appDate, inputFormatter);
        
        // Format the LocalDateTime to desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = appDateTime.format(outputFormatter);
        
        System.out.println("Original: " + appDate);
        System.out.println("Update date"+date);
        
        
        //logic

        
        
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.outlook.com");
        properties.put("mail.smtp.port", "587");

        String username = "mohijeet09@outlook.com";
        String password = "Mohijeetsinh@6353";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
        	
        	Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress("mohijeet09@outlook.com"));
            message.setSubject(subject);

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            String bydefaultmsg = "Dear "+tenantname 
            		+ ",\r\n"
            		+ "I hope this email finds you well."
            		+ "\r\n"
            		+ "I am writing to confirm the approved appointment for viewing a real estate property, as per your .\r\n"
            		+ "\r\n"
            		+ "email dated "+date+" I appreciate your prompt response and assistance in. \n arranging this viewing \r\n"
            		+ "\r\n"
            		+ "The details of the approved viewing appointment are as follows:\r\n"
            		+ "\r\n"
            		+ "Date : "+date
            		+ "\r\n"
            		+ "Time: Contact agent to confirm.\r\n"
            		+ "\r\n"
            		+ "Agent's Contact Email :"+agentmail+" \r\n"
            		+ "\r\n"

            		+ "I would like to express my gratitude for your cooperation in facilitating this viewing.\r\n Should there be any changes or if further information is required from my end, \r\n please do not hesitate to contact me via email or phone..\r\n"
            		+ "\r\n"
            		+ "Looking forward to the viewing and the opportunity to explore the property further. \r\n"
            		+ "\r\n"
            		+ "Best regards,\r\n"
            		+ "Property Lords The Real Estate Site\r\n"
            		+ "Your Agent Mail Address: \r\n"+ agentmail;
            messageBodyPart.setText(bydefaultmsg);
           

            // Create Multipart
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Set the multipart message to the email message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            flag = true;
            System.out.println("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }
	
	
}