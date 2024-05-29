package com.mastek.bean;

import java.time.LocalDate;

public class Rent {
	
	private int rent_Id;
	private double rent;
	private LocalDate startDate;
    private LocalDate endDate;
    private RentDocument rentDocument;
    
    
    
    
	public Rent() {}
	
	

	public Rent(double rent, LocalDate startDate, LocalDate endDate) {
		super();
		this.rent = rent;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Rent(double rent, LocalDate startDate, LocalDate endDate, RentDocument rentDocument) {
		super();
		this.rent = rent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentDocument = rentDocument;
	}


	
	public Rent(int rent_Id, double rent, LocalDate startDate, LocalDate endDate, RentDocument rentDocument) {
		super();
		this.rent_Id = rent_Id;
		this.rent = rent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.rentDocument = rentDocument;
	}

	



	public int getRent_Id() {
		return rent_Id;
	}
	public void setRent_Id(int rent_Id) {
		this.rent_Id = rent_Id;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate localDate) {
		this.startDate = localDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public RentDocument getRentDocument() {
		return rentDocument;
	}
	public void setRentDocument(RentDocument rentDocument) {
		this.rentDocument = rentDocument;
	}
    
    
    
    

    
    
}
