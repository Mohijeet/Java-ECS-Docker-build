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
import oracle.jdbc.OracleTypes;
import com.google.gson.Gson;
import com.mastek.bean.Property;
import com.mastek.bean.PropertyAddress;
import com.mastek.bean.PropertyImage;
import com.mastek.dao.ConnectionManager;

@WebServlet("/PropertySearchServlet")
public class PropertySearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PropertySearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Property> properties = new ArrayList<>();

        // Get search parameters from request
        String priceRange = request.getParameter("priceRange");
       String propertyType = request.getParameter("propertyType");
       String location = request.getParameter("location");
    
       if(priceRange.equals("0")) {
    	   priceRange ="";
       }
       if(propertyType.equals("0")) {
    	   propertyType ="";
       }
       if(location.equals("0")) {
    	   location ="";
       }
        System.out.println(priceRange+" "+propertyType+" "+location+ " in PropertySearchServelet");
       
        

        try {
            Connection conn = ConnectionManager.getConnection();
            CallableStatement cs = conn.prepareCall("{call searchProperties(?, ?, ?, ?)}");
            cs.setString(1, priceRange);
            cs.setString(2, propertyType);
            cs.setString(3, location);
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet)cs.getObject(4);

            while (rs.next()) {
                Property property = new Property();
                property.setPropertyId(rs.getInt("property_id"));
                property.setPropertyType(rs.getString("property_type"));
                property.setProSize(rs.getString("pro_size"));
                property.setPurpose(rs.getString("purpose"));
                property.setPrice(rs.getDouble("price"));
                property.setNoOfRooms(rs.getInt("no_of_rooms"));
                property.setNoOfKitchens(rs.getInt("no_of_kitchen"));
                property.setNoOfBathrooms(rs.getInt("no_of_bathrooms"));

                PropertyAddress address = new PropertyAddress();
                address.setSociety(rs.getString("address")); // set Society
                property.setAddress(address);

                // Fetching images for the property
                List<PropertyImage> images = new ArrayList<>();
                // Assuming the stored procedure returns image URLs along with other property details
                String img = rs.getString("img");
                // Modify image path similar to PropertyListServlet
                int lastIndexOfSeparator = img.lastIndexOf('\\');
                String newPath = img.substring(lastIndexOfSeparator + 1).replaceAll("\\\\", "\\\\\\\\");
                newPath = "Property_images\\" + newPath;
                
                PropertyImage propertyImage = new PropertyImage();
                propertyImage.setUrl(newPath);
                images.add(propertyImage);
                property.setImages(images);

                properties.add(property);
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
        doGet(request, response);
    }
}
