<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mastek.bean.Property" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<script>
    $(document).ready(function(){
        // Function to fetch data
        function fetchData() {
            var propertyType = "<%= request.getParameter("propertyType") %>";
            var location = "<%= request.getParameter("location") %>";
            var priceRange = "<%= request.getParameter("priceRange") %>"; // Added price range parameter

            $.ajax({
                type: 'GET',
                url: 'PropertySearchServlet', // URL of your servlet
                data: { propertyType: propertyType, location: location, priceRange: priceRange }, 
                success: function(data) {
                    var html = "<div class='row'>";
                    data.forEach((property, index) => {
                        html += "<div class='col-lg-4 col-md-6 wow fadeInUp' data-wow-delay='0.1s'>";
                        html += "<div class='property-item rounded overflow-hidden'>";
                        html += "<div class='position-relative overflow-hidden'>";
                        html += "<a href='propertyDescServlet?propertyId=" + property.propertyId + "'><img class='img-fluid' src='" + property.images[0].Url + "' style='width: 450px; height: 300px;' alt=''></a>";
                        html += "<div class='bg-primary rounded text-white position-absolute start-0 top-0 m-4 py-1 px-3'>For " + property.purpose + "</div>";
                        html += "<div class='bg-white rounded-top text-primary position-absolute start-0 bottom-0 mx-4 pt-1 px-3'>" + property.propertyType + "</div>";
                        html += "</div>";
                        html += "<div class='p-4 pb-0'>";
                        html += "<h5 class='text-primary mb-3'>" + property.price + "$</h5>";
                        html += "<a class='d-block h5 mb-2' href='propertyDescServlet?propertyId=" + property.propertyId + "'>New " + property.propertyType + " For " + property.purpose + "</a>";
                        html += "<p><i class='fa fa-map-marker-alt text-primary me-2'></i>" + property.address.society + "</p>";
                        html += "</div>";
                        html += "<div class='d-flex border-top'>";
                        html += "<small class='flex-fill text-center border-end py-2'><i class='fa fa-ruler-combined text-primary me-2'></i>" + property.proSize + " sqrft</small>";
                        html += "<small class='flex-fill text-center border-end py-2'><i class='fa fa-bed text-primary me-2'></i>" + property.noOfRooms + " Room</small>";
                        html += "<small class='flex-fill text-center py-2'><i class='fa fa-bath text-primary me-2'></i>" + property.noOfBathrooms + " Bath</small>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";

                        // Add margin-bottom to every third row
                        if ((index + 1) % 3 === 0) {
                            html += "<div class='w-100' style='margin-bottom:30px'></div>"; 
                        }
                    });
                    html += "</div>";
                    $('#dataContainer').html(html);
                },
                error: function(xhr, status, error) {
                    console.error('Error:', error);
                    // Handle error if any
                }
            });
        }

        // Fetch data when the page loads
        fetchData();
        
        //setInterval(fetchData, 5000); // Fetch data every 5 seconds (for example)
    });
</script>

<div id="dataContainer">
    <!-- Fetched data will be displayed here -->
</div>


</body>
</html>
