<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 31.05.2021
  Time: 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Welcome</title>
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
    <h1>Welcome, <c:out value="${loggedUser.getUsername()}"/> </h1>
    <h1>Your balance is: <c:out value="${loggedUser.getBalance()}"/>$</h1>
    <button><a href="Controller?command=gototopupbalance">Top up balance</a></button>
    <table style="text-align: center">
        <tr>
            <th>Type</th>
            <th>Cost</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="coffee" items="${coffeeList}">

            <tr>
                <c:if test="${coffee.getAmount() > 0}">
                    <td><c:out value="${coffee.getType()}" /></td>
                    <td><c:out value="${coffee.getCost()}" />$</td>
                    <td><c:out value="${coffee.getAmount()}"/></td>
                    <td>
                        <a href="Controller?command=addtocart&idCoffee=<c:out value="${coffee.getId()}"/>">Add to cart</a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>


    <table style="text-align: center">
        <tr>
            <th>Cost</th>
            <th>Type</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="ingredient" items="${ingredientList}">
            <tr>
                <c:if test="${ingredient.getAmount() > 0}">
                    <td><c:out value="${ingredient.getCost()}"/>$</td>
                    <td><c:out value="${ingredient.getType()}"/></td>
                    <td><c:out value="${ingredient.getAmount()}"/></td>
                    <td><a href="Controller?command=addtocart&idIngredient=<c:out value="${ingredient.getId()}"/>">Add to cart</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <h1>cost: <c:out value="${order.getCost()}"/></h1>
    <button><a href="Controller?command=gotocart">Go to cart</a> </button>
    <button><a href="Controller?command=gotoshowallorders">Show all orders</a></button>
    <button><a href="Controller?command=logout">Log Out</a></button>
</body>
</html>
