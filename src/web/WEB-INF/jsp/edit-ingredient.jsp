<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 17.06.2021
  Time: 6:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>EditIngredient</title>
</head>
<body>
<div id="edit_ingredient">
  <form method="post" action="Controller">
    <input type="hidden" name="command" value="editingredient">
    <input type="hidden" name="id" value="<c:out value='${ingredient.getId()}'/>">

    cost: <input type="number" step="0.01" name="cost" value="<c:out value='${ingredient.getCost()}'/>">
    type: <input type="text" name="type" value="<c:out value='${ingredient.getType()}'/>">
    amount: <input type="number" name="amount" value="<c:out value='${ingredient.getAmount()}'/>">

    <input type="submit" value="Edit">
  </form>
</div>
</body>
</html>
