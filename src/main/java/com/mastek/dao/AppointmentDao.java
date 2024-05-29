package com.mastek.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mastek.bean.Appointment;


public class AppointmentDao {

	
    private String sql = "INSERT INTO TBL_APPOINTMENTS (USER_ID, AGENT_ID, PROPERTY_ID_FK, APP_DATE, APP_STATUS) VALUES (?, ?, ?, ?, ?)";
    private String usersql = "SELECT * FROM TBL_APPOINTMENTS WHERE USER_ID = ?";
    private String agentsql = "SELECT * FROM TBL_APPOINTMENTS WHERE AGENT_ID = ? and APP_STATUS = 'Pending'";
    private String updateAppStatus = "UPDATE TBL_APPOINTMENTS SET APP_STATUS = ? WHERE APPOINTMENT_ID = ? ";
    
    Connection connection =null;
    PreparedStatement preparedStatement = null;

	public void saveAppointment(Appointment appointment) {

	    try {

	    	connection = ConnectionManager.getConnection();

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setInt(1, (int) appointment.getUserId());
	        preparedStatement.setInt(2, (int) appointment.getAgentId());
	        preparedStatement.setInt(3, appointment.getProperty_id_fk());
	        preparedStatement.setTimestamp(4, appointment.getAppDate());
	        preparedStatement.setString(5,"Pending");

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace(); // Handle or log exception as per your requirement
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle or log exception as per your requirement
	        }
	    }
	}
	
	
	public List<Appointment> getAppointmentsByUserId(int userId,String role) {
		
		
        List<Appointment> appointments = new ArrayList<>();
        ResultSet resultSet = null;

        
        try 
		{
	    	connection = ConnectionManager.getConnection();

			if(role.equals("agent") || role.equals("landlord"))
			{
				
				preparedStatement = connection.prepareStatement(agentsql);
				

			}
			else if(role.equals("tenant") || role.equals("user")) {

				preparedStatement = connection.prepareStatement(usersql);

		   }
      
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("APPOINTMENT_ID"));
                appointment.setUserId(resultSet.getInt("USER_ID"));

                appointment.setAgentId(resultSet.getInt("AGENT_ID"));
                appointment.setProperty_id_fk(resultSet.getInt("PROPERTY_ID_FK"));
                appointment.setAppDate(resultSet.getTimestamp("APP_DATE"));
                appointment.setAppStatus(resultSet.getString("APP_STATUS"));
                appointments.add(appointment);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
       
        }

        return appointments;
    }

	
	public boolean updateAppointmentStatus(int appId, String status) {
		
		boolean bool = false;
		
		try {
			
			connection = ConnectionManager.getConnection();
			preparedStatement = connection.prepareStatement(updateAppStatus);
			
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, appId);
			
			
			int i = preparedStatement.executeUpdate();
			if(i > 0) bool = true;  
			
				
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return bool;
	}

}
