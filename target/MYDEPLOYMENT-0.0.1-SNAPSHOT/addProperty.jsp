<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.mastek.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Property</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Your custom CSS overrides (if any) -->

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">


<%
// Check if the user is logged in he should not be able to see add property form/page
User user = (User) session.getAttribute("userObj");
if (user == null || user.getRole().equals("user") || user.getRole().equals("tenant")) {
	response.sendRedirect("index.jsp");
	return; // Stop further execution of the page
}
%>

<!-- Your custom CSS overrides (if any) -->
<style>
/* Add your custom CSS overrides here */
.form-group label, .divider {
	color: white;
}

.divider:after, .divider:before {
	content: "";
	flex: 1;
	height: 1px;
	background: #61aced;
}

#2e53db

        .h-custom {
	height: calc(100% - 73px);
}

@media ( max-width : 450px) {
	.h-custom {
		height: 100%;
	}
}
/* Custom style for smaller form fields */
.form-group {
	margin-bottom: 1.5rem;
}

label {
	font-weight: bold;
}

.form-container {
	background: linear-gradient(135deg, #153fead7, #67d6bc);
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
}

.register-button {
	background-color: #224cbe;
	border: none;
}

.register-button:hover {
	background-color: #27d856;
}
</style>
</head>
<body>


	<jsp:include page="navbar.jsp" />



	<section class="vh-100 mt-5">
		<div class="container-fluid h-custom">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-10">
						<div class="form-container">
							<form id="propertyDetailsForm" action="PropertyServlet"
								method="post" enctype="multipart/form-data">
								<div class="row">
									<!-- First Column - Property Details -->
									<div class="col-md-6">
										<div class="divider d-flex align-items-center my-4">
											Property Details</div>
										<!-- Property Type dropdown -->
										<div class="form-group">
											<label for="propertyType">Property Type</label> <select
												id="propertyType" name="propertyType" class="form-select"
												required oninput="validateSelection(this);">
												<option value="">Select property type</option>
												<option value="House">House</option>
												<option value="Flat">Flat</option>
												<option value="Studio">Studio</option>
												<option value="Appartment">Apartment</option>
											</select>
											<div id="propertyTypeFeedback" class="text-danger"></div>
										</div>
										<!-- Property Size input -->
										<div class="form-group">
											<label for="propertySize">Property Size in Sqrft</label> <input
												type="text" id="propertySize" name="propertySize"
												class="form-control" placeholder="Enter property size"
												required oninput="validatePropertySize(this);" />
											<div id="propertySizeFeedback" class="text-danger"></div>
										</div>


										<div class="form-group">
											<label for="price">Price</label> <input type="number"
												id="price" name="price" class="form-control"
												placeholder="Enter price" min="1000" required
												oninput=" validatePrice(this, 1000, 'priceFeedback');" />
											<div id="priceFeedback" class="text-danger"></div>
										</div>
										<!-- Features input -->
										<div class="form-group">
											<label for="features">Features</label> <select id="features"
												name="features" class="form-select"
												placeholder="select feature" required
												oninput="validateSelection(this);">
												<option value="">Select property features</option>
												<option value="Semi-furnished">Semi-furnished</option>
												<option value="Fully-furnished">Fully-furnished</option>
												<option value="Non-furnished">Non-furnished</option>
											</select>
											<div id="featuresFeedback" class="text-danger"></div>
										</div>
										<!-- Number of Rooms input -->
										<div class="form-group">
											<label for="numRooms">Number of Rooms</label> <input
												type="number" id="numRooms" name="numRooms"
												class="form-control" value="1" min="1" required />
										</div>
										<!-- Number of Kitchens input -->
										<div class="form-group">
											<label for="numKitchens">Number of Kitchens</label> <input
												type="number" id="numKitchens" name="numKitchens"
												class="form-control" value="1" min="1" required />
										</div>
										<!-- Number of Bathrooms input -->
										<div class="form-group">
											<label for="numBathrooms">Number of Bathrooms</label> <input
												type="number" id="numBathrooms" name="numBathrooms"
												class="form-control" value="1" min="1" required />
										</div>
										<!-- Property Images upload input -->
										<div class="form-group">
											<label for="propertyImages">Upload Property Images</label> <input
												type="file" id="propertyImages" name="propertyImages"
												class="form-control image-upload" multiple required />
											<div id="propertyImagesError" class="text-danger"></div>
										</div>
										<div class="form-group">
											<label for="propertyDocImages">Upload Property
												Document Images</label> <input type="file" id="propertyDocImages"
												name="propertyDocImages" class="form-control image-upload"
												required />
											<div id="propertyDocImagesError" class="text-danger"></div>

										</div>

										<!-- Your other form inputs for property details go here -->
									</div>
									<!-- Second Column - Property Address Details -->
									<div class="col-md-6">
										<div class="divider d-flex align-items-center my-4">
											Property Address Details</div>

										<div class="form-group">
											<label for="landmark">Landmark</label> <input type="text"
												id="landmark" name="landmark" class="form-control"
												placeholder="Enter landmark" required
												oninput="validateInput(this, 'landmarkFeedback');" />
											<div id="landmarkFeedback" class="text-danger"></div>
										</div>

										<div class="form-group">
											<label for="society">Society</label> <input type="text"
												id="society" name="society" class="form-control"
												placeholder="Enter society" required
												oninput="validateInput(this, 'societyFeedback');" />
											<div id="societyFeedback" class="text-danger"></div>
										</div>

										<div class="form-group">
											<label for="city">City</label> <input type="text" id="city"
												name="city" class="form-control" placeholder="Enter city"
												required oninput="validateInput(this, 'cityFeedback');" />
											<div id="cityFeedback" class="text-danger"></div>
										</div>

										<div class="form-group">
											<label for="state">State</label> <input type="text"
												id="state" name="state" class="form-control"
												placeholder="Enter state" required
												oninput="validateInput(this, 'stateFeedback');" />
											<div id="stateFeedback" class="text-danger"></div>
										</div>
										<!-- Pincode input -->
										<div class="form-group">
											<label for="pincode">Pincode</label> <input type="text"
												id="pincode" name="pincode" class="form-control"
												placeholder="Enter pincode" pattern="[1-9][0-9]{5}" required
												oninput="validatePincode(this);" />
											<div id="pincodeFeedback" class="text-danger"></div>
										</div>
										<!-- Amenities input -->
										<div class="form-group">
											<label for="amenities">Amenities</label> <input type="text"
												id="amenities" name="amenities" class="form-control"
												placeholder="Enter property amenities" required
												oninput="validateAmenities(this);" />
											<div id="amenitiesFeedback" class="text-danger"></div>
										</div>
										<!-- Status input -->
										<div class="form-group">
											<label for="status">Status</label> <select id="status"
												name="status" class="form-select" required
												oninput="validateSelection(this);">
												<option value="Available">Available</option>
												<option value="Not Available">Not Available</option>
											</select>
											<div id="statusFeedback" class="text-danger"></div>
										</div>
										<!-- Purpose input -->
										<div class="form-group">
											<label for="purpose">Purpose</label> <select id="purpose"
												name="purpose" class="form-select" required
												oninput="validateSelection(this);">
												<option value="">Select purpose</option>
												<option value="Rent">Rent</option>
												<option value="Sell">Sell</option>
											</select>
											<div id="purposeFeedback" class="text-danger"></div>
										</div>

										<!-- Your form inputs for property address details go here -->
									</div>
								</div>
								<!-- Register Property Button -->
								<div class="form-group text-center">
									<button
										class="btn btn-primary btn-lg btn-block register-button">Register
										Property</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- Bootstrap Bundle with Popper (JavaScript) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		// form Validation 
		//validation for  property type , features, status , purpose
		function validateSelection(input) {
			var errorElementId = input.id + "Feedback";
			var errorElement = document.getElementById(errorElementId);
			var value = input.value;

			if (value === "") {
				errorElement.textContent = "Please select an option.";
				input.setCustomValidity("Please select an option.");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity("");
			}
		}

		// Validation for property size
		function validatePropertySize(input) {
			var value = input.value;
			var errorElement = document.getElementById('propertySizeFeedback');

			if (value < 1000) {
				errorElement.textContent = "Property size must be greater than 1000 sq ft.";
				input
						.setCustomValidity("Property size must be greater than 1000 sq ft.");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity("");
			}
		}

		// Validation for price input field
		function validatePrice(input, minValue, feedbackId) {
			var price = input.value;
			var errorElement = document.getElementById(feedbackId);

			if (price < minValue) {
				errorElement.textContent = "Price must be greater than "
						+ minValue + ".";
				input.setCustomValidity("Price must be greater than "
						+ minValue + ".");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity("");
			}
		}

		// validation for Address
		function validateInput(input, feedbackId) {
			var value = input.value.trim();
			var errorElement = document.getElementById(feedbackId);

			if (value.length < 3) {
				errorElement.textContent = "Please enter a valid name with at least 3 characters.";
				input
						.setCustomValidity("Please enter a valid name with at least 3  characters.");
			} else if (!/^[a-zA-Z0-9\s]+$/.test(value)) {
				errorElement.textContent = "Address cannot contain special characters.";
				input
						.setCustomValidity("Address cannot contain special characters.");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity("");
			}
		}
		//validation for pincode
		function validatePincode(input) {
			var pincode = input.value.trim();
			var errorElement = document.getElementById("pincodeFeedback");

			// Check if pincode is exactly 6 digits long
			if (!/^\d{6}$/.test(pincode)) {
				errorElement.textContent = "Pincode must be exactly 6 digits.";
				input.setCustomValidity("Pincode must be exactly 6 digits.");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity("");
			}
		}
		// validation for Amenitiees 
		function validateAmenities(input) {
			var amenities = input.value.trim();
			var errorElement = document.getElementById("amenitiesFeedback");

			if (!/^[a-zA-Z\s,.]+$/.test(amenities)) {
				errorElement.textContent = "Amenities cannot contain numbers or special characters.";
				input
						.setCustomValidity("Amenities cannot contain numbers or special characters.");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity("");
			}
		}
		// Validation for images extensions
		function validateImageFileType(input, errorElementId) {
			var files = input.files;
			var errorElement = document.getElementById(errorElementId);
			var allowedExtensions = [ "jpg", "jpeg", "png" ];
			var invalidFiles = [];

			for (var i = 0; i < files.length; i++) {
				var fileName = files[i].name;
				var fileExtension = fileName.split('.').pop().toLowerCase();
				if (!allowedExtensions.includes(fileExtension)) {
					invalidFiles.push(fileName);
				}
			}

			if (invalidFiles.length > 0) {
				var errorMessage = "Only JPG, JPEG, and PNG files are allowed.";
				errorElement.textContent = errorMessage;
				input.setCustomValidity(errorMessage);
				return false;
			} else {
				errorElement.textContent = "";
				input.setCustomValidity("");
				return true;
			}
		}

		// Event listener for image file input change
		document.getElementById('propertyImages').addEventListener('change',
				function() {
					validateImageFileType(this, 'propertyImagesError');
				});

		document.getElementById('propertyDocImages').addEventListener('change',
				function() {
					validateImageFileType(this, 'propertyDocImagesError');
				});

		//submit if all validation is correctly followed 
		document.getElementById('propertyDetailsForm').addEventListener(
				'submit', function(event) {

				});
	</script>


</body>
</html>