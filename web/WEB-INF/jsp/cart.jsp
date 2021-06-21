<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 18.06.2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cart</title>
    <style type="text/css">
        table {
            border-collapse: collapse;
        }

        td, th {
            padding: 20px;
            border: 1px solid #000;
        }
    </style>
</head>
<body>
<table style="text-align: center">
    <tr>
        <th>Type</th>
        <th>Cost</th>
    </tr>
    <c:forEach var="coffee" items="${order.getCoffeeList()}" varStatus="loop">
        <tr>
            <td><c:out value="${coffee.getType()}" /></td>
            <td><c:out value="${coffee.getCost()}" />$</td>
            <td><a href="Controller?command=cartdeletecoffee&indexCoffee=${loop.index}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<table style="text-align: center">
    <tr>
        <th>Cost</th>
        <th>Type</th>
    </tr>
    <c:forEach var="ingredient" items="${order.getIngredientList()}" varStatus="loop">
        <tr>
            <td><c:out value="${ingredient.getCost()}"/>$</td>
            <td><c:out value="${ingredient.getType()}"/></td>
            <td><a href="Controller?command=cartdeleteingredient&indexIngredient=${loop.index}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<h1>cost: <c:out value="${order.getCost()}"/></h1>

<c:if test="${!message.isEmpty()}">
    <c:out value="${message}"/>
</c:if>

<button><a href="Controller?command=gotowelcomepage">Back</a></button>
<button><a href="Controller?command=saveneworder">Submit</a></button>
</body>
</html>
