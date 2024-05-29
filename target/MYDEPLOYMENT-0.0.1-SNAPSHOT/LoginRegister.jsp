<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		 $("#registerLink").click(function() {
		        $("#loginForm").hide();
		        $("#registerForm").show();
		        $("#resetPasswordForm").hide(); // Hide reset password form if shown
		    });

		    $("#loginLink").click(function() {
		        $("#registerForm").hide();
		        $("#loginForm").show();
		        $("#resetPasswordForm").hide(); // Hide reset password form if shown
		    });

	    $("#resetPasswordLink").click(function() {
	        $("#loginForm").hide();
	        $("#registerForm").hide();
	        $("#resetPasswordForm").show();
	    });
	    $("#resetPasswordForm #loginLink").click(function() {
	        $("#loginForm").show();
	        $("#registerForm").hide();
	        $("#resetPasswordForm").hide();
	    });
	});
</script>

<!-- Your custom CSS overrides (if any) -->
<style>
/* Add your custom CSS overrides here */
.divider:after, .divider:before {
	content: "";
	flex: 1;
	height: 1px;
	background: #eee;
}

.h-custom {
	height: calc(100% - 73px);
}

@media ( max-width : 450px) {
	.h-custom {
		height: 100%;
	}
}

/* Customize the button color */
.btn-primary {
	background-color: rgb(35, 66, 35);
	border-color: rgb(35, 66, 35);
}

/* Customize the copyrights background */
.bg-primary {
	background-color: rgb(35, 66, 35);
}
</style>
</head>
<body>


	<jsp:include page="navbar.jsp" />
	

	<%-- Display error message if login fails --%>
			<%
			String error = request.getParameter("error");
			String emailAlready = request.getParameter("emailAlready");
			String mobileAlready = request.getParameter("mobileAlready");
			String registrationSuccessfull = request.getParameter("registrationSuccessfull");
			String emailnotExist = request.getParameter("emailNotExist");
			String passwordResetSuccess = request.getParameter("passwordResetSuccess");
		
			if (error != null && error.equals("1")) {
			%>
			<div id="errorAlert" class="alert alert-danger" role="alert">Invalid
				Email or Password. Please try again.</div>
			<%
			}
			if (emailAlready != null && emailAlready.equals("1")) {
			%>
			<div id="emailAlert" class="alert alert-danger" role="alert">Email
				Already Registered please login..</div>
			<%
			}
			if (mobileAlready != null && mobileAlready.equals("1")) {
			%>
			<div id="mobileAlert" class="alert alert-danger" role="alert">Mobile
				Number Already Registered. Please try with another mobile Number..</div>
			<%
			}
			if (registrationSuccessfull != null && registrationSuccessfull.equals("1")) {
			%>
			<div id="registrationSuccessfullAlert" class="alert alert-success"
				role="alert">Registration Successful. Please Login..</div>
			<!-- Corrected text -->
			<%
			}
			if (emailnotExist != null && emailnotExist.equals("1")) {
			%>
			<div id="emailnotExistAlert" class="alert alert-danger" role="alert">Email
				Doesn't Exist Please register..</div>
			<!-- Corrected text -->
			<%
			}
			if (passwordResetSuccess != null && passwordResetSuccess.equals("1")) {
			%>
			<div id="passwordResetSuccessAlert" class="alert alert-success"
				role="alert">Password Reset Successfull ..</div>
			<!-- Corrected text -->
			<%
			}
			%>
		


	<div id="loginForm">

		<section class="mt-5">
			<div class="container-fluid h-custom">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-md-9 col-lg-6 col-xl-5">
						<img
							src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
							class="img-fluid" alt="Sample image">
					</div>
					<div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">

						<div
							class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">


						</div>
						<div class="divider d-flex align-items-center my-4">
							<p class="text-center fw-bold mx-3 mb-0">Login Form</p>

						</div>

						<form id="login" action="LoginRegisterServlet" method="POST">
							<input type="hidden" name="action" value="login">
							<!-- Email input -->
								<div class="form-outline mb-4">
								<label class="form-label" for="form3Example3">Email
									address</label> <input type="email" id="form3Example3" name="email"
									class="form-control form-control-lg"
									placeholder="Enter a valid email address" />
							</div>
							<!-- Password input -->
							<div class="form-outline mb-3">
							    <label class="form-label" for="form3Example4">Password</label> 
							    <div class="input-group">
							        <input type="password" id="form3Example4" name="password" class="form-control form-control-lg" placeholder="Enter password" />
							        <button class="btn btn-outline-secondary toggle-password" type="button" id="toggleLoginPassword">
							            <i class="bi bi-eye" style="border: none;"></i>
							        </button>
							    </div>
							</div>
							<div class="d-flex justify-content-between align-items-center">
								<!-- Checkbox -->
								<div class="form-check mb-0">
									<input class="form-check-input me-2" type="checkbox" value=""
										id="form2Example3" /> <label class="form-check-label"
										for="form2Example3"> Remember me </label>
								</div>
								<a href="#" id="resetPasswordLink" class="text-body">Forgot password?</a>
								<!-- Redirects to the registration page -->
							</div>
							<div class="text-center text-lg-start mt-4 pt-2">
								<input type="submit" class="btn btn-primary btn-lg"
									value="Login"
									style="padding-left: 2.5rem; padding-right: 2.5rem;">
								<p class="small fw-bold mt-2 pt-1 mb-0">
									Don't have an account? <a href="#" id="registerLink"
										class="link-danger">Register</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>

		</section>

	</div>


	<div id="registerForm" style="display: none;">

		<section class="vh-100">
			<div class="container-fluid h-custom">

				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-md-9 col-lg-6 col-xl-5">
						<img
							src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
							class="img-fluid" alt="Sample image">
					</div>
					<div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
						<div class="divider d-flex align-items-center my-4">
							<p class="text-center fw-bold mx-3 mb-0">Registration Form</p>
						</div>

						<form id="register" action="LoginRegisterServlet" method="POST">
							<input type="hidden" name="action" value="register">
							<!-- First Name input -->
							<div class="form-outline mb-4">
								<label for="firstName">First Name:</label> <input type="text"
									id="firstName" name="fname"
									class="form-control form-control-lg"
									placeholder="Enter your first name" required
									oninput="validateFirstName(this)">
								<div id="firstNameError" class="text-danger"></div>
							</div>
							<!-- Last Name input -->
							<div class="form-outline mb-4">
								<label for="lastName">Last Name:</label> <input type="text"
									id="lastName" name="lname" class="form-control form-control-lg"
									placeholder="Enter your last name" required
									oninput="validateLastName(this)">
								<div id="lastNameError" class="text-danger"></div>
							</div>
							<!-- Mobile Number input -->
							<div class="form-outline mb-4">
							
								<label for="mobileNumber">Mobile Number:</label> <input
									type="text" id="mobileNumber" name="mobile"
									class="form-control form-control-lg"
									placeholder="Enter your mobile number (10 digits)" required
									oninput="validateMobileNumber(this); " >

								<div id="mobileNumberError" class="text-danger"></div>
							</div>
							<!-- Email input -->
							<div class="form-outline mb-4">
								<label for="email">Email address:</label> <input type="email"
									id="email" name="email" class="form-control form-control-lg"
									placeholder="Enter a valid email address" required
									oninput="validateEmailAddress(this); "
>
								<div id="emailError" class="text-danger"></div>
							</div>
							<!-- Password input -->
							<div class="form-outline mb-4">
								    <label for="password">Password:</label>
								    <div class="input-group">
								        <input type="password" id="password" name="password" class="form-control form-control-lg" placeholder="Enter password (at least 8 characters with alphanumeric and special characters)" required oninput="validatePassword(this)">
								        <button class="btn btn-outline-secondary toggle-password" type="button" id="toggleRegisterPassword">
								             <i class="bi bi-eye" style="border: none;"></i>
								        </button>
								    </div>
								    <div id="passwordError" class="text-danger"></div>
								</div>
							<!-- User Roles input -->
							<div class="form-outline mb-4">
								<label for="userRoles">Register as</label> <select
									id="userRoles" name="roles"
									class="form-control form-control-lg" required>
									<option value="" selected disabled>Select role</option>
									<option value="user">User</option>
									<option value="agent">Agent</option>
									<option value="landlord">Landlord</option>
									<option value="tenant">Tenant</option>
								</select>
							</div>
							<div class="d-flex justify-content-between align-items-center">
								<!-- Checkbox -->
								<div class="form-check mb-0">
									<input class="form-check-input me-2" type="checkbox" value=""
										id="agreeTerms" required> <label
										class="form-check-label" for="agreeTerms"> I agree to
										the terms and conditions </label>
								</div>
							</div>
							<div class="text-center text-lg-start mt-4 pt-2">
								<input type="submit" class="btn btn-primary btn-lg"
									value="Register"
									style="padding-left: 2.5rem; padding-right: 2.5rem;">
								<p class="small fw-bold mt-2 pt-1 mb-0">
									Already have an account? <a href="#" id="loginLink"
										class="link-danger">Login</a>
								</p>
							</div>
						</form>
					</div>
				</div>
			</div>

		</section>
	</div>



<!--  Resetting password  -->
	
	<!--  Resetting password  -->
	<div id="resetPasswordForm">
    <section class="vh-100">
        <div class="container-fluid h-custom">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-9 col-lg-6 col-xl-5">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp" class="img-fluid" alt="Sample image">
                </div>
                <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0">Reset Password</p>
                    </div>
                    <form id="resetPasswordForm" action="LoginRegisterServlet" method="POST">
                        <input type="hidden" name="action" value="reset">
                        <!-- Email input -->
                        <div class="form-outline mb-4">
                            <label class="form-label" for="resetEmail">Email address</label>
                            <input type="email" id="resetEmail" name="resetEmail" class="form-control form-control-lg" placeholder="Enter your email address" required>
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">
                            <input type="submit" value="Reset Password" class="btn btn-primary btn-lg" style="padding-left: 2.5rem; padding-right: 2.5rem;">
                            <p class="small fw-bold mt-2 pt-1 mb-0">
                                <a href="#" id="loginLink" class="link-danger">Back to Login</a>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>

	
	
	

	<script>
	//Validation
		//Function for FirstName 
		function validateFirstName(input) {
			var firstName = input.value.trim();//removing spaces
			var specialChars = /[^\w\s]/;// regex for checking special characters
			var space = /\s/; //regex for checking spaces
			var numbers = /\d/;//regex for checking numbers
			var errorElement = document.getElementById("firstNameError");
			if (firstName.length > 20 || firstName .length <3 ) {
				errorElement.textContent = "First name must be between 3 and 20 Characters.";
				input.setCustomValidity("First name must be between 3 and 20 Characters.");
			} else if (specialChars.test(firstName) || space.test(firstName)) {
				errorElement.textContent = "First name should not contain Special Characters or spaces.";
				input.setCustomValidity("First name should not contain Special Characters or spaces.");
			} else if (numbers.test(firstName)) {
				errorElement.textContent = "First name should not contain Numbers.";
				input.setCustomValidity("First name should not contain Numbers.");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity(""); // Clear the validation message
			}
		}

		// Function for Lastname
		function validateLastName(input) {
			var lastName = input.value.trim();
			var specialChars = /[^\w\s]/;// regex for checking special characters
			var space = /\s/; //regex for checking spaces
			var numbers = /\d/;//regex for checking numbers
			var errorElement = document.getElementById("lastNameError");
			var firstNameValue = document.getElementById("firstName").value.trim();

			if (lastName.length > 20 || lastName.length < 3) {
				errorElement.textContent = "Last name must be between 3 and 20 Characters.";
				input.setCustomValidity("Last name must be between 3 and 20 Characters.");
			} 
			else if (specialChars.test(lastName)||space.test(lastName)) {
				errorElement.textContent = "Last name should not contain Special Characters or spaces.";
				input.setCustomValidity("Last name should not contain Special Characters or spaces.");
			}   else if (numbers.test(lastName)) {
				errorElement.textContent = "Last name should not contain Numbers.";
				input.setCustomValidity("Last name should not contain Numbers.");
			}else if (firstNameValue === lastName) {
				errorElement.textContent = "First name and lastname must be different.";
				input.setCustomValidity("First name and lastname mustbe different.");
			} else {
				errorElement.textContent = "";
				input.setCustomValidity(""); // Clear the validation message
			}
		}

		// Function for validating Mobile Number
		function validateMobileNumber(input) {
			var mobileNumber = input.value.trim();
			var errorElement = document.getElementById("mobileNumberError");

			if (mobileNumber === "") {
				errorElement.textContent = "Mobile number is required.";
				input.setCustomValidity("Mobile number is required.");
			} else if (mobileNumber.length !== 10) {
				errorElement.textContent = "Mobile number must be 10 digits long.";
				input.setCustomValidity("Mobile number must be 10 digits long.");
			} else if (!/^\d{10}$/.test(mobileNumber)) {
				errorElement.textContent = "Mobile number Should not contain alphabets or special charaters.";
				input.setCustomValidity("Mobile number Should not contain alphabets or special characters.");
			} else if (!/^[6-9]\d{9}$/.test(mobileNumber)) {
		        errorElement.textContent = "Enter Correct mobile Number";
		        input.setCustomValidity("Enter Correct Mobile number");
		    }else {
				errorElement.textContent = "";
				input.setCustomValidity("");
			}
		}

		

		//Function for Validating EmailAddress
				function validateEmailAddress(input) {
		    var email = input.value.trim();
		    var errorElement = document.getElementById("emailError");
		    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		    var emailPattern2 = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}\.[a-zA-Z]{2,}$/;
		
		    if (!emailPattern.test(email) && !emailPattern2.test(email)) {
		        errorElement.textContent = "Please enter a valid email address.";
		        input.setCustomValidity("Invalid email format");
		    } else {
		        errorElement.textContent = "";
		        input.setCustomValidity("");
		    }
		}
		 
		 //function for password
		 function validatePassword(input) {
        var password = input.value.trim();
        var errorElement = document.getElementById("passwordError");
        var lengthPattern = /.{8,}/; 
        var alphanumericPattern = /[a-zA-Z]/; 
        var digitPattern = /\d/; 
        var specialCharPattern = /[!@#$%^&*(),.?":{}|<>]/; 

        if (!lengthPattern.test(password)) {
            errorElement.textContent = "Password must be at least 8 characters long.";
            input.setCustomValidity("Password must be at least 8 characters long.");
        } else if (!alphanumericPattern.test(password)) {
            errorElement.textContent = "Password must contain at least one letter.";
            input.setCustomValidity("Password must contain at least one letter.");
        } else if (!digitPattern.test(password)) {
            errorElement.textContent = "Password must contain at least one digit.";
            input.setCustomValidity("Password must contain at least one digit.");
        } else if (!specialCharPattern.test(password)) {
            errorElement.textContent = "Password must contain at least one special character.";
            input.setCustomValidity("Password must contain at least one special character.");
        } else {
            errorElement.textContent = "";
            input.setCustomValidity("");
        }
    }     //validation for newpassword
		 function validateNewPassword(input) {
		        var password = input.value.trim();
		        var errorElement = document.getElementById("newPasswordError");
		        var lengthPattern = /.{8,}/; 
		        var alphanumericPattern = /[a-zA-Z]/; 
		        var digitPattern = /\d/; 
		        var specialCharPattern = /[!@#$%^&*(),.?":{}|<>]/; 

		        if (!lengthPattern.test(password)) {
		            errorElement.textContent = "Password must be at least 8 characters long.";
		            input.setCustomValidity("Password must be at least 8 characters long.");
		        } else if (!alphanumericPattern.test(password)) {
		            errorElement.textContent = "Password must contain at least one letter.";
		            input.setCustomValidity("Password must contain at least one letter.");
		        } else if (!digitPattern.test(password)) {
		            errorElement.textContent = "Password must contain at least one digit.";
		            input.setCustomValidity("Password must contain at least one digit.");
		        } else if (!specialCharPattern.test(password)) {
		            errorElement.textContent = "Password must contain at least one special character.";
		            input.setCustomValidity("Password must contain at least one special character.");
		        } else {
		            errorElement.textContent = "";
		            input.setCustomValidity("");
		        }
		    }
		//validation for Confirmpassword
		 function validateConfirmPassword(input) {
		        var password = input.value.trim();
		        var errorElement = document.getElementById("confirmPasswordError");
		        var newpasswordValue = document.getElementById("newPassword").value.trim();
		        if(!(password===newpasswordValue)){
		        	errorElement.textContent = "new password and confirm password didn't match.";
		            input.setCustomValidity("new password and confirm password didn't match.");
		        }
		         else {
		            errorElement.textContent = "";
		            input.setCustomValidity("");
		        }
		    }
		 
	//code for eye symbol in password 
	document.querySelectorAll(".toggle-password").forEach(function(button) {
	    button.addEventListener("click", function() {
	        var passwordInput = this.parentNode.querySelector("input[type='password']");
	        var icon = this.querySelector("i");
	
	        if (passwordInput.type === "password") {
	            passwordInput.type = "text";
	            icon.classList.remove("bi-eye");
	            icon.classList.add("bi-eye-slash");
	        } else {
	            passwordInput.type = "password";
	            icon.classList.remove("bi-eye-slash");
	            icon.classList.add("bi-eye");
	        }
	    });
	});

		 
	
	// Function to hide alerts after 5 seconds
    setTimeout(function() {
        var errorAlert = document.getElementById('errorAlert');
        var emailAlert = document.getElementById('emailAlert');
        var mobileAlert = document.getElementById('mobileAlert');
        var registrationSuccessfullAlert=  document.getElementById('registrationSuccessfullAlert');
        var emailnotExist = document.getElementById('emailnotExistAlert');  
        var passwordResetSuccess = document.getElementById('passwordResetSuccessAlert');
        if (errorAlert) {
            errorAlert.style.display = 'none';
        }
        if (emailAlert) {
            emailAlert.style.display = 'none';
        }
        if (mobileAlert) {
            mobileAlert.style.display = 'none';
        }if (registrationSuccessfullAlert) {
        	registrationSuccessfullAlert.style.display = 'none';
        }if (emailnotExist) {
        	emailnotExist.style.display = 'none';
        }if (passwordResetSuccess) {
        	passwordResetSuccess.style.display = 'none';
        }
        
    }, 4000); // 4000 milliseconds = 4 seconds
	</script>

	



	<!-- Bootstrap Bundle with Popper (JavaScript) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
