package com.mastek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.mastek.bean.PropertyImage;

public class PropertyImgDAO {
	
	
	 public void insertPropertyImages(int propertyId,PropertyImage propertyImg) {
	        String query = "INSERT INTO TBL_PROPERTY_IMG (PROPERTY_ID_FK, IMG) VALUES (:1 , :2 )";
	        try (Connection connection = ConnectionManager.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(query)) 
	        	{
	        		preparedStatement.setInt(1,propertyId);
	        		preparedStatement.setString(2, propertyImg.getUrl());

		            preparedStatement.executeUpdate();
		            System.out.println("Success in Image");

	          	} catch (SQLException e) {
	            e.printStackTrace(); // Handle or log the exception as needed
	        }
		
	    
	 
	 }
	 
}
