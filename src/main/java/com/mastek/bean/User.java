package com.mastek.bean;

public class User {


	    private int userId;
	    private String firstName;
	    private String lastName;
	    private String mobileNumber;
	    private String email;
	    private String password;
	    private String role;

	    
	    public User() {};
	   
	    public User( String firstName, String lastName, String mobileNumber, String email, String password, String role) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.mobileNumber = mobileNumber;
	        this.email = email;
	        this.password = password;
	        this.role = role;
	    }

	    
	    public User(int userId, String firstName, String lastName, String mobileNumber, String email, String password, String role) {
	        this.userId = userId;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.mobileNumber = mobileNumber;
	        this.email = email;
	        this.password = password;
	        this.role = role;
	    }

	    
	    
	    
	    public int getUserId() {
			return userId;
		}




		public void setUserId(int userId) {
			this.userId = userId;
		}




		public String getFirstName() {
			return firstName;
		}




		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}




		public String getLastName() {
			return lastName;
		}




		public void setLastName(String lastName) {
			this.lastName = lastName;
		}




		public String getMobileNumber() {
			return mobileNumber;
		}




		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}




		public String getEmail() {
			return email;
		}




		public void setEmail(String email) {
			this.email = email;
		}




		public String getPassword() {
			return password;
		}




		public void setPassword(String password) {
			this.password = password;
		}




		public String getRole() {
			return role;
		}




		public void setRole(String role) {
			this.role = role;
		}




		@Override
	    public String toString() {
	        return "User{" +
	                "userId=" + userId +
	                ", firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", mobileNumber='" + mobileNumber + '\'' +
	                ", email='" + email + '\'' +
	                ", role='" + role + '\'' +
	                '}';
	    }
	}

