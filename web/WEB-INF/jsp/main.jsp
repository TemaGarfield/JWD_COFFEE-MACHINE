<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 26.05.2021
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.main.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.button.en" var="en_button"/>
<fmt:message bundle="${locale}" key="locale.button.ru" var="ru_button"/>
<fmt:message bundle="${locale}" key="locale.button.log_in" var="log_in"/>
<fmt:message bundle="${locale}" key="locale.main.button.register" var="register"/>


<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/main.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/main-page.css"/>
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
                <div class="forms">
                    <form action="Controller?command=registation">
                        <input type="hidden" name="command" value="registration">
                        <button class="form-btn">${register}</button>
                    </form>


                    <form action="Controller?command=logination">
                        <input type="hidden" name="command" value="logination">
                        <button class="form-btn">${log_in}</button>
                    </form>
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
