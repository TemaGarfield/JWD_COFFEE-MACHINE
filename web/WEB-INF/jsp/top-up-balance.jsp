<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 18.06.2021
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>TopUpBalance</title>
</head>
<body>
<form method="post" action="Controller">
    <input type="hidden" name="command" value="topupbalance">
    <input type="hidden" name="id" value="<c:out value="${loggedUser.getId()}"/>">

    creditCardNumber: <input type="text" name="creditCardNumber">
    money: <input type="number" step="0.01" name="money">

    <input type="submit" value="Top up">
</form>

<button><a href="Controller?command=gotowelcomepage">Back</a></button>
</body>
</html>
