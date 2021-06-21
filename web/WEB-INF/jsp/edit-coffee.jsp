<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 09.06.2021
  Time: 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>EditCoffee</title>
</head>
<body>
<div id="edit_coffee">
    <form method="post" action="Controller">
        <input type="hidden" name="command" value="editcoffee">
        <input type="hidden" name="id" value="<c:out value="${coffee.getId()}"/>">

        type: <input type="text" name="type" value="<c:out value="${coffee.getType()}"/>">
        cost: <input type="number" step="0.01" name="cost" value="<c:out value="${coffee.getCost()}"/>">
        amount: <input type="number" name="amount" value="<c:out value="${coffee.getAmount()}"/>">

        <input type="submit" value="Edit">
    </form>
</div>
</body>
</html>
