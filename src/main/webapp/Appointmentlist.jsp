<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mastek.bean.Appointment"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointment Statuses</title>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

</head>
<body>
	<%
	Integer userId = (Integer) session.getAttribute("userId");
	String userRole = (String) session.getAttribute("userRole");
	%>


	<jsp:include page="navbar.jsp" />

	<div class="container-xxl">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-10">
						<div id="loadingOverlay"
							style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 9999;">
						<div
							style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); color: #fff; font-size: 24px;">
							<div id="statusMessage" class="text-success bg-white"
								style="display: none; font-weight: 500; font-size: 20px; margin-left: 30px">
							</div>

						</div>
					</div>
					<h2 class="text-center mb-4">My Appointments</h2>
					<c:set var="count" value="0" />
					<div class="row">
						<c:forEach items="${appointments}" var="appointment">
							<div class="col-md-4">
								<div class="card">
									<div class="card-header bg-primary text-white text-center">
										<b>Appointment Number ${count = count + 1}</b>
									</div>
									<div class="card-body">
										<table class="table">
											<tr>
												<td><strong>User ID:</strong></td>
												<td>${appointment.userId}</td>
											</tr>
											<tr>
												<td><strong>Agent ID:</strong></td>
												<td>${appointment.agentId}</td>
											</tr>
											<tr>
												<td><strong>Property ID:</strong></td>
												<td>${appointment.property_id_fk}</td>
											</tr>
											<tr>
												<td><strong>Appointment Date:</strong></td>
												<td>${appointment.appDate}</td>
											</tr>
										</table>
									</div>
									<c:if test="${userRole eq 'user'|| userRole eq 'tenant'}">
										<c:if test="${appointment.appStatus eq 'Pending'}">
											<button class="btn btn-warning">${appointment.appStatus}</button>
										</c:if>
										<c:if test="${appointment.appStatus eq 'Reject'}">
											<button class="btn btn-danger">${appointment.appStatus}</button>
										</c:if>
										<c:if test="${appointment.appStatus eq 'Approved'}">
											<button class="btn btn-success">${appointment.appStatus}</button>
										</c:if>
									</c:if>
								<div class="row">
								<c:if test="${userRole eq 'agent' || userRole eq 'landlord'}">
									
									<form class="form-appointment" action="AppointmentStatus" method="post">
									<input type="hidden" class="tenantId" name="tenantId" value="${appointment.userId}">
								    <input type="hidden" class="agentId" name="agentId" value="${appointment.agentId}">
								    <input type="hidden" class="propertyId" name="propertyId" value="${appointment.property_id_fk}">
								    <input type="hidden" class="appDate" name="appDate" value="${appointment.appDate}">
								    <input type="hidden" class="appStatus" name="appStatus" value="${appointment.appStatus}">
										
									
										<input type="hidden" class="appointmentId" name="appointmentId" value="${appointment.appointmentId}">
									 
								     	<button type="submit" name="action" value="Approved" class="btn btn-success mb-2 col-12 ">Accept</button>
	                                 	<button type="submit" name="action" value="Reject" class="btn btn-danger col-12">Reject</button>

									</form>
								</c:if>
								</div>

								</div>
							</div>
						</c:forEach>
					</div>
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
         $('.form-appointment').submit(function(event) {
             event.preventDefault(); // Prevent the default form submission

             var form = $(this);
             var appointmentId = form.find('input[name="appointmentId"]').val();
             
             var tenantId = form.find('input[name="tenantId"]').val();
             var agentId = form.find('input[name="agentId"]').val();
             var propertyId = form.find('input[name="propertyId"]').val();
             var appDate = form.find('input[name="appDate"]').val();
             var appStatus = form.find('input[name="appStatus"]').val();

             var status = form.find('button[type="submit"]:focus').val(); // Get the value of the focused button

             $.ajax({
                 type: 'POST',
                 url: form.attr('action'),
                 data: { 
                	 
                	 appointmentId: appointmentId,
                	 
                	 tenantId:tenantId,
                	 agentId:agentId,
                	 propertyId:propertyId,
                	 appDate:appDate,
                	 appStatus:appStatus,
                	 
                	 action: status 
                	 
                 
                 }, // Include the button value as 'action'
                 success: function(response) {
                     var message = '';
                     if (status === 'Approved') {
                         message = 'Appointment Accepted';
                         showStatusMessage(message, true);

                     } else if (status === 'Reject') {
                         message = 'Appointment Rejected';
                         showStatusMessage(message, false);


                     }
                     setTimeout(function() {
                         location.reload();
                     }, 2000);
                 },
                 error: function(xhr, status, error) {
                     console.error('Error:', error);
                     showStatusMessage('Error occurred', false);
                 }
             });
         });

         function showStatusMessage(message, isSuccess) {
             var statusMessage = $('#statusMessage');
             statusMessage.text(message);
         	$('#loadingOverlay').show();

             if (isSuccess) {
                 statusMessage.removeClass('text-danger').addClass('text-success');
             } else {
                 statusMessage.removeClass('text-success').addClass('text-danger');
             }
             statusMessage.fadeIn('slow').delay(1000).fadeOut('slow');
         }
     });
 
	
	</script>
</body>
</html>
