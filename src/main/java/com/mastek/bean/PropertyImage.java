package com.mastek.bean;

public class PropertyImage {

	    private int imageId;
	    private Property property; 
	    private String Url;

	    public PropertyImage() {
	    }

	    public PropertyImage(Property property, String url) {
	        this.property = property;
	        this.Url = url;
	    }

	    public PropertyImage(int imageId, Property property, String url) {
	        this.imageId = imageId;
	        this.property = property;
	        this.Url = url;
	    }

	    // Getters and setters for all fields
	    
	    
	    public Property getProperty() {
			return property;
		}

		public void setProperty(Property property) {
			this.property = property;
		}

		public String getUrl() {
			return Url;
		}

		public void setUrl(String url) {
			Url = url;
		}
	    

	    @Override
	    public String toString() {
	        return "PropertyImage{" +
	                "imageId=" + imageId +
	                ", property=" + property +
	                ", url='" + Url + '\'' +
	                '}';
	    }

		
	}


