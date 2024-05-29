package com.mastek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mastek.bean.Property;
import com.mastek.bean.PropertyAddress;
import com.mastek.bean.PropertyImage;
import com.mastek.bean.User;

import oracle.jdbc.proxy.annotation.Pre;

public class PropertyDao {

	private static final String INSERT_PROPERTY_SQL = "INSERT INTO tbl_properties"
			+ "(u_id_fk, property_type, pro_size, price, features, no_of_rooms, no_of_kitchen, no_of_bathrooms, amenities, status, purpose) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

//	// property_id, u_id_fk, property_type, pro_size, price, features, no_of_rooms,
//	// no_of_kitchen, no_of_bathrooms, amenities, status, purpose
//
	private static final String SELECT_PROPERTY_BY_ID = "SELECT * from tbl_properties where property_id = ?";
//
//	// private static final String SELECT_ALL_PROPERTY = "SELECT * FROM
//	// tbl_properties";
//
//	private static final String DELETE_PROPERTY_SQL = "DELETE FROM tbl_properties WHERE property_id =?";
	private static final String UPDATE_PROPERTY_STATUS_SQL = "UPDATE tbl_properties SET  status = :1 where property_id = :2 and purpose = :3 ";
	private static final String SELECT_IMAGES_BY_PROPERTY_ID = "SELECT IMG  from tbl_property_img where PROPERTY_ID_FK = ?";
	private static final String SELECT_ADDRESS_BY_PROPERTY_ID = "SELECT LANDMARK,SOCIETY,CITY,STATES,PINCODE  from tbl_property_add where PROPERTY_ID_FK = ?";

	 
	 private static final String UPDATE_PROPERTY_ADDRESS_SQL = "UPDATE tbl_property_add SET "
	            + "LANDMARK=?, SOCIETY=?, CITY=?, STATES=?, PINCODE=? WHERE PROPERTY_ID_FK=?";
	 
	 private static final String DELETE_PROPERTY_SQL = "DELETE FROM tbl_properties WHERE PROPERTY_ID=?";
	
//	New 
//	
//    private static final String GET_PROPERTY_BY_ID_SQL = ""
//            + "SELECT p.*, a.*, d.* "
//            + "FROM Property p "
//            + "INNER JOIN PropertyAddress a ON p.address_id = a.addressId "
//            + "INNER JOIN PropertyDocument d ON p.document_id = d.documentId "
//            + "LEFT JOIN PropertyImage img ON p.propertyId = img.property_id "  // Left join for optional images
//            + "WHERE p.propertyId = ?";
//
//    private static final String GET_ALL_PROPERTIES_SQL = ""
//            + "SELECT p.*, a.*, d.*, img.url AS image_url "  // Select image URL from joined table
//            + "FROM Property p "
//            + "INNER JOIN PropertyAddress a ON p.address_id = a.addressId "
//            + "INNER JOIN PropertyDocument d ON p.document_id = d.documentId "
//            + "LEFT JOIN PropertyImage img ON p.propertyId = img.property_id";  // Left join for optional images
//
   private static final String UPDATE_PROPERTY_SQL = "UPDATE tbl_properties SET "
            + "PROPERTY_TYPE=?, PRO_SIZE=?, PRICE=?, FEATURES=?, "
            + "NO_OF_ROOMS=?, NO_OF_KITCHEN=?, NO_OF_BATHROOMS=?, AMENITIES=?, "
            + "STATUS=?, PURPOSE=? WHERE PROPERTY_ID=?";


	private static final String SAVE_PROPERTY_IMAGE_SQL = "INSERT INTO tbl_property_img (property_id_fk, img) VALUES (?, ?)";

	private static final String SAVE_PROPERTY_DOC_SQL = "INSERT INTO tbl_property_doc (property_id_fk, pro_doc_img) VALUES (?, ?)";

	private static final String querysel = "select PROPERTY_ID from TBL_PROPERTIES where PROPERTY_ID=(select max(PROPERTY_ID) from tbl_properties)";

// End	

	Connection connection = null;

	public PropertyDao() throws SQLException {

		connection = ConnectionManager.getConnection();

	}

	// insert user

	public void insertProperty(Property property) {

		try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROPERTY_SQL)) {

			preparedStatement.setInt(1, property.getOwner().getUserId()); // Set owner ID as foreign key
			preparedStatement.setString(2, property.getPropertyType());
			preparedStatement.setString(3, property.getProSize());
			preparedStatement.setDouble(4, property.getPrice());
			preparedStatement.setString(5, property.getFeatures());
			preparedStatement.setInt(6, property.getNoOfRooms());
			preparedStatement.setInt(7, property.getNoOfKitchens());
			preparedStatement.setInt(8, property.getNoOfBathrooms());
			preparedStatement.setString(9, property.getAmenities());
			preparedStatement.setString(10, property.getStatus());
			preparedStatement.setString(11, property.getPurpose());

			preparedStatement.executeUpdate();
			// Save address and document after getting the auto-generated property ID
			int propertyId = getGeneratedKey(connection);

			System.out.println("propertyId " + propertyId);
			PropertyAddressDAO addressDao = new PropertyAddressDAO();
			addressDao.insertProperty(propertyId, property.getAddress());

			PropertyDocDAO docDao = new PropertyDocDAO();
			docDao.insertPropertyDocs(propertyId, property.getDocument());

			int finalPropertyId = propertyId;
			// Save each property image
			PropertyImgDAO imageDao = new PropertyImgDAO();
			for (PropertyImage image : property.getImages()) {
				imageDao.insertPropertyImages(finalPropertyId, image);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private int getGeneratedKey(Connection conn) throws SQLException {
		int generatedKey = -1;
		ResultSet rs = conn.createStatement().executeQuery(querysel);
		if (rs.next()) {
			generatedKey = rs.getInt(1);
		}
		return generatedKey;
	}

	// get property by id

	public Property getPropertyById(int propertyId) {
		Property property = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROPERTY_BY_ID);
				PreparedStatement preparedStatementImages = connection.prepareStatement(SELECT_IMAGES_BY_PROPERTY_ID);
				PreparedStatement preparedStatementAddress = connection
						.prepareStatement(SELECT_ADDRESS_BY_PROPERTY_ID)) {

			preparedStatement.setInt(1, propertyId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				User user = new User();
				user.setUserId(resultSet.getInt("u_id_fk"));

				property = new Property();
				property.setOwner(user);
				property.setPropertyId(resultSet.getInt("property_id"));
				property.setPropertyType(resultSet.getString("property_type"));
				property.setProSize(resultSet.getString("pro_size"));
				property.setFeatures(resultSet.getString("features"));
				property.setPurpose(resultSet.getString("purpose"));
				property.setPrice(resultSet.getDouble("price"));
				property.setNoOfRooms(resultSet.getInt("no_of_rooms"));
				property.setNoOfKitchens(resultSet.getInt("no_of_kitchen"));
				property.setNoOfBathrooms(resultSet.getInt("no_of_bathrooms"));
				property.setAmenities(resultSet.getString("amenities"));
				property.setStatus(resultSet.getString("status"));

				// property.setAddress(rs.getString("address"));
				// Set other properties from tbl_properties

				// Fetch property images
				preparedStatementImages.setInt(1, propertyId);
				ResultSet imageResultSet = preparedStatementImages.executeQuery(); //

				List<PropertyImage> list = new ArrayList<>();

				while (imageResultSet.next()) {

					System.out.println("image " + imageResultSet.getString("img"));

					PropertyImage image = new PropertyImage();

					// Get the index of the last occurrence of the directory separator (\)
					String filePath = imageResultSet.getString("img");

					int lastIndexOfSeparator = filePath.lastIndexOf('\\');

					// Get the substring starting from the last occurrence of the directory
					// separator
					String newPath = filePath.substring(lastIndexOfSeparator + 1);

					// Replace backslashes with double backslashes
					newPath = newPath.replaceAll("\\\\", "\\\\\\\\");

					// Add the necessary directory (Property_images\\) to the beginning of the path
					newPath = "Property_images\\" + newPath;

					System.out.println("new " + newPath);

					// property.setImageData(newPath);
					image.setUrl(newPath);
					System.out.println("imgage object:" + image.toString());
					list.add(image); // Set properties of image
					System.out.println(list.toString());

				}

				property.setImages(list);
				System.out.println("Property:" + property.getImages());
				for (PropertyImage propertyImage : list) {

					System.out.println(propertyImage);
					System.out.println("img:");

				}

				// Fetch property address

				preparedStatementAddress.setInt(1, propertyId);
				ResultSet addressResultSet = preparedStatementAddress.executeQuery();
				if (addressResultSet.next()) {
					PropertyAddress address = new PropertyAddress();

					// Set properties of address
					address.setLandmark(addressResultSet.getString("landmark"));
					address.setSociety(addressResultSet.getString("society"));
					address.setCity(addressResultSet.getString("city"));
					address.setState(addressResultSet.getString("states"));
					address.setPincode(addressResultSet.getString("pincode"));

					property.setAddress(address);
				}
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return property;
	}
	
	
	
	// update status
		public boolean updatePropertyStatusById(int propId, String purpose) {
		
			boolean isUpdate = false;
			String propPurpose = purpose;
			
			if(propPurpose.equals("Rent")) 
			{
					try {
						PreparedStatement psf = connection.prepareStatement(UPDATE_PROPERTY_STATUS_SQL);
						psf.setString(1, "Not Available");
						psf.setInt(2, propId);
						psf.setString(3, propPurpose);
						
						int i = psf.executeUpdate();
						if(i > 0) {
							isUpdate = true;
						}
						
						
					}catch (Exception e) {
			
						e.printStackTrace();
					}
					
			}
			
			
			return isUpdate;
			
		
	}
		
		
		
	// 
		

		public List<Property> getPropertiesByUserId(int userId) {
		    List<Property> properties = new ArrayList<>();
		    String query= "SELECT * FROM TBL_PROPERTIES WHERE U_ID_FK=?";
		    try (PreparedStatement statement = connection.prepareStatement(query)) {
		        statement.setInt(1, userId);
		        try (ResultSet resultSet = statement.executeQuery()) {
		            while (resultSet.next()) {
		                Property property = new Property();
		                property.setPropertyId(resultSet.getInt("PROPERTY_ID"));
		                User user = new User();
		                user.setUserId(resultSet.getInt("U_ID_FK"));
		                property.setOwner(user);
		                property.setPropertyType(resultSet.getString("PROPERTY_TYPE"));
		                property.setProSize(resultSet.getString("PRO_SIZE"));
		                property.setPrice(resultSet.getDouble("PRICE"));
		                property.setFeatures(resultSet.getString("FEATURES"));
		                property.setNoOfRooms(resultSet.getInt("NO_OF_ROOMS"));
		                property.setNoOfKitchens(resultSet.getInt("NO_OF_KITCHEN"));
		                property.setNoOfBathrooms(resultSet.getInt("NO_OF_BATHROOMS"));
		                property.setAmenities(resultSet.getString("AMENITIES"));
		                property.setStatus(resultSet.getString("STATUS"));
		                property.setPurpose(resultSet.getString("PURPOSE"));
		                properties.add(property);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return properties;
		}
		
		
		public boolean updateProperty(Property property) {
	        boolean success = false;
	        PreparedStatement stmt1 = null;
	        PreparedStatement stmt2 = null;

	        try {
	            // Begin transaction
	            connection.setAutoCommit(false);

	            // Update tbl_properties
	            stmt1 = connection.prepareStatement(UPDATE_PROPERTY_SQL);
	            stmt1.setString(1, property.getPropertyType());
	            stmt1.setString(2, property.getProSize());
	            stmt1.setDouble(3, property.getPrice());
	            stmt1.setString(4, property.getFeatures());
	            stmt1.setInt(5, property.getNoOfRooms());
	            stmt1.setInt(6, property.getNoOfKitchens());
	            stmt1.setInt(7, property.getNoOfBathrooms());
	            stmt1.setString(8, property.getAmenities());
	            stmt1.setString(9, property.getStatus());
	            stmt1.setString(10, property.getPurpose());
	            stmt1.setInt(11, property.getPropertyId());
	            int rowsUpdated1 = stmt1.executeUpdate();

	            // Update tbl_property_add
	            PropertyAddress address = property.getAddress();
	            stmt2 = connection.prepareStatement(UPDATE_PROPERTY_ADDRESS_SQL);
	            stmt2.setString(1, address.getLandmark());
	            stmt2.setString(2, address.getSociety());
	            stmt2.setString(3, address.getCity());
	            stmt2.setString(4, address.getState());
	            stmt2.setString(5, address.getPincode());
	            stmt2.setInt(6, property.getPropertyId());
	            int rowsUpdated2 = stmt2.executeUpdate();

	            // Commit transaction if both updates are successful
	            if (rowsUpdated1 > 0 && rowsUpdated2 > 0) {
	                connection.commit();
	                success = true;
	            } else {
	                connection.rollback();
	            }
	        } catch (SQLException e) {
	            // Handle exceptions
	            try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            e.printStackTrace();
	        } finally {
	            // Close resources
	            try {
	                if (stmt1 != null) stmt1.close();
	                if (stmt2 != null) stmt2.close();
	                connection.setAutoCommit(true);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return success;
	    }
		
		public boolean deleteProperty(int propertyId) {
	        boolean success = false;
	        PreparedStatement stmt1 = null;

	        try {

	            // Delete from tbl_properties
	            stmt1 = connection.prepareStatement(DELETE_PROPERTY_SQL);
	            stmt1.setInt(1, propertyId);
	            int rowsDeleted1 = stmt1.executeUpdate();

	            // Delete from tbl_property_add (cascade delete)
	            
	            // Commit transaction if both deletions are successful
	            if (rowsDeleted1 > 0) {
	                success = true;
	            } else {
	                connection.rollback();
	            }
	        } catch (SQLException e) {
	            // Handle exceptions
	            try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	            e.printStackTrace();
	        } finally {
	            // Close resources
	            try {
	                if (stmt1 != null) stmt1.close();
	                connection.setAutoCommit(true);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return success;
	    }
		public boolean markPropertyAsSold(int propertyId) {
			boolean success = false;

		    String sql = "UPDATE tbl_properties SET STATUS = 'Not Available' WHERE PROPERTY_ID = ?";
		    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		        preparedStatement.setInt(1, propertyId);
		        preparedStatement.executeUpdate();
		        success=true;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return success;
		}	

}
