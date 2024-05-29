package com.mastek.bean;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TempRent {
	
	private int rentId;
    private int property_id_fk;  // refering property table property id
	
    private int user_tenant_id;  //referent user tables userid
    private int agent_landlord_id; //referent user tables userid
    private String firstname;
    private String mobilenum;
    private String email;
    private double rent;
    private Date startDate;
    private Date endDate;
    private String adharcard;
    private String app_status;
    
    public TempRent() {
		super();
	}
    
	public TempRent(Property property, User user_tenant_id, User agent_landlord_id, String firstname, String mobilenum,
			String email, double rent, Date startDate, Date endDate, String adharcard,String app_status) {
		super();
		this.property_id_fk = property.getPropertyId();
		this.user_tenant_id = user_tenant_id.getUserId();
		this.agent_landlord_id = agent_landlord_id.getUserId();
		this.firstname = firstname;
		this.mobilenum = mobilenum;
		this.email = email;
		this.rent = rent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adharcard = adharcard;
		this.app_status = app_status;
	}
	
	
	public TempRent(int rentId, Property property, User user_tenant_id, User agent_landlord_id, String firstname,
			String mobilenum, String email, double rent, Date startDate, Date endDate, String adharcard,String app_status) {
		super();
		this.rentId = rentId;
		this.property_id_fk = property.getPropertyId();
		this.user_tenant_id = user_tenant_id.getUserId();
		this.agent_landlord_id = agent_landlord_id.getUserId();
		this.firstname = firstname;
		this.mobilenum = mobilenum;
		this.email = email;
		this.rent = rent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adharcard = adharcard;
		this.app_status = app_status;
	}
	public int getRentId() {
		return rentId;
	}
	public void setRentId(int rentId) {
		this.rentId = rentId;
	}
	
	
	public int getProperty_id_fk() {
		return property_id_fk;
	}

	public void setProperty_id_fk(int property_id_fk) {
		this.property_id_fk = property_id_fk;
	}
	
	

	
	
	
	public String getApp_status() {
		return app_status;
	}

	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}

	public int getUser_tenant_id() {
		return user_tenant_id;
	}

	public void setUser_tenant_id(int user_tenant_id) {
		this.user_tenant_id = user_tenant_id;
	}

	public int getAgent_landlord_id() {
		return agent_landlord_id;
	}

	public void setAgent_landlord_id(int agent_landlord_id) {
		this.agent_landlord_id = agent_landlord_id;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMobilenum() {
		return mobilenum;
	}
	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAdharcard() {
		return adharcard;
	}
	public void setAdharcard(String adharcard) {
		this.adharcard = adharcard;
	}
    
    
	

	
	// this is for setting date in string 
	public void setStartDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date) dateFormat.parse(dateString);
            this.startDate = date;
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the ParseException
        }
    }

	// set end date in string
	public void setEndDateString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = (Date) dateFormat.parse(dateString);
            this.endDate = date;
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the ParseException
        }
    }

	@Override
	public String toString() {
		return "TempRent [rentId=" + rentId + ", property_id_fk=" + property_id_fk + ", user_tenant_id="
				+ user_tenant_id + ", agent_landlord_id=" + agent_landlord_id + ", firstname=" + firstname
				+ ", mobilenum=" + mobilenum + ", email=" + email + ", rent=" + rent + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", adharcard=" + adharcard + "]";
	}
    
    

	

}
