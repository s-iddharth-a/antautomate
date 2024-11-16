<%@ page import="java.util.List" %>
<%@ page import="ToDoAppHibernate.ToDoItem" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete To-Do Item</title>
</head>
<body>
    <h1>Delete a To-Do Item</h1>
    <form action="ToDoServlet?action=delete" method="post">
        <label for="index">Select Item ID to Delete:</label>
        <select id="index" name="index">
            <% 
                // Retrieve the list of items from the request
                List<ToDoItem> items = (List<ToDoItem>) request.getAttribute("items");

                // Check if there are items to display in the drop-down
                if (items != null) {
                    // Loop through the items to create the <option> elements
                    for (ToDoItem item : items) {
            %>
                <option value="<%= item.getId() %>">ID: <%= item.getId() %> - <%= item.getDescription() %></option>
            <% 
                    }
                } else {
            %>
                <option value="">No items available</option>
            <% 
                }
            %>
        </select>
        <br><br>
        <input type="hidden" name="action" value="delete" />
        <button type="submit">Delete Item</button>
    </form>
    <a href="index.jsp">Back to Home</a>
</body>
</html>