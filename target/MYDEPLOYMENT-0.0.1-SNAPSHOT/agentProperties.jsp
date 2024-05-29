<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
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
	width: auto; /* Add some top and bottom padding */
}

.h2 {
	color: #311b1b;
	text-align: center; /* Darker text for headings */
	margin-bottom: 20px; /* Add margin between title and table */
}

/* Apply general styles to the table */
table {
	width: 100%;
	border-collapse: collapse;
	border-spacing: 0;
	font-family: Arial, sans-serif;
}

/* Style the table header */
th {
	background-color: #0d6efd; /* Blue */
	color: white;
    justify-content: center !important;
    align-items: center !important;
    padding: 10px;/* Increase padding for better appearance */
}

/* Style table cells */
td {
	margin: 20px;
	padding: 10px; /* Increase padding for better appearance */
	border-bottom: 1px solid #ddd;
	text-align:center;
}

/* Define alternating row colors */
tr:nth-child(even) {
	background-color: #f2f2f2; /* Light gray */
}

tr:nth-child(odd) {
	background-color: white;
}

/* Style buttons */
.action-button {
	background-color: #007bff; /* Blue */
	border: none;
	color: white;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin-right: 5px; /* Add margin between buttons */
	cursor: pointer;
	border-radius: 20px;
	transition: background-color 0.3s; /* Add smooth transition */
	border: 1px solid #007bff;
}

.delete-button {
	background-color: #dc3545; /* Cherry red */
	border: 1px solid #dc3545;
}

.sold-button {
	background-color: #11b437; /* Light yellow */
	border: 1px solid #11b437;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<h2 class="text-center mb-5">Agent Properties</h2>

		<table cellspacing="0" class="m-20">

			<thead class=" flex align-center justify-center center">
				<tr class="justify-content-center">
					<th class=" flex justify-content-center">Property ID</th>
					<th class="flex justify-content-center">Property Type</th>
					<th class="flex justify-content-center">Status</th>
					<th class="flex justify-content-center">Purpose</th>
					<th class="flex justify-content-center">Update</th>
					<th class="flex justify-content-center">Delete</th>
					<th class="flex justify-content-center">Mark as Sold</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${agentProperties}" var="property"
					varStatus="loop">
					<tr>
						<td>${property.propertyId}</td>
						<td>${property.propertyType}</td>
						<td>${property.status}</td>
						<td>${property.purpose}</td>
						<td>
							<form action="PropertyCrud" method="post">
								<input type="hidden" name="propertyId"
									value="${property.propertyId}"> <input type="hidden"
									name="action" value="update">
								<div class="d-flex justify-content-center">
									<button
										class="btn btn-secondary action-icon-button sold-button align-center justify-center"
										type="submit">
										<i class="bi bi-pencil"></i>
									</button>
								</div>
							</form>
						</td>
						<td>
							<form action="PropertyCrud" method="post">
								<input type="hidden" name="propertyId"
									value="${property.propertyId}">
									 <input type="hidden" name="action" value="delete">
								<div class="d-flex justify-content-center">
									<button class="btn btn-danger action-icon-button delete-button"
										type="submit">
										<i class="fa fa-trash"></i>
									</button>
								</div>
							</form>
						</td>
						<td>
							<form action="PropertyCrud" method="post">
								<input type="hidden" name="propertyId"
									value="${property.propertyId}"> <input type="hidden"
									name="action" value="markassold">
								<div class="d-flex justify-content-center">
									<button class="btn btn-success action-icon-button sold-button"
										type="submit">
										<i class="fa fa-check"></i>
									</button>
								</div>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
