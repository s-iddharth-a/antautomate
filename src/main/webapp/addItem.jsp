<!DOCTYPE html>
<html>
<head>
    <title>Add To-Do Item</title>
</head>
<body>
    <h1>Add a New To-Do Item</h1>
    <form action="ToDoServlet?action=add" method="post">
        <label for="description">Item Description:</label>
        <input type="text" id="description" name="description" required>
        <button type="submit">Add Item</button>
    </form>
    <a href="index.jsp">Back to Home</a>
</body>
</html>