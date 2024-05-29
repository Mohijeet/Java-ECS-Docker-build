package com.mastek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mastek.bean.PropertyDocument;

public class PropertyDocDAO {
	public void insertPropertyDocs(int propertyId_fk,PropertyDocument propertyDoc) {
        String query = "INSERT INTO TBL_PROPERTY_DOC (PROPERTY_ID_FK, PRO_DOC_IMAGE) VALUES (:1 , :2 )";
        
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, propertyId_fk);
            preparedStatement.setString(2, propertyDoc.getDocumentImage());
            

            preparedStatement.executeUpdate();

            System.out.println("Success in Document");
          	} catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
	
    }
}
