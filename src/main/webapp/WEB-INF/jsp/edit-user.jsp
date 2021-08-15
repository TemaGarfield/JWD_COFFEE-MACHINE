<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 05.08.2021
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.admin.edit_user.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.admin.button.edit" var="edit"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.id_user" var="id"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.username" var="username"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.balance" var="balance"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.admin.button.edit" var="edit"/>

<html>
<head>
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/sources/css/main.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/sources/css/admin.css" rel="stylesheet">
</head>
<body>
    <div class="wrapper">
        <header>
            <div class="container">
                <div class="header-content">
                    <c:import url="admin-header.jsp"/>
                </div>
            </div>
        </header>

        <div class="content">
            <div class="container">
                <div id="edit_user">
                    <form method="post" action="Controller">
                        <input type="hidden" name="command" value="edituser">
                        <input type="hidden" name="id" value="<c:out value="${user.getId()}"/> ">

                        <p>${username}</p> <input type="email" name="username" value="<c:out value="${user.getUsername()}"/>" class="input-field"> <br/>
                        <p>${password}</p> <input type="password" name="password" value="" class="input-field"> <br/>
                        <p>${balance}</p> <input type="number" name="balance" value="<c:out value="${user.getBalance()}"/>" class="input-field" step="0.01"> <br/>
                        <input type="submit" value="${edit}" class="admin-btn">
                    </form>
                </div>
                <c:if test="${!message.isEmpty()}">
                    <c:out value="${message}"/>
                    <c:set var="message" scope="session" value=""/>
                </c:if>
            </div>
        </div>

        <footer>
            <div class="container">
                <div class="footer-content">
                    <c:import url="footer.jsp"/>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>
