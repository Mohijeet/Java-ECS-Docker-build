package com.mastek.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mastek.bean.PropertyAddress;
import com.mastek.bean.Rent;
import com.mastek.bean.User;
import com.mastek.dao.ConnectionManager;
import com.mastek.dao.PropertyAddressDAO;
import com.mastek.dao.RentDao;
import com.mastek.dao.UserDao;
import com.mastek.services.MailServices;
import com.mastek.services.PdfServices;

/**
 * Servlet implementation class Approve_Reject_AppServlet
 */
@WebServlet("/Approve_Reject_AppServlet")
public class Approve_Reject_AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Approve_Reject_AppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int propertyId = Integer.parseInt(request.getParameter("propertyId"));
        String firstName = request.getParameter("firstName");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        double rent = Double.parseDouble(request.getParameter("rent"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String adharCard = request.getParameter("adharCard");
        String status = request.getParameter("status");
		int userTenantId = Integer.parseInt(request.getParameter("userTenantId"));
		int agentLandlordId = Integer.parseInt(request.getParameter("agentLanlordId"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userObj");
		String landlordname =  user.getFirstName();
		String agentEmail = user.getEmail();


        // Perform necessary operations, such as updating the application status in the database
        // For demonstration purposes, let's just print the received parameters
        System.out.println("Property ID: " + propertyId);
        System.out.println("Agent First Name: " + firstName);
        System.out.println("Agent Mobile: " + mobile);
        System.out.println("Agent Email: " + email);
        System.out.println("Property Rent: " + rent);
        System.out.println("Rent Start Date: " + startDate);
        System.out.println("Rent End Date: " + endDate);
        System.out.println("Adhar Card: " + adharCard);
        System.out.println("Status: " + status);
        System.out.println("user tanant id "+userTenantId);
        System.out.println("agent landlord id "+agentLandlordId);
        System.out.println("aadhar card "+adharCard);
        System.out.println("user tenant id"+ userTenantId);
        System.out.println("agent landlord id"+ agentLandlordId);



        
        
        try {
        	
        	final String updateTempRent = "update tbl_temp_rent_info set app_status = ? where property_id_fk = ?";
        			
        			
        	Connection connection = ConnectionManager.getConnection();
        	PreparedStatement psf = connection.prepareStatement(updateTempRent);
        	psf.setString(1, status);
        	psf.setInt(2, propertyId);
        	
        	int i = psf.executeUpdate();
        	
        	
        	
        	if(i > 0) {
        		System.out.println("inserted in temp rent successfully");
        		
        		if(status.equals("approved")) {
        			Rent rentTable = new Rent();
            		rentTable.setRent(rent);
            		rentTable.setStartDate(LocalDate.parse(startDate));
            		rentTable.setEndDate(LocalDate.parse(endDate));
            		
            		PropertyAddressDAO propertyaddressdao = new PropertyAddressDAO();
    				PropertyAddress propertyaddress = propertyaddressdao.getAddressById(propertyId);
    				System.out.println("address:" + propertyaddress.toString());

    				PdfServices pdfservices = new PdfServices();
    				String lease_doc = pdfservices.createLeasePDF( firstName ,landlordname , propertyaddress, rentTable,adharCard);
    				
    				String aadhar_card = "D:\\MASTEK PROJECT\\Main Project Files\\MastekProjectG15\\src\\main\\webapp\\"+adharCard;

            		RentDao rd = new RentDao();
            		boolean result = rd.insertRent(propertyId, userTenantId, agentLandlordId, rentTable,lease_doc,aadhar_card);
    	        	MailServices sendmail = new MailServices();
    	        	
    	        	
    	        	UserDao userdao = new UserDao();
    	        	User userDetails = userdao.selectUserById(userTenantId);
    	        	String tenantmail = userDetails.getEmail();
    	        	//String name = userDetails.getFirstName();
    	        	
    	        	//String tenantmail = userdao.selectUserById(userTenantId);
    	        	System.out.print("mail:"+tenantmail);
    	        	System.out.print("mail:"+agentEmail);
    	        	sendmail.sendEmail(tenantmail, agentEmail,firstName,"Rent Application Approved", lease_doc);
            		if(result) System.out.println("inserted in rent info");
            		else System.out.println("failed to insert rent info");
        		}
        		else {
        			
        			System.out.println("Your Application is Rejected ");
        			
        		}
	
        	}
        	else {
                System.out.println("Status update failed");

        	}
        	
        	
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
        // You can send a response back if needed

}
}