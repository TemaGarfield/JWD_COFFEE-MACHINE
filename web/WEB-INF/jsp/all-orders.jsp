<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 20.06.2021
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
</head>
<body>
    <button><a href="Controller?command=gotowelcomepage">Back</a></button>
    <c:forEach var="order" items="${orders}">
        <table>
            <tr>
                <th>Products</th>
            </tr>
            <c:forEach var="coffee" items="${order.getCoffeeList()}">
                <tr>
                    <td><c:out value="${coffee.getType()}"/></td>
                </tr>
            </c:forEach>

            <c:forEach var="ingredient" items="${order.getIngredientList()}">
                <tr>
                    <td><c:out value="${ingredient.getType()}"/></td>
                </tr>
            </c:forEach>

        </table>
        <p><c:out value="${order.getCost()}"/>$</p>
    </c:forEach>
</body>
</html>
