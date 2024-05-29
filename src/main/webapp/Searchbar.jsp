<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="java.util.List" %>
	<%@ page import="com.mastek.bean.Property" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Inter:wght@700;800&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
	<link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">

</head>
<body>

				<!-- Search Start -->
			
				<div class="container-fluid bg-primary mb-5 wow fadeIn" data-wow-delay="0.1s" style="padding: 35px;">
			    <div class="container">
			        <div class="row g-2">
			            <div class="col-md-10">
			                <div class="row g-2">
			                    <div class="col-md-4">
			                        <select id="priceRange" class="form-select border-0 py-3">
			                            <option value="1000-10000">$1000 - $10000</option>
			                            <option value="10000-20000">$10000 - $20000</option>
			                            <option value="20000-30000">$20000 - $30000</option>
			                            <option value="30000-40000">$30000 - $40000</option>
			                            <option value="40000-50000">$40000 - $50000</option>
			                            <option value="50000-100000">$50000 - $100000</option>
			                            <option value="100000-5000000"> Between 10 lakh and 50 lakhs</option>
			                        </select>
			                    </div>
			                    <div class="col-md-4">
			                        <select id="propertyType" class="form-select border-0 py-3">
			                            <option value="0">Property Type</option>
			                            <option value="Flat">Flat</option>
			                            <option value="House">House</option>
			                            <option value="Studio">Studio</option>
			                            <option value="Appartment">Appartment</option>
			                        </select>
			                    </div>
			                    <div class="col-md-4">
			                        <select id="location" class="form-select border-0 py-3">
			                            <option value="0">Location</option>
			                            <option value="ahmedabad">Ahmedabad</option>
			                            <option value="Rajkot">Rajkot</option>
			                            <option value="vadodara">Vadodara</option>
			                            <option value="Mumbai">Mumbai</option>
			                            
			                        </select>
			                    </div>
			                </div>
			            </div>
			            <div class="col-md-2">
			                <button id="searchButton" class="btn btn-dark border-0 w-100 py-3">Search</button>
			            </div>
			        </div>
			    </div>
			</div>
				
<!-- Search End -->

<!-- JavaScript to handle search button click and redirection -->
		<script>
		$(document).ready(function() {
		    // Function to handle search button click
		    $('#searchButton').click(function() {
		        // Get the selected price range, property type, and location
		        var priceRange = $('#priceRange').val() || ''; // Using logical OR operator to set empty string if null
		        var propertyType = $('#propertyType').val() || ''; // Using logical OR operator to set empty string if null
		        var location = $('#location').val() || ''; // Using logical OR operator to set empty string if null
		        
		        // Construct the URL with search parameters
		        var url = 'PropertySearchList.jsp';
		        
		        if (priceRange !== '') {
		            url += '?priceRange=' + encodeURIComponent(priceRange);
		        }
		        if (propertyType !== '') {
		            url += (priceRange !== '' ? '&' : '?') + 'propertyType=' + encodeURIComponent(propertyType);
		        }
		        if (location !== '') {
		            url += ((priceRange !== '' || propertyType !== '') ? '&' : '?') + 'location=' + encodeURIComponent(location);
		        }
		        
		        // Redirect to PropertyList.jsp with search parameters
		        window.location.href = url;
		    });
		});

</script>

</body>
</html>