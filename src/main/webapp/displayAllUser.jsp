<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="com.mastek.bean.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  

</head>
<body>
 <script>
    $(document).ready(function(){
        // Function to fetch data without clicking a button
        function fetchData() {
            $.ajax({
                type: 'GET',
                url: 'displayAllUserServlet', // URL of your servlet
                success: function(response) {
                    // Clear previous data
                    $('#dataContainer').empty();

                    // Iterate over each user object in the response
                    $.each(response, function(index, User) {
                        // Create HTML elements to display user information
                        var userHtml = '<div class="user">' +
                                            '<label>User ID : ' + User.userId + ', </label> &nbsp;' +
                                            '<label>Name: ' + User.firstName + '' + User.lastName + ', </label> &nbsp;' +
                                            '<label>Email: ' + User.email + '</label>' +
                                            // Add other user properties as needed
                                        '</div>';

                        // Append the user HTML to the dataContainer
                        $('#dataContainer').append(userHtml);
                    });
                }
            });
        }

        // Fetch data when the page loads
        fetchData();
        
        // Optionally, you can also set an interval to fetch data periodically
        // setInterval(fetchData, 5000); // Fetch data every 5 seconds (for example)
    });
</script>


 <div id="dataContainer">
        <!-- Fetched data will be displayed here -->
    </div>

</body>
</html>