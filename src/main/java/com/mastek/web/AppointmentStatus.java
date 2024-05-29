package com.mastek.web;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastek.bean.User;
import com.mastek.dao.AppointmentDao;
import com.mastek.dao.UserDao;
import com.mastek.services.MailServices;

/**
 * Servlet implementation class AppointmentStatus
 */
@WebServlet("/AppointmentStatus")
public class AppointmentStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AppointmentStatus() {
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
        	int tenantId = Integer.parseInt(request.getParameter("tenantId"));
        	int agentId = Integer.parseInt(request.getParameter("agentId"));
        	int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        	String appDate = request.getParameter("appDate");
        	String appStatus = request.getParameter("appStatus");

	        System.out.println("tenant ID: " + tenantId);
	        System.out.println("agent ID: " + agentId);
	        System.out.println("property ID: " + propertyId);
	        System.out.println("appDate : " + appDate);
	        System.out.println("appStatus : " + appStatus);
	        
	        HttpSession session = request.getSession();
			User user = (User) session.getAttribute("userObj");
			String landlordname =  user.getFirstName();
			String agentEmail = user.getEmail();
			
		
			int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
	        String status = request.getParameter("action");
	        
	        System.out.println("Appointment ID: " + appointmentId);
	        System.out.println("Status: " + status);
	        
	        AppointmentDao dao = new AppointmentDao();
	        
	       boolean isSuccess = dao.updateAppointmentStatus(appointmentId, status);
	        
	        if(isSuccess) {
	        	System.out.println("appointment updated successfully");
	        	
	        	
	        	if(status.equals("Approved")) {
	        		
	        		
	        		MailServices sendmail = new MailServices();
		        	
		        	
		        	
					UserDao userdao = new UserDao();
		        	User userDetails = userdao.selectUserById(tenantId);
		        	String tenantmail = userDetails.getEmail();
		        	String tenantName = userDetails.getFirstName();

		        	
		        	
		        	System.out.println("tenant mail:"+tenantmail);
		        	System.out.println("agent mail:"+agentEmail);
		        	System.out.println("Tenant name :"+tenantName);

		        	
		        	try {
		        		
						boolean isSend = sendmail.sendAppointmentStatusEmail(tenantmail,appDate, agentEmail,tenantName);
						if(isSend) System.out.println("mail sended");
						else System.out.println("mail send fail");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        	}else {
	        		System.out.println("Appointment rejected");
	        	}
	        	
	        	
	        		
	        }else {
	        	System.out.println("appointment update failed");
	        }

	        response.getWriter().write("<h1>App status</h1>");

	}

}
