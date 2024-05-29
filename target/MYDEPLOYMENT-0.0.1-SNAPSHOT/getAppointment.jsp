<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mastek.bean.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Property Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            color: #333; /* Set text color to dark gray */
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .container {
            max-width: 400px; /* Adjust container width as needed */
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        .mt-4 {
            margin-top: 1rem; /* Set top margin to 1rem */
        }
        .form-label {
            color: #444; /* Set label color to a shade of gray */
            font-weight: bold;
            margin-bottom: 10px;
            display: block;
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
        .btn {
            display: inline-block;
            font-weight: 400;
            color: #212529;
            text-align: center;
            vertical-align: middle;
            cursor: pointer;
            border: 1px solid transparent;
            padding: .375rem .75rem;
            font-size: 1rem;
            line-height: 1.5;
            border-radius: 20px; /* Set border radius */
            transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
            background-color: #007bff; /* Set button background color */
            border-color: #007bff;
            color: #fff;
        }
        .btn-primary:hover {
            color: #fff;
            background-color: #0056b3; /* Darken button color on hover */
            border-color: #0056b3;
        }
    </style>
</head>
<body>
<%
    Integer userId = (Integer) session.getAttribute("userId");
%>

<div class="container">
    <!-- Appointment form -->
    <form id="appointmentForm" action="appointmentServlet" method="post">
        <label class="form-label" for="appDate">Appointment Date:</label>
        <input type="date" id="appDate" name="appDate" class="form-input" required><br>
        
        <!-- Hidden fields for USER_ID, AGENT_ID, PROPERTY_ID_FK -->
        <input type="hidden" id="userId" name="userId" value="<%= userId %>">
     <input type="hidden" id="agentId" name="agentId" value="<%= request.getParameter("agentId") %>">
<input type="hidden" id="propertyId" name="propertyId" value="<%= request.getParameter("propertyId") %>">

        
        <!-- Add more fields as needed -->

        <input type="submit" class="btn btn-primary m-0 p-0" value="Send Appointment Request">
    </form>
</div>

<!-- <script>
    document.getElementById("appointmentForm").addEventListener("submit", function(event) {
        // Prevent the default form submission behavior
        event.preventDefault();
        
        // Perform AJAX submission or any other necessary actions here
        
        // Display alert
        
        // Redirect to index.jsp after 2 seconds
       /*  setTimeout(function() {
            window.location.href = "index.jsp";
        }, 100); */
    });
</script> -->

</body>
</html>



