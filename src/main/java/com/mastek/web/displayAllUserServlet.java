package com.mastek.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mastek.bean.User;
import com.mastek.dao.UserDao;


/**
 * Servlet implementation class displayAllUserServlet
 */
@WebServlet("/displayAllUserServlet")
public class displayAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	List<User> userList;

	
    public displayAllUserServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
				userList =  UserDao.selectAllUser();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	        
	        // Convert fetched data to JSON
	        String jsonData = new Gson().toJson(userList);

	        // Set content type and write JSON response
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonData);
	
	}

	

}
