package com.mastek.bean;

import java.util.List;

public class Property {


    private int propertyId;
    private User owner; 
    private String propertyType;
    private String proSize;
    private double price;
    private String features;
    private int noOfRooms;
    private int noOfKitchens;
    private int noOfBathrooms;
    private String amenities;
    private String status;
    private String purpose;
    private List<PropertyImage> images; // List of property images
    private PropertyAddress address; // Single address
    private PropertyDocument document; // Single Document
//    private Rent rent;
//    private Sell sell;
//    

    public Property() {
    }

    public Property(User owner, String propertyType, String proSize, double price, String features, int noOfRooms,
                     int noOfKitchens, int noOfBathrooms, String amenities, String status, String purpose, PropertyAddress address, List<PropertyImage> images,PropertyDocument document) {
        this.owner = owner;
        this.propertyType = propertyType;
        this.proSize = proSize;
        this.price = price;
        this.features = features;
        this.noOfRooms = noOfRooms;
        this.noOfKitchens = noOfKitchens;
        this.noOfBathrooms = noOfBathrooms;
        this.amenities = amenities;
        this.status = status;
        this.purpose = purpose;
        this.address = address;
        this.images = images;
        this.document = document;
    }

    public Property(int propertyId, User owner, String propertyType, String proSize, double price, String features,
                     int noOfRooms, int noOfKitchens, int noOfBathrooms, String amenities, String status, String purpose, PropertyAddress address, List<PropertyImage> images,PropertyDocument document) {
        this.propertyId = propertyId;
        this.owner = owner;
        this.propertyType = propertyType;
        this.proSize = proSize;
        this.price = price;
        this.features = features;
        this.noOfRooms = noOfRooms;
        this.noOfKitchens = noOfKitchens;
        this.noOfBathrooms = noOfBathrooms;
        this.amenities = amenities;
        this.status = status;
        this.purpose = purpose;
        this.address = address;
        this.images = images;
        this.document = document;

    }

    
    
		
	//	{	    	this.agent_id_fk = owner.getUserId();}

		public User getOwner() {
		return owner;
	}
		
		

	public int getPropertyId() {
			return propertyId;
		}

		public void setPropertyId(int propertyId) {
			this.propertyId = propertyId;
		}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getProSize() {
		return proSize;
	}

	public void setProSize(String proSize) {
		this.proSize = proSize;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public int getNoOfKitchens() {
		return noOfKitchens;
	}

	public void setNoOfKitchens(int noOfKitchens) {
		this.noOfKitchens = noOfKitchens;
	}

	public int getNoOfBathrooms() {
		return noOfBathrooms;
	}

	public void setNoOfBathrooms(int noOfBathrooms) {
		this.noOfBathrooms = noOfBathrooms;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public List<PropertyImage> getImages() {
		return images;
	}

	public void setImages(List<PropertyImage> images) {
		this.images = images;
	}

	public PropertyAddress getAddress() {
		return address;
	}

	public void setAddress(PropertyAddress address) {
		this.address = address;
	}

	public PropertyDocument getDocument() {
		return document;
	}

	public void setDocument(PropertyDocument document) {
		this.document = document;
	}

		@Override
	    public String toString() {
	        return "Property{" +
	                "propertyId=" + propertyId +
	                ", owner=" + owner +
	                ", propertyType='" + propertyType + '\'' +
	                ", proSize='" + proSize + '\'' +
	                ", price=" + price +
	                ", features='" + features + '\'' +
	                ", noOfRooms=" + noOfRooms +
	                ", noOfKitchens=" + noOfKitchens +
	                ", noOfBathrooms=" + noOfBathrooms +
	                ", amenities='" + amenities + '\'' +
	                ", status='" + status + '\'' +
	                ", purpose='" + purpose + '\'' +
	                '}';
	    }
		
		

	}

	