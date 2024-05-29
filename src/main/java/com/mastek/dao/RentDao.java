package com.mastek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mastek.bean.Rent;

public class RentDao {
	
	
	final static String insertToRentInfo ="insert into tbl_rent_info (property_id_fk, user_tenant_id, agent_landlord_id, rent, start_date, end_date ) values (?,?,?,?,?,?)";
	private static final String querysel = "select RENT_ID from TBL_RENT_INFO where RENT_ID=(select max(RENT_ID) from TBL_RENT_INFO)";

	
	public Boolean insertRent(int Property_id_fk, int User_tenant_id, int Agent_landlord_id,  Rent rent,String lease,String adharcard) {
		Boolean bool = false;
//		String sql = "INSERT INTO TBL_TEMP_RENT_INFO (PROPERTY_ID_FK, USER_TENANT_ID, AGENT_LANDLORD_ID, RENT, START_DATE, END_DATE, TENANT_ADHARCARD,APP_STATUS) "
//                + "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
		
        try(Connection connection = ConnectionManager.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(insertToRentInfo)) {

            
            
            preparedStatement.setInt(1, Property_id_fk);
            preparedStatement.setInt(2, User_tenant_id);
            preparedStatement.setInt(3, Agent_landlord_id);
            preparedStatement.setDouble(4, rent.getRent());
            preparedStatement.setDate(5, java.sql.Date.valueOf(rent.getStartDate()));
            preparedStatement.setDate(6, java.sql.Date.valueOf(rent.getEndDate()));
        	preparedStatement.executeUpdate();

            int rent_id_fk = getGeneratedKey(connection);
            System.out.println("Rent id fk"+rent_id_fk);
            boolean rd = insertRentDocument(rent_id_fk,lease,adharcard);
            if(rd) {
                bool=true;
            }
           
            PropertyDao dao = new PropertyDao();
            boolean propStatus = dao.updatePropertyStatusById(Property_id_fk,"Rent");
            
            if(propStatus) {
            	
            	System.out.println("Property status has been updated... Now its Rented :) ");
            	
            }else {
            	
            	System.out.println("We're facing some issue to update status of Property :( ");

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions here
        }
        return bool;
    }
	

	public Boolean insertRentDocument(int rent_id_fk, String lease,String adharcard) {
		Boolean bool = false;
		
    	final String insertToRentDocument = "insert into tbl_rent_doc (rent_id_fk, rent_lease_image, tenant_adharcard) values(?,?,?)";
    	
    	 try(Connection connection = ConnectionManager.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(insertToRentDocument)) {

        
        
        preparedStatement.setInt(1,rent_id_fk); 	
        preparedStatement.setString(2,lease);
        preparedStatement.setString(3, adharcard);
        
        int rd = preparedStatement.executeUpdate();

       
        if(rd > 0) {
            bool=true;
        }
       
        
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions here
    }

		return bool;
	}

	
	private int getGeneratedKey(Connection conn) throws SQLException {
        int generatedKey = -1;
        ResultSet rs = conn.createStatement().executeQuery(querysel);
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }
	
	
}
