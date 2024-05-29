package com.mastek.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mastek.bean.Appointment;
import com.mastek.dao.AppointmentDao;
import javax.servlet.http.HttpSession;


@WebServlet("/appointmentServlet")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse request parameters
    	
    	
        String appDateString = request.getParameter("appDate");
        Timestamp appDate = Timestamp.valueOf(appDateString + " 00:00:00"); // Append time part to match the required format

        int userId = Integer.parseInt(request.getParameter("userId"));
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        int propertyId = Integer.parseInt(request.getParameter("propertyId"));
       
        System.out.println("user id "+userId);
        System.out.println("agent id "+agentId);
        System.out.println("prop id "+propertyId);

        // Create Appointment object
        Appointment appointment = new Appointment();
        appointment.setAppDate(appDate);
        appointment.setUserId(userId);
        appointment.setAgentId(agentId);
        appointment.setProperty_id_fk(propertyId);

        // Pass Appointment object to


        // Pass Appointment object to AppointmentDao
        AppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.saveAppointment(appointment);
       
        // Redirect or forward to a success page
        response.sendRedirect("index.jsp");
    }
    
 
    
    // Get the userId attribute from the session
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	
    	
    	Integer userId = (Integer) session.getAttribute("userId");
    	String role = (String) session.getAttribute("userRole");

        AppointmentDao appointmentDao = new AppointmentDao();
     
        List<Appointment> appointments = appointmentDao.getAppointmentsByUserId(userId,role);
        request.setAttribute("appointments", appointments);
        System.out.println(appointments.toString());

        request.getRequestDispatcher("Appointmentlist.jsp").forward(request, response);
        }
}
