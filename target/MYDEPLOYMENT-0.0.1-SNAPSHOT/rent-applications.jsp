<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ page import="com.mastek.bean.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rent Application Form</title>
    <!-- Add your CSS and other head elements here -->
    <style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    margin: 0;
    padding: 0;
    color: #333; /* Set text color to dark gray */
  }
  .container {
    max-width: 400px; /* Increased max-width */
    margin: 50px auto; /* Increased margin */
    padding: 40px; /* Increased padding */
    background-color: #ffffff; /* Change background color of container */
   
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  h1 {
    color: #215994; /* Set heading color to a shade of blue */
    text-align: center;
    margin-bottom: 30px;
  }
  .form-logo {
    text-align: center;
    margin-bottom: 20px;
  }
  .form-logo img {
    max-width: 100%;
    height: auto;
  }
  .form-label {
    color: #121b24; /* Set label color to the same blue as the heading */
    font-weight: bold;
    margin-bottom: 5px;
  }
  .form-input {
    width: calc(100% - 24px); /* Adjust input width */
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
    background-color: #0056b3;
    color: #fff;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
    width: 97%;


   
   
  }
  .form-submit-button:hover {
    background-color: #9bc8f5; /* Darken button color on hover */
  }
</style>
</head>
<body>
	<%
        User user = (User) session.getAttribute("userObj");
	%>
	
  <jsp:include page="navbar.jsp" />
  
  <div class="container">
  <h1>Rental Application Form</h1>
  <div class="form-logo">
    <img src="https://www.jotform.com/uploads/ugurg/form_files/Green%20Modern%20Villa%20For%20Rent%20Flyer.65b75d68e48932.56271882.png" alt="Online Rental Application Form">
  </div>
  <form id="rental-form myForm" action="sentRentRequest" method="post" enctype="multipart/form-data">
   <input type="hidden" name="userId"  value="<%= user.getUserId() %>" /><br/><br/>
        <input type="hidden"  name="agentId" value="<%= request.getParameter("agentId") %>" /><br/><br/>
        <input type="hidden" name="propertyId" value="<%= request.getParameter("propertyId") %>"/><br/><br/>
    <div style="display: flex; flex-wrap: wrap; justify-content: space-between;">

       
      <div style="width: 48%;">
        <label class="form-label">Rent</label>
        <input type="number" id="disabledInput" name="rent" class="form-input" value="<%= request.getParameter("rent") %>" disabled >
  		<input type="hidden" id="hiddenInput" name="rent" value="<%= request.getParameter("rent") %>">   
      </div>
      <div style="width: 48%;">
        <label class="form-label" for="start-date">Start Date</label>
        <input type="date" id="start-date"  name="startDate" class="form-input" required>
      </div>
      
      <div style="width: 48%;">
        <label class="form-label" for="end-date">End Date</label>
        <input type="date" id="end-date" name="endDate" class="form-input" required>
      </div>
      
      
      <div style="width: 48%;">
        <label class="form-label" for="end-date">Upload Aadhar Card</label>
         <input type="file" id="aadharPhoto" name="aadharPhoto" class="form-input" required>
      </div>
    </div>
    
    
   
    <button type="submit" class="form-submit-button">Send Application</button>
  </form>
  </div>
   <%--  <h2>Rent Application Form</h2>
    <form id="myForm" action="sentRentRequest" method="post" enctype="multipart/form-data">
        <label for="username">UserID:</label>
        <input type="hidden" name="userId"  value="<%= user.getUserId() %>" /><br/><br/>

        <label for="agentId">Agent ID:</label>
        <input type="hidden"  name="agentId" value="<%= request.getParameter("agentId") %>" /><br/><br/>

        <label for="propertyId">Property ID:</label>
        <input type="hidden" name="propertyId" value="<%= request.getParameter("propertyId") %>"/><br/><br/>
        
        <label for="startDate">Rent:</label>
        <input type="number"  name="rent" value="<%= request.getParameter("rent") %>" required /><br/><br/>
        
        <input type="number" id="disabledInput" name="disabledInput" value="<%= request.getParameter("rent") %>" disabled>
  		<input type="hidden" id="hiddenInput" name="rent">
        
        <label for="startDate">Start Date:</label>
        <input type="date" name="startDate" required /><br/><br/>

        <label for="endDate">End Date:</label>
        <input type="date"  name="endDate" required /><br/><br/>

        <label for="aadharPhoto">Aadhar Card Photo:</label>
        <input type="file" id="aadharPhoto" name="aadharPhoto" required /><br/><br/>

        <input type="submit" value="submit" />
    </form> --%>
    
    <script>
				document.getElementById("myForm").addEventListener("submit", function() {
		  // Copy the value of the disabled input to the hidden input
		 		 document.getElementById("rent").value = document.getElementById("disabledInput").value;
				});
</script>
</body>
</html>
