package com.mastek.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastek.bean.TempRent;
import com.mastek.bean.User;
import com.mastek.dao.TempRentDao;

/**
 * Servlet implementation class agentRentDisplayServlet
 */
@WebServlet("/agentRentDisplayServlet")
public class agentRentDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public agentRentDisplayServlet() {
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
		   
		   System.out.println("role "+user.getRole());
		   
		   TempRentDao rentinfoDao = new TempRentDao();
		   List<TempRent> rentinfo = rentinfoDao.selectAllApplication(user,user.getRole());
		   
		  
		   System.out.println(rentinfo);
		   request.setAttribute("rentInfo", rentinfo);
		 

        // Forward the request to the JSP
        request.getRequestDispatcher("agent_display_rent_application.jsp").forward(request, response);
	}

}
