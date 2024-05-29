package com.mastek.web;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mastek.bean.Property;
import com.mastek.bean.PropertyAddress;
import com.mastek.bean.PropertyDocument;
import com.mastek.bean.PropertyImage;
import com.mastek.bean.User;
import com.mastek.dao.PropertyAddressDAO;
import com.mastek.dao.PropertyDao;
import com.mastek.dao.PropertyDocDAO;
import com.mastek.dao.PropertyImgDAO;

/**
 * Servlet implementation class PropertyServlets
 */
@WebServlet("/PropertyServlet")
@MultipartConfig
public class PropertyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   HttpSession session = request.getSession();
		   User user = (User) session.getAttribute("userObj");

	        Property property = new Property();
	        PropertyDao propertyDao = null;

	        try {
	            propertyDao = new PropertyDao(); // Initialize PropertyDao
	            property.setOwner(user);
	            property.setPropertyType(request.getParameter("propertyType"));
	            property.setProSize(request.getParameter("propertySize"));
	            property.setPrice(Double.parseDouble(request.getParameter("price")));
	            property.setFeatures(request.getParameter("features"));
	            property.setNoOfRooms(Integer.parseInt(request.getParameter("numRooms")));
	            property.setNoOfKitchens(Integer.parseInt(request.getParameter("numKitchens")));
	            property.setNoOfBathrooms(Integer.parseInt(request.getParameter("numBathrooms")));
	            property.setAmenities(request.getParameter("amenities"));
	            property.setStatus(request.getParameter("status"));
	            property.setPurpose(request.getParameter("purpose"));

	            PropertyAddress address = new PropertyAddress();
	            address.setCity(request.getParameter("city"));
	            address.setLandmark(request.getParameter("landmark"));
	            address.setSociety(request.getParameter("society"));
	            address.setPincode(request.getParameter("pincode"));
	            address.setState(request.getParameter("state"));

	            property.setAddress(address);
	            
	            
	            // property Document
	            
	            Part imagePart1 = request.getPart("propertyDocImages");
	            String fileName1 = saveImageToFileSystem(imagePart1,"Docs");
	            
	            PropertyDocument propertydoc = new PropertyDocument();
	            propertydoc.setDocumentImage(fileName1);
	            
	            property.setDocument(propertydoc);
	            
	            
	            
//	            // image property insert 
	            Collection<Part> imageParts = request.getParts();
	            List<PropertyImage> images = new ArrayList<>();
	            for (Part part : imageParts) {
	                if (part.getName().startsWith("propertyImages")) { // Assuming image parts have name attribute like image1, image2, etc.
	                    String fileName = saveImageToFileSystem(part,"Images");
	                    // Save the image file to your desired location
	                    // Construct a PropertyImage object and add it to the list
	                    PropertyImage image = new PropertyImage();
	                    image.setUrl(fileName);
	                    images.add(image);
	                }
	            }
	            
	            property.setImages(images);
	            
	            
	            
	            propertyDao.insertProperty(property); // Insert property data
	            
	            response.getWriter().print("<h3>Successfully Registered Property</h3>");
	            response.sendRedirect("index.jsp");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.getWriter().print("<h3>Error occurred while registering property</h3>");
	        }
	
	}
	
	private String saveImageToFileSystem(Part filePart, String img) throws IOException {
		String uploadDirectory="";
		String fileName="";
		int count=1;
		if(img=="Images") {
			uploadDirectory = "/Users/mohijeet/eclipse-dynamic/FINAL_MASTEK/src/main/webapp/Property_images"; // Update this path
			fileName = "Property_IMG_"+System.currentTimeMillis() + "_" + filePart.getSubmittedFileName()+ count +getExtension(filePart); // Generate a unique filename
		}
		else if(img=="Docs") {
			uploadDirectory = "/Users/mohijeet/eclipse-dynamic/FINAL_MASTEK/src/main/webapp/Property_docs";
			fileName = "Property_DOC_" + System.currentTimeMillis() + "_" + filePart.getSubmittedFileName()+  + count +getExtension(filePart); // Generate a unique filename
		}
			try (InputStream inputStream = filePart.getInputStream();
             OutputStream outputStream = new FileOutputStream(uploadDirectory + File.separator + fileName)) {

            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
		
        count++;
        return uploadDirectory+"\\"+fileName;
    }

		
        
	
	private String getExtension(Part filePart) {
	    String contentDisposition = filePart.getHeader("content-disposition");
	    String[] tokens = contentDisposition.split(";");
	    for (String token : tokens) {
	        if (token.trim().startsWith("filename")) {
	            String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
	            return fileName.substring(fileName.lastIndexOf("."));
	        }
	    }
	    return "";
	}



}
