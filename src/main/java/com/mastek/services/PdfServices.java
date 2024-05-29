package com.mastek.services;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.mastek.bean.PropertyAddress;
import com.mastek.bean.Rent;
import com.mastek.dao.RentDao;

public class PdfServices {
	public String createLeasePDF(String tenant , String landlord , PropertyAddress address , Rent rent , String addharcard ) {
        String party1 = landlord;
        String party2 = tenant;
        String city = address.getCity();
        String society = address.getSociety();
        String state = address.getState();
        String pincode = address.getPincode();
        LocalDate startDate =  rent.getStartDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedStartDate = startDate.format(formatter);
        System.out.println("Formatted Start Date: " + formattedStartDate);
        
        LocalDate endDate =  rent.getEndDate();
        String formattedEndDate = endDate.format(formatter);
        System.out.println("Formatted Start Date: " + formattedStartDate);

        // Generate lease document content
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = sdf.format(new Date());
        String leaseContent = "Lease Agreement\n\n" 
                            + "Parties:\n" 
                            + "Landlord: " + party1 + "\n" 
                            + "Tenant: " + party2 + "\n" 
                            + "Address: \n" + "Society: "+society+ " , City: "+ city + " , State: "+ state + " , Pincode: "+ pincode + "\n\n" 
                            + "Term of Lease:\n" 
                            + "This lease shall commence on " + currentDate + " and shall continue for a period of one year,\n unless terminated earlier as provided herein.\n\n" 
                            + "Rent Payment:\n" 
                            + "The monthly rent for the premises shall be " + rent.getRent() + " payable in advance on the first day of each month.\n\n" 
                            + "Rules and Regulations:\n" 
                            + "1. The premises shall be used exclusively for residential purposes.\n" 
                            + "2. The tenant shall not make any alterations or improvements to the premises \n without prior written consent from the landlord.\n" 
                            + "3. Pets are not allowed on the premises without the landlord's written permission.\n\n" 
                            + "Maintenance and Repairs:\n" 
                            + "The landlord shall be responsible for maintaining the structural elements of the premises,\n  while the tenant shall be responsible for maintaining cleanliness and minor repairs.\n\n" 
                            + "Termination:\n" 
                            + "Either party may terminate this lease upon giving thirty (30) days' written notice to the other party.\n\n" 
                            +"Start Date: " + formattedStartDate + " to " + "End Date: " + formattedEndDate
                            + " \nDigital Signatures:\n" 
                            + "Landlord: [Digital Signature Here]\n" 
                            + "Tenant: [Digital Signature Here]\n";

        // Generate unique file name
        String fileName = "lease_" + System.currentTimeMillis() + ".pdf";
        String filePath = "D:\\MASTEK PROJECT\\Main Project Files\\MastekProjectG15\\src\\main\\webapp\\rent_lease_doc\\" + fileName;

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.beginText();
                contents.setFont(PDType1Font.HELVETICA, 12);
                contents.newLineAtOffset(25, 700);
                contents.setLeading(14.5f);
                
                // Manually handle line breaks and check for overflow
                String[] lines = leaseContent.split("\n");
                float leading = 14.5f; // Adjust leading (line spacing) as needed
                float maxWidth = page.getMediaBox().getWidth() - 50; // Adjusted maximum width
                for (String line : lines) {
                    float width = PDType1Font.HELVETICA.getStringWidth(line) / 1000 * 12;
                    if (width > maxWidth) { // Check for overflow
                        // Calculate the maximum characters that fit within the width
                        int maxChars = (int)((maxWidth / width) * line.length());
                        line = line.substring(0, maxChars);
                    }
                    contents.showText(line);
                    contents.newLine(); // Move to the next line
                }
                
                contents.endText();
            }
            PDPage page2 = new PDPage(PDRectangle.A4);
            doc.addPage(page2);
            PDImageXObject image = PDImageXObject.createFromFile("D:\\MASTEK PROJECT\\Main Project Files\\MastekProjectG15\\src\\main\\webapp\\"+addharcard, doc);
            try (PDPageContentStream contents2 = new PDPageContentStream(doc, page2)) {
            	contents2.beginText();
                contents2.setFont(PDType1Font.HELVETICA, 12);
                contents2.newLineAtOffset(25, 700);
                contents2.setLeading(14.5f);
                contents2.showText("Tenant Addharcard");
                contents2.endText();
                contents2.drawImage(image,100,450,200,200);
            }

            
            doc.save(filePath);
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return filePath;
    }
}
