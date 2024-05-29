package com.mastek.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mastek.bean.Property;
import com.mastek.bean.PropertyAddress;
import com.mastek.bean.PropertyImage;
import com.mastek.dao.ConnectionManager;


import oracle.jdbc.OracleTypes;

/**
 * Servlet implementation class propertyListServlet
 */
@WebServlet("/propertyListServlet")
public class propertyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public propertyListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  List<Property> properties = new ArrayList<>();

	        try {
	            Connection conn = ConnectionManager.getConnection();
	            CallableStatement cs = conn.prepareCall("{call displayPropertyList(?)}");
	            cs.registerOutParameter(1, OracleTypes.CURSOR);
	            cs.execute();
	            ResultSet rs = (ResultSet)cs.getObject(1);
	            
	            while (rs.next()) {
		            String property_status = rs.getString("status");

	            	if(property_status.equals("Available")) 
	            	{

			                Property property = new Property();
			                property.setPropertyId(rs.getInt("property_id"));
			                property.setPropertyType(rs.getString("property_type"));
			                property.setProSize(rs.getString("pro_size"));
			                property.setPurpose(rs.getString("purpose"));
			                property.setStatus(rs.getString("status"));
			                property.setPrice(rs.getDouble("price"));
			                property.setNoOfRooms(rs.getInt("no_of_rooms"));
			                property.setNoOfKitchens(rs.getInt("no_of_kitchen"));
			                property.setNoOfBathrooms(rs.getInt("no_of_bathrooms"));
			                System.out.println("Status:"+property.getStatus());
			                PropertyAddress address = new PropertyAddress();
			                address.setSociety(rs.getString("address")); // set Society 
			                property.setAddress(address);

	                
	                

	                // Fetching images for the property
	                List<PropertyImage> images = new ArrayList<>();
	                // Assuming the stored procedure returns image URLs along with other property details
	                
	                String filePath = rs.getString("img");
	                int lastIndexOfSeparator = filePath.lastIndexOf('\\');
	                String newPath = filePath.substring(lastIndexOfSeparator + 1).replaceAll("\\\\", "\\\\\\\\");
	                newPath = "Property_images\\" + newPath;
	                
	                PropertyImage proimage = new PropertyImage();
	                proimage.setUrl(newPath);
	                images.add(proimage);
	                
	                property.setImages(images);
	                
	                properties.add(property);
	                System.out.println("properties : "+properties);

	            	}
	            	else{
	            		System.out.println("Property Is Not Available");
	            	}
	            }

	            rs.close();
	            cs.close();
	            conn.close();
	            
            	

	            // Convert fetched data to JSON
	            String jsonData = new Gson().toJson(properties);

	            // Set content type and write JSON response
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            PrintWriter out = response.getWriter();
	            out.print(jsonData);
	            out.flush();
            	
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write("Error occurred while fetching data from the database.");
	        }
	    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
