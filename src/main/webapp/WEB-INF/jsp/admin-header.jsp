<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 11.07.2021
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.admin.text.header" var="header_text"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.main_page" var="main_page"/>
<fmt:message bundle="${locale}" key="locale.button.log_out" var="log_out"/>
<fmt:message bundle="${locale}" key="locale.admin.link.users" var="users"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/admin-header.css">

<h1>${header_text}</h1>
<a href="Controller?command=gotoadminpage">${main_page}</a>
<a href="Controller?command=gotoeditusers">${users}</a>
<a href="Controller?command=logout">${log_out}</a>
