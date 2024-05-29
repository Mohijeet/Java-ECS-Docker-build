package com.mastek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mastek.bean.TempRent;
import com.mastek.bean.User;

public class TempRentDao {

	public Boolean insertTempRent(TempRent tempRent) {
		Boolean bool = false;
		String sql = "INSERT INTO TBL_TEMP_RENT_INFO (PROPERTY_ID_FK, USER_TENANT_ID, AGENT_LANDLORD_ID, RENT, START_DATE, END_DATE, TENANT_ADHARCARD,APP_STATUS) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
		
        try(Connection connection = ConnectionManager.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            
            
            // Set parameters
            preparedStatement.setInt(1, tempRent.getProperty_id_fk());
            preparedStatement.setInt(2, tempRent.getUser_tenant_id());
            preparedStatement.setInt(3, tempRent.getAgent_landlord_id());
            preparedStatement.setDouble(4, tempRent.getRent());
            preparedStatement.setDate(5,new java.sql.Date(tempRent.getStartDate().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(tempRent.getEndDate().getTime()));
            preparedStatement.setString(7, tempRent.getAdharcard());
            preparedStatement.setString(8, "pending");
            
            preparedStatement.executeUpdate();
            bool=true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions here
        }
        return bool;
    }
	
	public List<TempRent> selectAllApplication(User user,String role){
		List<TempRent> rentApplication = new ArrayList<>();
		String agentSql = "select TR.RENT_ID,TR.PROPERTY_ID_FK, TR.USER_TENANT_ID, TR.AGENT_LANDLORD_ID, TR.RENT, TR.START_DATE, TR.END_DATE, TR.TENANT_ADHARCARD,TR.APP_STATUS, U.FIRST_NAME, U.MOBILENUMBER, U.EMAIL from TBL_TEMP_RENT_INFO TR join TBL_USERS U on TR.USER_TENANT_ID=U.U_ID where TR.AGENT_LANDLORD_ID= ? and TR.APP_STATUS = 'pending' ";
		String tenantSql = "select TR.RENT_ID,TR.PROPERTY_ID_FK, TR.USER_TENANT_ID, TR.AGENT_LANDLORD_ID, TR.RENT, TR.START_DATE, TR.END_DATE, TR.TENANT_ADHARCARD, TR.APP_STATUS, U.FIRST_NAME, U.MOBILENUMBER, U.EMAIL from TBL_TEMP_RENT_INFO TR join TBL_USERS U on TR.AGENT_LANDLORD_ID=U.U_ID where TR.USER_TENANT_ID= ?";
		PreparedStatement preparedStatement = null;
		
			
				try (Connection connection = ConnectionManager.getConnection();)
				{
					if(role.equals("agent") || role.equals("landlord"))
					{
						
						preparedStatement = connection.prepareStatement(agentSql);
						

					}
					else if(role.equals("tenant") || role.equals("user")) {

						preparedStatement = connection.prepareStatement(tenantSql);

				   }
					
					
				preparedStatement.setInt(1, user.getUserId());
	            ResultSet resultSet = preparedStatement.executeQuery();
	            System.out.println(resultSet);
	            while (resultSet.next()) {
	                TempRent TempRent = new TempRent();
	                TempRent.setRentId(resultSet.getInt("RENT_ID"));
	                TempRent.setProperty_id_fk(resultSet.getInt("PROPERTY_ID_FK"));
	                TempRent.setUser_tenant_id(resultSet.getInt("USER_TENANT_ID"));
	                TempRent.setAgent_landlord_id(resultSet.getInt("AGENT_LANDLORD_ID"));
	                TempRent.setRent(resultSet.getDouble("RENT"));
	                TempRent.setStartDate(resultSet.getDate("START_DATE"));
	                TempRent.setEndDate(resultSet.getDate("END_DATE"));
	                String filePath =resultSet.getString("TENANT_ADHARCARD");

	                int lastIndexOfSeparator = filePath.lastIndexOf('\\');
	                
	                
	               			// Get the substring starting from the last occurrence of the directory separator
	                String newPath = filePath.substring(lastIndexOfSeparator + 1);
	                
	                			// Replace backslashes with double backslashes
	                newPath = newPath.replaceAll("\\\\", "\\\\\\\\");
	                
	                			// Add the necessary directory (Property_images\\) to the beginning of the path
	                newPath = "Tenant_aadharCard\\" + newPath;

	                TempRent.setAdharcard(newPath);
	                TempRent.setApp_status(resultSet.getString("APP_STATUS"));

	                TempRent.setFirstname(resultSet.getString("FIRST_NAME"));
	                TempRent.setMobilenum(resultSet.getString("MOBILENUMBER"));
	                TempRent.setEmail(resultSet.getString("EMAIL"));
	                rentApplication.add(TempRent);
	                System.out.println(TempRent.toString());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return rentApplication;
	}
	
	
	
	
	
}
