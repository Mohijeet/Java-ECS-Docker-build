<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Set New Password</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            color: #333; /* Set text color to dark gray */
        }
        .container {
            max-width: 500px;
            min-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff; /* Change background color of container */
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #0056b3; /* Set heading color to a shade of blue */
            text-align: center;
            margin-bottom: 30px;
        }
        .form-label {
            color: #444; /* Set label color to a shade of gray */
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
            text-align: left; /* Adjust label alignment */
        }
        .form-input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc; /* Change border color to light gray */
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
            background-color: #f9f9f9; /* Change input background color to a light shade */
            color: #333; /* Set input text color to dark gray */
        }
        .form-input::placeholder {
            color: #999; /* Set placeholder color to a lighter gray */
        }
        .form-submit-button {
            background-color: #056cda;
            color: #fff;
            border: none;
            padding: 12px 20px;
            font-size: 18px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
            display: inline-block;
            text-align: center;
        }
        .form-submit-button:hover {
            background-color: #0056b3; /* Darken button color on hover */
        }
    </style>
</head>
<body>
<div id="newPasswordForm">
    <section class="vh-100">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center">
                <div class="col-md-8 col-lg-6 col-xl-4">
                    <div class="text-center mb-4">
                        <h2 class="fw-bold">Set New Password</h2>
                        <p class="lead">Enter the OTP sent to your email and set a new password.</p>
                    </div>
                    <form id="newPasswordForm" action="LoginRegisterServlet" method="POST">
                        <input type="hidden" name="action" value="verifyOTP">
                        <!-- OTP input -->
                        <div class="form-group mb-3">
                            <label for="otp" class="form-label">Enter OTP</label>
                            <input type="text" id="otp" name="otp" class="form-input" placeholder="Enter OTP" required>
                        </div>
                        <!-- New Password input -->
                        <div class="form-group mb-3">
                            <label for="newPassword" class="form-label">New Password</label>
                            <div class="input-group">
                                <input type="password" id="newPassword" name="newPassword" class="form-input" placeholder="Enter your new password" required>
                                <button class="btn btn-outline-secondary toggle-password" type="button" id="toggleNewPassword">
                                    <i class="bi bi-eye"></i>
                                </button>
                            </div>
                        </div>
                        <!-- Confirm Password input -->
                        <div class="form-group mb-4">
                            <label for="confirmPassword" class="form-label">Confirm Password</label>
                            <div class="input-group">
                                <input type="password" id="confirmPassword" name="confirmPassword" class="form-input" placeholder="Confirm your new password" required>
                                <button class="btn btn-outline-secondary toggle-password" type="button" id="toggleConfirmPassword">
                                    <i class="bi bi-eye"></i>
                                </button>
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="form-submit-button btn btn-primary btn-lg">Set New Password</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p class="small">Remembered your password? <a href="#" id="loginLink" class="link-primary">Back to Login</a></p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
