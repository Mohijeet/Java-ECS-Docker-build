package com.mastek.bean;
import java.sql.Timestamp;

public class Appointment {
    private int appointmentId;
    private Timestamp appDate;
    private int userId;
    private int agentId;
    private int property_id_fk; 
    private String appStatus;

    // Constructor
    public Appointment() {
    }

    // Getters and setters
    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Timestamp getAppDate() {
        return appDate;
    }

  

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

 
    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

	public void setAppDate(Timestamp appDate) {
		this.appDate=appDate;
		
	}

	public int getProperty_id_fk() {
		return property_id_fk;
	}

	public void setProperty_id_fk(int property_id_fk) {
		this.property_id_fk = property_id_fk;

	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", appDate=" + appDate + ", userId=" + userId
				+ ", agentId=" + agentId + ", property_id_fk=" + property_id_fk + ", appStatus=" + appStatus + "]";
	}
	
	
}
