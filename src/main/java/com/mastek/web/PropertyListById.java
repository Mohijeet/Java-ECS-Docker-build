package com.mastek.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastek.bean.Property;
import com.mastek.bean.User;
import com.mastek.dao.PropertyDao;

/**
 * Servlet implementation class PropertyListById
 */
@WebServlet("/PropertyListById")
public class PropertyListById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropertyListById() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userObj");
		try {
			PropertyDao propertysDao = new PropertyDao();
			List<Property> agentProperties = propertysDao.getPropertiesByUserId(user.getUserId());
			request.setAttribute("agentProperties", agentProperties);
	        request.getRequestDispatcher("agentProperties.jsp").forward(request, response);
		
		}
		catch(Exception e ) {
			
		}
        // Forward the properties to a JSP page
        }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
