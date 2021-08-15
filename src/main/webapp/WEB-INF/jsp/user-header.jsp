<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 11.07.2021
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.welcome.title" var="header_text"/>
<fmt:message bundle="${locale}" key="locale.welcome.text.balance" var="balance"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.top_up_balance" var="top_up_balance"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.go_to_cart" var="go_to_cart"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.show_all_orders" var="show_all_orders"/>
<fmt:message bundle="${locale}" key="locale.button.log_out" var="log_out"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.main_page" var="main_page"/>

<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/user-header.css">
</head>
<body>

        <h1>${header_text}</h1>
        <div class="links">
            <a href="Controller?command=gotowelcomepage">${main_page}</a>
            <a href="Controller?command=gotocart">${go_to_cart}</a>
            <a href="Controller?command=gotoshowallorders">${show_all_orders}</a>
            <a href="Controller?command=gototopupbalance">${top_up_balance}</a>
            <a href="Controller?command=logout">${log_out}</a>
        </div>

</body>
</html>


