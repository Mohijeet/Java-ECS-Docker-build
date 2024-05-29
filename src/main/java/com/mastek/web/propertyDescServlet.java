package com.mastek.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mastek.bean.Property;
import com.mastek.dao.PropertyDao;

/**
 * Servlet implementation class propertyDescServlet
 */
@WebServlet("/propertyDescServlet")
public class propertyDescServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public propertyDescServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pid = Integer.parseInt(request.getParameter("propertyId"));
       
        try {
        
        PropertyDao propertyDao  = new PropertyDao();
        
        Property property = propertyDao.getPropertyById(pid);	
        

        
        String amenities = property.getAmenities();
        String[] amenitiesArray = amenities.split(",");
               
        request.setAttribute("propertyDesc", property);
        request.setAttribute("amenities", amenitiesArray);

        request.getRequestDispatcher("property-desc.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
           

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
