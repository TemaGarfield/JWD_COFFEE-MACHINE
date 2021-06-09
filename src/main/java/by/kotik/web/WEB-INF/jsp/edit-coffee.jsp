<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 09.06.2021
  Time: 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditCoffee</title>
</head>
<body>
<div id="edit_coffee">
    <form method="post" action="Controller">
        <input type="hidden" name="command" value="editcoffee">
        type: <input type="text" name="type">
        cost: <input type="number" step="0.01" name="cost">
        amount: <input type="number" name="amount">

        <input type="submit" value="Edit">
    </form>
</div>
</body>
</html>
