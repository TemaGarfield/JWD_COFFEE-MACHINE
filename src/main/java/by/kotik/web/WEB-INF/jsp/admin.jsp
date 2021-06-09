<%@ page import="java.util.List" %>
<%@ page import="by.kotik.bean.Coffee" %><%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 01.06.2021
  Time: 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Admin</title>
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
            <th>Amount</th>
        </tr>
        <c:forEach var="coffee" items="${coffeeList}">
            <tr>
                <td><c:out value="${coffee.getType()}" /></td>
                <td><c:out value="${coffee.getCost()}" />$</td>
                <td><c:out value="${coffee.getAmount()}"/></td>
                <td><a href="Controller?command=editcoffee&id=<c:out value='${coffee.getId()}'/> ">Edit</a></td>
                <td><a href="Controller?command=deletecoffee&id=<c:out value='${coffee.getId()}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

    <button onclick="openForm('add_coffee')">Add coffee</button>
    <div  id="add_coffee" style="display: none">
        <form method="post" action="Controller">
            <input type="hidden" name="command" value="savenewcoffee">
            type: <input type="text" name="type">
            cost: <input type="number" step="0.01" name="cost">
            amount: <input type="number" name="amount">

            <input type="submit" value="Add">
        </form>
    </div>

    <button><a href="Controller?command=logout">Log Out</a></button>
</body>
</html>

<script>
    function openForm(id) {
        document.getElementById(id).style.display = "block";
    }
</script>


