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
</head>
<body>

    <c:forEach var="coffee" items="${coffeeList}">
        <p><c:out value="${coffee.getType()}" /> <c:out value="${coffee.getCost()}" /> <c:out value="${coffee.getAmount()}"/></p>
    </c:forEach>

    <button>Add coffee</button>
</body>
</html>
