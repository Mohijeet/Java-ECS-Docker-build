package com.mastek.dao;

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

/**
 * Servlet implementation class tenantRentStatusDisplayServlet
 */
@WebServlet("/tenantRentStatusDisplayServlet")
public class tenantRentStatusDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tenantRentStatusDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				   HttpSession session = request.getSession();
				   User user = (User) session.getAttribute("userObj");
				   System.out.println(user.getEmail());
				   System.out.println("role "+user.getRole());

				   TempRentDao rentinfoDao = new TempRentDao();
				   List<TempRent> tenantappinfo = rentinfoDao.selectAllApplication(user,user.getRole());
				   System.out.println("from servlet of tenant "+tenantappinfo.toString());
				   request.setAttribute("tenantAppInfo", tenantappinfo);

		        // Forward the request to the JSP
		        request.getRequestDispatcher("tenant_display_rent_application.jsp").forward(request, response);
	}

	
}
