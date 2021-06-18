<%@ page import="by.kotik.bean.User" %><%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 31.05.2021
  Time: 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("loggedUser");
    if (user == null) {
        response.sendRedirect("error.jsp");
    }
%>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1>Welcome, <%= user.getUsername() %></h1>
    <h1>Your balance is: <%= user.getBalance() %></h1>
    <button><a href="Controller?command=logout">Log Out</a></button>
</body>
</html>
