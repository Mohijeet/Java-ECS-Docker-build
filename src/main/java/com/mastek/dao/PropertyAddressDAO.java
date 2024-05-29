package com.mastek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mastek.bean.PropertyAddress;

public class PropertyAddressDAO {
	 public void insertProperty(int property_id_fk,PropertyAddress propertyAddress) {
	        String query = "INSERT INTO TBL_PROPERTY_ADD (PROPERTY_ID_FK, LANDMARK, SOCIETY, CITY, STATES, PINCODE) VALUES (:1 , :2 , :3 , :4 , :5 , :6 )";
	        
	        try (Connection connection = ConnectionManager.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setInt(1, property_id_fk);
	            preparedStatement.setString(2, propertyAddress.getLandmark());
	            preparedStatement.setString(3, propertyAddress.getSociety());
	            preparedStatement.setString(4, propertyAddress.getCity());
	            preparedStatement.setString(5, propertyAddress.getState());
	            preparedStatement.setString(6, propertyAddress.getPincode());
	            

	            preparedStatement.executeUpdate();

	            System.out.println("Success in Address");
	          	} catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception as needed
	        }
		
	    }
	 
	 
	 
	 public PropertyAddress getAddressById(int propertyId) {
 		 String query = "SELECT ADDRESS_ID, PROPERTY_ID_FK, LANDMARK, SOCIETY, CITY, STATES, PINCODE " +
                 "FROM TBL_PROPERTY_ADD " +
                 "WHERE PROPERTY_ID_FK = ?";
 		 PropertyAddress propertyAddress = null;
 		 try (Connection connection = ConnectionManager.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
 			 	 preparedStatement.setInt(1, propertyId);
 			 	 try (ResultSet rs = preparedStatement.executeQuery()) {
 	                if (rs.next()) {
 	                    int addressId = rs.getInt("ADDRESS_ID");
 	                    int propertyIdFk = rs.getInt("PROPERTY_ID_FK");
 	                    String landmark = rs.getString("LANDMARK");
 	                    String society = rs.getString("SOCIETY");
 	                    String city = rs.getString("CITY");
 	                    String state = rs.getString("STATES");
 	                    String pincode = rs.getString("PINCODE");

 	                    // Construct PropertyAddress object with fetched data
 	                    propertyAddress = new PropertyAddress(addressId, landmark, society, city, state, pincode);
 
 	                }
 	            }
 			 
 		 }
 		 catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception as needed
	      }
         return propertyAddress;
 	}

}
