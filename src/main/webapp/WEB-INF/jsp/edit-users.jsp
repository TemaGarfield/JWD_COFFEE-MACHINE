<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 05.08.2021
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.admin.edit_users.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.id_user" var="id"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.username" var="username"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.balance" var="balance"/>
<fmt:message bundle="${locale}" key="locale.button.delete" var="delete"/>
<fmt:message bundle="${locale}" key="locale.admin.button.edit" var="edit"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.admin.edit_users.add_new_user" var="add_new_user"/>

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
                <div class="tables">
                    <table>
                        <tr>
                            <td>${id}</td>
                            <td>${username}</td>
                            <td>${balance}</td>
                        </tr>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td><c:out value="${user.getId()}"/> </td>
                                <td><c:out value="${user.getUsername()}"/> </td>
                                <td><c:out value="${user.getBalance()}"/> </td>
                                <td><a href="Controller?command=deleteuser&id=<c:out value="${user.getId()}"/>">${delete}</a> </td>
                                <td><a href="Controller?command=gotoedituser&id=<c:out value="${user.getId()}"/>">${edit}</a> </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <button onclick="openForm('add_user')" class="admin-btn">${add_new_user}</button>
                <div id="add_user" style="display: none">
                    <form method="post" action="Controller">
                        <input type="hidden" name="command" value="saveuserfromadmin">
                        ${username}: <input type="email" name="username" class="input-field">
                        ${password}: <input type="password" name="password" class="input-field">
                        ${balance}: <input type="number" name="balance" class="input-field" step="0.01">

                        <input type="submit" value="${add_new_user}" class="admin-mini-btn">

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

<script>
    function openForm(id) {
        document.getElementById(id).style.display = "block";
    }
</script>
