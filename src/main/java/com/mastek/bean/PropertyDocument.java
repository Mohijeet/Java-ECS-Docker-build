package com.mastek.bean;

public class PropertyDocument {

    private int documentId;
    private String documentImage; // Assuming String for document data

    // Getters and setters (omitted for brevity)

    public PropertyDocument() {};
    
    
	
		    public PropertyDocument( String documentImage) {
				super();
				this.documentImage = documentImage;
			}
		    
		    public PropertyDocument(int documentId, String documentImage) {
				super();
				this.documentId = documentId;
				this.documentImage = documentImage;
			}


		
			public int getDocumentId() {
				return documentId;
			}
		
			public void setDocumentId(int documentId) {
				this.documentId = documentId;
			}
		
			
		
			public String getDocumentImage() {
				return documentImage;
			}
		
			public void setDocumentImage(String documentImage) {
				this.documentImage = documentImage;
			}
		
			@Override
		    public String toString() {
		        return "PropertyDocument{" +
		                "documentId=" + documentId +
		                ", documentImage='" + documentImage + '\'' +
		                '}';
		    }
}
