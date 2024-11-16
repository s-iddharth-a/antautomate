<%@ page import="java.util.List" %>
<%@ page import="ToDoAppHibernate.ToDoItem" %>
<!DOCTYPE html>
<html>
<head>
    <title>To-Do List</title>
</head>
<body>
    <h1>Your To-Do List</h1>
    <ul>
        <% 
            // Retrieve the list of items from the request
            List<ToDoItem> items = (List<ToDoItem>) request.getAttribute("items");

            // Check if there are items in the list
            if (items != null) {
                // Loop through each item in the list and display it
                for (ToDoItem item : items) {
        %>
            <li>ID: <%= item.getId() %> - <%= item.getDescription() %></li>
        <% 
                }
            } else {
        %>
            <li>No items available.</li>
        <% 
            }
        %>
    </ul>
    <a href="index.jsp">Back to Home</a>
</body>
</html>