<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="com.mastek.bean.TempRent"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Application Status</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
	<div class="container-xxl">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<h2 class="text-center mb-4">My Applications</h2>
					<c:set var="count" value="0" />

					<div class="row">
						<c:forEach items="${tenantAppInfo}" var="rentinfo">
							<div class="col-md-4">
								<div class="card">
									<div class="card-header bg-primary text-white text-center"><b>Application Number ${count = count + 1}</b></div>
									<div class="card-body">
										<table class="table">
											        <tr>
											            <td><strong>Property Number:</strong></td>
											            <td>${rentinfo.property_id_fk}</td>
											        </tr>
											        <tr>
											            <td><strong>Agent First Name:</strong></td>
											            <td>${rentinfo.firstname}</td>
											        </tr>
											        <tr>
											            <td><strong>Agent Mobile:</strong></td>
											            <td>${rentinfo.mobilenum}</td>
											        </tr>
											        <tr>
											            <td><strong>Agent Email:</strong></td>
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
											            <td colspan="2">
											                <img src="${rentinfo.adharcard}" height="200" width="300" />
											            </td>
											        </tr>
											    </table>
											</p>
										
										
									</div>
									<c:if test="${rentinfo.app_status eq 'pending'}">
                                        <button class="btn btn-warning">${rentinfo.app_status}</button>
                                    </c:if>
                                    <c:if test="${rentinfo.app_status eq 'reject'}">
                                        <button class="btn btn-danger">${rentinfo.app_status}</button>
                                    </c:if>
                                    <c:if test="${rentinfo.app_status eq 'approved'}">
                                        <button class="btn btn-success">${rentinfo.app_status}</button>
                                    </c:if>
								</div>
							</div>
							<!-- Add more columns and cards as needed -->
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


	

</body>
</html>