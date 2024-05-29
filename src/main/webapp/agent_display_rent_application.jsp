<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mastek.bean.TempRent"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<title>Property Lords</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="keywords">
<meta content="" name="description">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

<link href="img/favicon.ico" rel="icon">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&link=swap"
	rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<link href="css/bootstrap.min.css" rel="stylesheet">

<link href="css/style.css" rel="stylesheet">

<style>
.body {
	font-family: 'Heebo', sans-serif;
	background-color: #f6f9fc; /* Light blue background */
}

.container {
	padding: 50px 0;
	width:auto; /* Add some top and bottom padding */
}

.h2 {
	color: #311b1b; /* Darker text for headings */
}

.card {
	border-radius: 15px; /* Rounded corners */
	border: 2px solid #3d89cc; /* Solid border */
	margin-bottom: 20px; /* Add space between cards */
	transition: transform 0.3s ease; /* Add transition */
	width: 350px; /* Set card width */

}

.card:hover {
	transform: translateY(-10px); /* Move card up on hover */
}

.card-header {
	color: #188fff; /* White text for header */
	border-radius: 15px 15px 0 0; /* Rounded corners only at top */
	padding: 10px; /* Adjusted padding */
	font-weight: bold;
	font-size: 16px; /* Adjusted font size */
	border-bottom: 2px solid #cdd8e1;
	/* Solid border at bottom of header */
	text-align: center; /* Center align header text */
}

.card-body {
	padding: 20px; /* Increase padding for readability */
}

.badge {
	color: #fff; /* White text for badges */
}

.badge-pending {
	background-color: #ffc107; /* Yellow for pending */
}

.badge-accepted {
	background-color: #11b437; /* Green for accepted */
}

.badge-rejected {
	background-color: #db1327; /* Cherry red for rejected */
}

.btn {
	margin-top: 10px;
	border: solid rgb(205, 205, 228);
	border-radius: 1px;
	width: 100%; /* Make buttons full width */
	border-radius: 10px;
	font-size: 14px; /* Adjust font size */
	transition: background-color 0.3s; /* Add transition */
}

.btn.accepted {
	background-color: #11b437; /* Green background for accepted */
	color: #fff; /* White text for accepted */
}

.btn.rejected {
	background-color: #d41023; /* Red background for rejected */
	color: #f9f9f9; /* White text for rejected */
	text: bold
}

.btn:hover {
	background-color: #4ab0ef; /* Blue background on hover */
}
</style>
</head>

<body>
	<jsp:include page="navbar.jsp" />
	
	
			<div class="container">
				<div class="row justify-content-center">
								
					<div class="col-lg-12">
						   <div id="loadingOverlay" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 9999;">
	    					<div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); color: #fff; font-size: 24px;">
	        				<div id="statusMessage" class="text-success bg-white" style="display: none; font-weight:500; font-size:20px; margin-left:30px"></div>
	        				
	    					</div>
							</div>
						<h2 class="text-center mb-5">Tenant Applications</h2>
						
						<c:set var="count" value="0" />
	
						<div class="row">
							<c:forEach items="${rentInfo}" var="rentinfo">
								<div class="col-md-4">
									<div class="card" style="margin-left:20px">
										<div class="card-header bg-primary text-white text-center">
											<b>Application Number ${count = count + 1}</b>
										</div>
										<div class="card-body">
											<table class="table">
												<tr>
													<td><strong>Property Number:</strong></td>
													<td>${rentinfo.property_id_fk}</td>
													
												</tr>
												<tr>
													<td><strong>Tenant Name:</strong></td>
													<td>${rentinfo.firstname}</td>
												</tr>
												<tr>
													<td><strong>Tenant Mobile:</strong></td>
													<td>${rentinfo.mobilenum}</td>
												</tr>
												<tr>
													<td><strong>Tenant Email:</strong></td>
													<td>${rentinfo.email}</td>
												</tr>
												<tr>
													<td><strong>Property Rent:</strong></td>
													<td>${rentinfo.rent}</td>
												</tr>
												<tr>
													<td><strong>Rent Start Date:</strong></td>
													<td>${rentinfo.startDate}</td>
												</tr>
												<tr>
													<td><strong>Rent End Date:</strong></td>
													<td>${rentinfo.endDate}</td>
												</tr>
												<tr>
													<td colspan="2"><img src="${rentinfo.adharcard}"
														height="200" width="300" /></td>
												</tr>
											</table>
	
										
										</div>
										<input type="hidden" class="userTenantId" value="${rentinfo.user_tenant_id}">
										<input type="hidden" class="agentLanlordId" value="${rentinfo.agent_landlord_id}">
	
										
										<button class="btn btn-success approve">Approve</button>
										<button class="btn btn-danger reject">Reject</button>
									</div>
								</div>
								<!-- Add more columns and cards as needed -->
							</c:forEach>
						</div>
	
					</div>
				</div>
			</div>

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

	<script>
    $(document).ready(function() {
    	 $('.btn.approve').click(function() {
             var card = $(this).closest('.card');
             var propertyId = card.find('td:nth-child(2)').eq(0).text().trim();
             var firstName = card.find('td:nth-child(2)').eq(1).text().trim();
             var mobile = card.find('td:nth-child(2)').eq(2).text().trim();
             var email = card.find('td:nth-child(2)').eq(3).text().trim();
             var rent = card.find('td:nth-child(2)').eq(4).text().trim();
             var startDate = card.find('td:nth-child(2)').eq(5).text().trim();
             var endDate = card.find('td:nth-child(2)').eq(6).text().trim();
             var adharCard = card.find('img').attr('src').trim();
             var userTenantId = $(this).closest('.card').find('.userTenantId').val(); // Fetching userTenantId relative to the clicked button
             var agentLanlordId = $(this).closest('.card').find('.agentLanlordId').val();

	

             var data = {
                 propertyId: propertyId,
                 firstName: firstName,
                 mobile: mobile,
                 email: email,
                 rent: rent,
                 startDate: startDate,
                 endDate: endDate,
                 adharCard: adharCard,
                 status: 'approved', // or 'rejected' based on the button clicked
                 userTenantId : userTenantId,
                 agentLanlordId : agentLanlordId
                 
                 
             };
             

            $(this).closest('.card-body').find('.btn.reject').attr('disabled', true);
            $(this).removeClass('btn accept').addClass('btn approved').text('Approved').attr('disabled', true);
                updateApplicationStatus(data,true);
            
            
		         
                //updateApplicationStatus(applicationNumber, 'approved');
        });

        $('.btn.reject').click(function() {
            //var propertyId = $(this).closest('.card').find('.property-id').text();
            var card = $(this).closest('.card');
            var propertyId = card.find('td:nth-child(2)').eq(0).text().trim();
            var firstName = card.find('td:nth-child(2)').eq(1).text().trim();
            var mobile = card.find('td:nth-child(2)').eq(2).text().trim();
            var email = card.find('td:nth-child(2)').eq(3).text().trim();
            var rent = card.find('td:nth-child(2)').eq(4).text().trim();
            var startDate = card.find('td:nth-child(2)').eq(5).text().trim();
            var endDate = card.find('td:nth-child(2)').eq(6).text().trim();
            var adharCard = card.find('img').attr('src').trim();
            var userTenantId = $(this).closest('.card').find('.userTenantId').val(); // Fetching userTenantId relative to the clicked button
            var agentLanlordId = $(this).closest('.card').find('.agentLanlordId').val();


            var data = {
                propertyId: propertyId,
                firstName: firstName,
                mobile: mobile,
                email: email,
                rent: rent,
                startDate: startDate,
                endDate: endDate,
                adharCard: adharCard,
                status: 'reject', // or 'rejected' based on the button clicked
                userTenantId : userTenantId,
                agentLanlordId : agentLanlordId
            };
             
             $(this).closest('.card-body').find('.btn.approve').attr('disabled', true);
				// Change class and text of the reject button
				$(this).removeClass('btn reject').addClass('btn rejected').text('Rejected').attr('disabled', true);
            updateApplicationStatus(data,false);
            
            
		          
            //updateApplicationStatus(applicationNumber, 'reject');	
        });

        function updateApplicationStatus(data,status) {
        	
        	$('#loadingOverlay').show();
        	
            $.ajax({
                type: 'POST',
                url: 'Approve_Reject_AppServlet', 
                data: data,
                success: function(response) {
                	 var message = 'Application Accepted.';
                	 var failedmessage = "Application Rejected"
                	 
                	 if(status) showStatusMessage(message, status);
                	 else showStatusMessage(failedmessage, status);

                	 setTimeout(function() {
                         location.reload();
                     }, 3000); 
                   
                },
                error: function(xhr, status, error) {	
                    console.error('Error:', error);
                }
            });
        }
    });
    
    
    function showStatusMessage(message, isSuccess) {
        var statusMessage = $('#statusMessage');
        statusMessage.text(message);
        if (isSuccess) {
            statusMessage.removeClass('text-danger').addClass('text-success');
        } else {
            statusMessage.removeClass('text-success').addClass('text-danger');
        }
        statusMessage.fadeIn('slow').delay(1000).fadeOut('slow');
    }
    
   
</script>
	
	</body>
	</html>