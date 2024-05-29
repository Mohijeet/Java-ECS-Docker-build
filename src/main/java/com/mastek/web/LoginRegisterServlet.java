package com.mastek.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.*;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastek.bean.User;
import com.mastek.dao.ConnectionManager;
import com.mastek.dao.UserDao;


import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * Servlet implementation class LoginRegisterServlet
 */
@WebServlet("/LoginRegisterServlet")
public class LoginRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDao;
	
    public LoginRegisterServlet() throws ServletException{
		userDao = new UserDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String action = request.getParameter("action");

	        if (action.equals("reset")) {
	            String resetEmail = request.getParameter("resetEmail");
	            String newPassword = request.getParameter("newPassword");
	            String confirmPassword = request.getParameter("confirmPassword");

	            // Validate if newPassword and confirmPassword match
	         // Check if newPassword is not null before comparing
	            if (newPassword != null && !newPassword.equals(confirmPassword)) {
	                response.sendRedirect("ResetPass.jsp?error=Passwords do not match");
	                return;
	            }


	            // Generate OTP
	            String otp = generateOTP();

	            // Send OTP via email
	            if (sendOTP(resetEmail, otp)) {
	                // Store the OTP in session for verification
	                HttpSession session = request.getSession();
	                session.setAttribute("resetEmail", resetEmail);
	                session.setAttribute("otp", otp);

	                response.sendRedirect("ResetPass.jsp");
	            } else {
	                response.sendRedirect("LoginRegister.jsp?error=Failed to send OTP");
	            }
	        } else if (action.equals("verifyOTP")) {
	            HttpSession session = request.getSession();
	            String sessionOTP = (String) session.getAttribute("otp");

	            String enteredOTP = request.getParameter("otp");
	            if (enteredOTP.equals(sessionOTP)) {
	                String resetEmail = (String) session.getAttribute("resetEmail");
	                String newPassword = request.getParameter("newPassword");
	                resetPassword(resetEmail, newPassword);

	                response.sendRedirect("LoginRegister.jsp?success=Password reset successful");
	            } else {
	                response.sendRedirect("ResetPass.jsp?error=Invalid OTP");
	            }
	        } else if (action.equals("login")) {
	        	
	        	String email = request.getParameter("email");
	        	String originalPassword = request.getParameter("password");
				
	

	        	String query = "SELECT * FROM tbl_users WHERE email = ?";

	        	try {
	        	    PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query);
	        	    preparedStatement.setString(1, email);

	        	    ResultSet rs = preparedStatement.executeQuery();

	        	    if (rs.next()) {
	                    String passwordFromDB = rs.getString("password");

	                    if (originalPassword.equals(passwordFromDB)) {
	                        User user = new User();
	                        user.setUserId(rs.getInt("u_id"));
	                        user.setFirstName(rs.getString("first_name"));
	                        user.setLastName(rs.getString("last_name"));
	                        user.setMobileNumber(rs.getString("mobilenumber"));
	                        user.setEmail(rs.getString("email"));
	                        user.setRole(rs.getString("u_roles"));

	                        HttpSession session = request.getSession();
	                        session.setAttribute("userObj", user);
	                        session.setAttribute("userId", rs.getInt("u_id"));
	                        session.setAttribute("userRole", rs.getString("u_roles"));

	                        response.sendRedirect("index.jsp");
	                    }  else {
	        	            // Passwords don't match, authentication failed
	        	            response.sendRedirect("LoginRegister.jsp?error=1");
	        	        }
	        	    
	        	    } else {
	        	        // User not found
	        	        response.sendRedirect("LoginRegister.jsp?error=User not found");
	        	    }
	        	} catch (Exception e) {
	        	    e.printStackTrace();
	        	    response.sendRedirect("LoginRegister.jsp?error=Database error");
	        	}

			
			            
	        } else if (action.equals("register")) {
	        	
	        	
	            // Handle registration logic
	        	

				String fname= request.getParameter("fname");
				String lname= request.getParameter("lname");
				String mobile= request.getParameter("mobile");
				String email= request.getParameter("email");
				String password= request.getParameter("password");
				String roles= request.getParameter("roles");


	            // Check if email already exists
	            if (userExists(email)) {
	            	
					response.sendRedirect("LoginRegister.jsp?emailAlready=1");
	                
	            } else if(mobileNumberExists(mobile)) {
	            	
	            	response.sendRedirect("LoginRegister.jsp?mobileAlready=1");

	            	
	            }
	            else {
	                try {
	                	
						registerUser(fname,lname,mobile,email,password,roles);
						
					} catch (NoSuchAlgorithmException e) {
	        			response.sendRedirect("LoginRegister.jsp?error=Database error");

						e.printStackTrace();
					}
		                
		                response.sendRedirect("LoginRegister.jsp?registrationSuccessfull=1");
	            }
	        }
	        //logic for updating user details

	        
	
			}
	        
	      
			private boolean userExists(String email) {
				
			    String CHECK_USER_QUERY = "SELECT * FROM tbl_users WHERE email = ?";
			    
			    try {
			    	
			        Connection connection = ConnectionManager.getConnection();
			        
			        PreparedStatement checkUserStmt = connection.prepareStatement(CHECK_USER_QUERY);
			        checkUserStmt.setString(1, email);
			        
			        ResultSet rs = checkUserStmt.executeQuery();
			        
			        boolean userExists = rs.next();
			        
			        // Close resources
			        rs.close();
			        checkUserStmt.close();
			        connection.close();
			        
			        
			        return userExists;
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}
			
			private boolean mobileNumberExists(String mobile) {
			    String CHECK_USER_MOBILE = "SELECT * FROM tbl_users WHERE mobileNumber = ?";
			    
			    try {
			        Connection connection = ConnectionManager.getConnection();
			        PreparedStatement checkMobileStmt = connection.prepareStatement(CHECK_USER_MOBILE);
			        checkMobileStmt.setString(1, mobile);
			        
			        ResultSet rs = checkMobileStmt.executeQuery();
			        
			        boolean mobileExists = rs.next();
			        
			        // Close resources
			        rs.close();
			        checkMobileStmt.close();
			        connection.close();
			        
			        return mobileExists;
			        
			    } catch (SQLException e) {
			        e.printStackTrace();
			        return false;
			    }
			}


			
			 private void registerUser(String fname, String lname, String mobile, String email, String password, String roles) throws NoSuchAlgorithmException {
			    // Implement your logic to register a new user
			    // For example, add user to a database
			
				
				 try {
			            User newUser = new User(fname, lname, mobile, email, password, roles);
			            userDao.insertUser(newUser);
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			    }
	

			

			    private String generateOTP() {
			        Random random = new Random();
			        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
			        return String.valueOf(otp);
			    }

			    private boolean sendOTP(String email, String otp) {
			        boolean flag = false;

			        Properties properties = new Properties();
			        properties.put("mail.smtp.auth", "true");
			        properties.put("mail.smtp.starttls.enable", "true");
			        properties.put("mail.smtp.host", "smtp.outlook.com");
			        properties.put("mail.smtp.port", "587");

			        String username = "mohijeet09@outlook.com";
			        String password = "Mohijeetsinh@6353";

			        Session session = Session.getInstance(properties, new Authenticator() {
			            @Override
			            protected PasswordAuthentication getPasswordAuthentication() {
			                return new PasswordAuthentication(username, password);
			            }
			        });

			        try {
			            Message message = new MimeMessage(session);
			            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			            message.setFrom(new InternetAddress("mohijeet09@outlook.com"));
			            message.setSubject("Your OTP for Password Reset");

			            String otpMessage = "Your OTP for password reset is: " + otp;

			            MimeBodyPart messageBodyPart = new MimeBodyPart();
			            messageBodyPart.setText(otpMessage);

			            MimeMultipart multipart = new MimeMultipart();
			            multipart.addBodyPart(messageBodyPart);

			            message.setContent(multipart);

			            Transport.send(message);
			            flag = true;
			            System.out.println("OTP sent successfully");
			        } catch (MessagingException e) {
			            e.printStackTrace();
			        }

			        return flag;
			    }


			    private void resetPassword(String email, String hashedPassword) {
			        // Update the password in the database
			        String UPDATE_PASSWORD_QUERY = "UPDATE tbl_users SET password = ? WHERE email = ?";
			        try (Connection connection = ConnectionManager.getConnection();
			                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_QUERY)) {
			            preparedStatement.setString(1, hashedPassword);
			            preparedStatement.setString(2, email);
			            preparedStatement.executeUpdate();
			        } catch (SQLException e) {
			            e.printStackTrace();
			            // Handle database error
			        }
			    }

			    private String generateRememberMeToken() {
			        // Generate a unique token (e.g., using UUID)
			        return UUID.randomUUID().toString();
			    }

}


