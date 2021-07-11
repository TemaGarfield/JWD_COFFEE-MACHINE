<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 26.05.2021
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.login.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.text.login" var="login"/>
<fmt:message bundle="${locale}" key="locale.text.password" var="password"/>
<fmt:message bundle="${locale}" key="locale.button.log_in" var="log_in"/>
<fmt:message bundle="${locale}" key="locale.login.link.create_account" var="create_account"/>


<html>
<head>
    <title>${title}</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/login.css">
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
                    <div class="form-content">
                        <form action="Controller" method="post">
                            <input type="hidden" name="command" value="login">
                            <p>${login}</p> <input type="text" name="login" value="" class="input-field"><br/>
                            <p>${password}</p> <input type="password" name="password" value="" class="input-field"> <br/>

                            <input type="submit" value="${log_in}" class="submit-btn"> <br/>
                        </form>

                        <c:if test="${!message.isEmpty()}">
                            <c:out value="${message}"/>
                            <br/>
                            <c:set var="message" scope="session" value=""/>
                        </c:if>

                        <a href="Controller?command=registration">${create_account}</a>
                    </div>
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
