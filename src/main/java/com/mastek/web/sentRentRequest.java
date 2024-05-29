package com.mastek.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mastek.bean.TempRent;
import com.mastek.dao.TempRentDao;

/**
 * Servlet implementation class sentRentRequest
 */
@WebServlet("/sentRentRequest")
@MultipartConfig
public class sentRentRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sentRentRequest() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		TempRent rentInfo = new TempRent();
		
		

		System.out.println(request.getParameter("agentId"));
		rentInfo.setProperty_id_fk(Integer.parseInt(request.getParameter("propertyId")));
		System.out.println(request.getParameter("propertyId"));

		rentInfo.setUser_tenant_id(Integer.parseInt(request.getParameter("userId")));

		rentInfo.setAgent_landlord_id(Integer.parseInt(request.getParameter("agentId")));

		System.out.println(request.getParameter("agentId"));

		rentInfo.setRent(Double.parseDouble(request.getParameter("rent")));
		rentInfo.setStartDateString(request.getParameter("startDate"));
		rentInfo.setEndDateString(request.getParameter("endDate"));

		Part imagePart1 = request.getPart("aadharPhoto");
		String fileName1 = saveImageToFileSystem(imagePart1);
		rentInfo.setAdharcard(fileName1);
		TempRentDao rentInfoDAO = new TempRentDao();
		Boolean status = rentInfoDAO.insertTempRent(rentInfo);
		if (status) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<script>");
			out.println("alert('localhost says data inserted successfully');");
			out.println("window.location.href = 'index.jsp';"); // Redirect to home page
			out.println("</script>");
			out.println("</body></html>");
		} else {
			// Handle data insertion failure
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to insert data.");
		}

		System.out.println("Success");
	}

	private String saveImageToFileSystem(Part filePart) throws IOException {
		String uploadDirectory = "";
		String fileName = "";
		int count = 1;

		uploadDirectory = "/Users/mohijeet/eclipse-dynamic/FINAL_MASTEK/src/main/webapp/Tenant_aadharCard"; // Update
																																			// this
																																			// path
		fileName = "Tenant_Adharcard" + System.currentTimeMillis() + "_" + filePart.getSubmittedFileName() + count
				+ getExtension(filePart); // Generate a unique filename

		try (InputStream inputStream = filePart.getInputStream();
				OutputStream outputStream = new FileOutputStream(uploadDirectory + File.separator + fileName)) {

			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		}

		count++;
		return uploadDirectory + "\\" + fileName;
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
