package com.mastek.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastek.bean.Property;
import com.mastek.bean.PropertyAddress;
import com.mastek.bean.User;
import com.mastek.dao.PropertyDao;

/**
 * Servlet implementation class PropertyCrud
 */
@WebServlet("/PropertyCrud")
@MultipartConfig
public class PropertyCrud extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PropertyCrud() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userObj");
		String action = request.getParameter("action");
		PropertyDao propertydao = null;
		Boolean status;
		try {
			propertydao = new PropertyDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int propertyid = Integer.parseInt(request.getParameter("propertyId"));

		switch (action) {
		case "update":
			Property propertyall = propertydao.getPropertyById(propertyid);
			request.setAttribute("Property", propertyall);
			System.out.println("property in update:" + propertyall.toString());
			request.getRequestDispatcher("UpdateProperty.jsp").forward(request, response);
			// response.getWriter().append("Served at: insert" +
			// propertyid).append(request.getContextPath());
			break;
		case "delete":

			status =propertydao.deleteProperty(propertyid);
			if (status) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<script>");
				out.println("alert('localhost says property deleted successfully');");
				out.println("window.location.href = 'index.jsp';"); // Redirect to home page
				out.println("</script>");
				out.println("</body></html>");
			} else {
				// Handle data insertion failure
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<script>");
				out.println("alert('localhost says that there were some issue with delete');");
				out.println("window.location.href = 'index.jsp';"); // Redirect to home page
				out.println("</script>");
				out.println("</body></html>");
			}
			
			break;
		case "markassold":
			
			status=propertydao.markPropertyAsSold(propertyid);
			if (status) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<script>");
				out.println("alert('localhost says property marked aa sold/rent');");
				out.println("window.location.href = 'index.jsp';"); // Redirect to home page
				out.println("</script>");
				out.println("</body></html>");
			} else {
				// Handle data insertion failure
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<script>");
				out.println("alert('localhost says that there were some issue with marking property');");
				out.println("window.location.href = 'index.jsp';"); // Redirect to home page
				out.println("</script>");
				out.println("</body></html>");
			}
			//response.getWriter().append("Served at:sold " + propertyid).append(request.getContextPath());
			break;
		case "updateProperty":
			//response.getWriter().append("Served at:upadte property call" + propertyid).append(request.getContextPath());
			Property property = new Property();
			property.setPropertyId(propertyid);
			property.setOwner(user);
			property.setPropertyType(request.getParameter("propertyType"));
			property.setProSize(request.getParameter("propertySize"));
			property.setPrice(Double.parseDouble(request.getParameter("price")));
			property.setFeatures(request.getParameter("features"));
			property.setNoOfRooms(Integer.parseInt(request.getParameter("numRooms")));
			property.setNoOfKitchens(Integer.parseInt(request.getParameter("numKitchens")));
			property.setNoOfBathrooms(Integer.parseInt(request.getParameter("numBathrooms")));
			property.setAmenities(request.getParameter("amenities"));
			property.setStatus(request.getParameter("propoertyStatus"));
			property.setPurpose(request.getParameter("purpose"));

			PropertyAddress address = new PropertyAddress();
			address.setCity(request.getParameter("city"));
			address.setLandmark(request.getParameter("landmark"));
			address.setSociety(request.getParameter("society"));
			address.setPincode(request.getParameter("pincode"));
			address.setState(request.getParameter("state"));

			property.setAddress(address);

			status = propertydao.updateProperty(property);
			if (status) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<script>");
				out.println("alert('localhost says property updated successfully');");
				out.println("window.location.href = 'index.jsp';"); // Redirect to home page
				out.println("</script>");
				out.println("</body></html>");
			} else {
				// Handle data insertion failure
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("<script>");
				out.println("alert('localhost says that there were some issue with update');");
				out.println("window.location.href = 'index.jsp';"); // Redirect to home page
				out.println("</script>");
				out.println("</body></html>");
			}
			break;
		default:
			response.getWriter().append("Served at:defaults " + propertyid).append(request.getContextPath());
			break;

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
