<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 26.05.2021
  Time: 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.register.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.text.login" var="login"/>
<fmt:message bundle="${locale}" key="locale.text.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.register.button.register" var="register"/>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/register.css">
</head>
<body>
    <div class="wrapper">
        <header>
            <div class="container">
                <div class="header-content">
                    <c:import url="login-header.html"/>
                </div>
            </div>
        </header>

        <div class="content">
            <div class="container">
                <div class="form">
                    <form>
                        <input type="hidden" name="command" value="savenewuser">
                        <p>${login}</p>
                        <input type="email" name="login" value="" placeholder="kotik@example.com" class="input-field">
                        <p>${password}</p>
                        <input type="password" name="password" value="" class="input-field">

                        <input type="submit" value="${register}" class="submit-btn">
                    </form>
                    <c:if test="${!message.isEmpty()}">
                        <c:out value="${message}"/>
                        <c:set var="message" scope="session" value=""/>
                    </c:if>
                </div>
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
