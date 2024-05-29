<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>    
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Property Details</title>
   <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/propertyDetail.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    
</head>

<body>
	<%
	String userRole = (String) session.getAttribute("userRole");
	%>

		<jsp:include page="navbar.jsp" />
		
		
		<div class="row no-gutters">
		<div class="col-md-6 mt-5">
			<div class="property-details slideshow-container">
				<div class="rent-box">For ${propertyDesc.purpose}</div>

				<c:forEach items="${propertyDesc.images}" var="image">
					<img src="${image.url}" class="mySlides" alt="Property Image">
				</c:forEach>
				
				<a class="prev" onclick="plusSlides(-1)">&#10094;</a> <a
					class="next" onclick="plusSlides(1)">&#10095;</a>
			</div>
		</div>
		<div class="col-md-6 mt-5">
			<div class="property-details">
				<div class="property-info">
					<h2 class="text-white">Property Information</h2>
					<a href="property-list.jsp">
						<div class="back-arrow">
							<i class="fa fa-arrow-left" style="color: white"
								aria-hidden="true"></i>
							<div class="tooltip">Back To Home</div>
						</div>
					</a>

					<div class="table-responsive">
						<table class="table text-white">
							<tbody>
								<tr>

									<td><strong>Property type:</strong></td>
									<td><Strong> ${propertyDesc.propertyType}</Strong></td>
								</tr>
								<tr>
									<td><strong>Features:</strong></td>
									<td><strong>${propertyDesc.features}</strong></td>
								</tr>

								<tr>
									<td><strong>Size:</strong></td>
									<td><strong>${propertyDesc.proSize} sqft</strong></td>
								</tr>
								<tr>
									<td><strong>Rooms:</strong></td>
									<td><Strong>${propertyDesc.noOfRooms}</Strong></td>
								</tr>
								<tr>
									<td><strong>Kitchens:</strong></td>
									<td><strong>${propertyDesc.noOfKitchens}</strong></td>
								</tr>
								<tr>
									<td><strong>Bathrooms:</strong></td>
									<td><strong>${propertyDesc.noOfBathrooms}</strong></td>
								</tr>
								<tr>
									<td><strong>price:</strong></td>
									<td><strong>${propertyDesc.price} Rs</strong></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
		

    <div class="location-info">
        <h2 class="text-white">Amenities</h2>
        
        <div class="amenity-box ">
              <c:forEach var="amenity" items="${amenities}">
                 <div class="amenity"><strong><c:out value="${amenity}"/></strong></div>
              
     	  	  </c:forEach>
     	
        </div>
    </div>
    <div class="location-info">
        <h2 class="text-white">Location Information</h2>
        <div class="amenity-box">
            <div class="amenity"><strong>Society: ${propertyDesc.address.society}</strong></div>
            <div class="amenity"><strong>City: ${propertyDesc.address.city}</strong></div>
            <div class="amenity"><strong>State:  ${propertyDesc.address.state}</strong></div>
            <div class="amenity"><strong>Pin Code: ${propertyDesc.address.pincode}</strong></div>
        </div>
    </div>
    
    
    
		<div class="mt-4">
		    <c:if test="${userRole eq 'user' || userRole eq 'tenant'}">
		
			<form id="bookAppointment" action="getAppointment.jsp" method="post" class="mt-2"  style="display: inline;">
		        <input type="hidden" name="propertyId" value="${propertyDesc.propertyId}">
		        <input type="hidden" name="agentId" value="${propertyDesc.owner.userId}">
		        <input type="hidden" name="rent" value="${propertyDesc.price}">
		        <!-- Add more form fields as needed -->
			    	<button type="submit" class="btn btn-primary" style="border-radius: 20px;">Book an Appointment</button>
		    </form>
			
		    <form id="rentApplicationForm" action="rent-applications.jsp" method="post" class="mt-2"  style="display: inline;">
		        <input type="hidden" name="propertyId" value="${propertyDesc.propertyId}">
		        <input type="hidden" name="agentId" value="${propertyDesc.owner.userId}">
		        <input type="hidden" name="rent" value="${propertyDesc.price}">
		
		        <c:if test="${propertyDesc.purpose eq 'Rent'}">
		            <button type="submit" class="btn btn-primary" style="border-radius: 20px;">Send Rent Application</button>
		        </c:if>
		    </form>
		  </c:if>
		       
		</div>
	

    
    
    <script>
        function goBack() {
            window.history.back();
        }
    </script>

    <script>
        var slideIndex = 1;
        showSlides(slideIndex);

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            var i;
            var slides = document.getElementsByClassName("mySlides");
            if (n > slides.length) { slideIndex = 1 }
            if (n < 1) { slideIndex = slides.length }
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slides[slideIndex - 1].style.display = "block";
        }
        
        
        function redirectToRentApplications(propertyId) {
			// Redirect to rent-applications.jsp with id=1 as a parameter
			var url = 'rent-applications.jsp?propertyId=' + propertyId;
			window.location.href = url;
		}
        
    </script>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>