<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 18.06.2021
  Time: 8:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.button.back" var="back"/>
<fmt:message bundle="${locale}" key="locale.welcome.top_up_balance.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.welcome.top_up_balance.text.credit_card_number" var="credit_card_number"/>
<fmt:message bundle="${locale}" key="locale.welcome.top_up_balance.text.money" var="money"/>
<fmt:message bundle="${locale}" key="locale.welcome.top_up_balance.button.top_up" var="top_up"/>


<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/welcome.css">
</head>
<body>
    <div class="wrapper">
        <header>
            <div class="container">
                <div class="header-content">
                    <c:import url="user-header.jsp"/>
                </div>
            </div>
        </header>

        <div class="content">
            <div class="container">
                <div class="top-up-balance-form">
                    <form method="post" action="Controller">
                        <input type="hidden" name="command" value="topupbalance">
                        <input type="hidden" name="id" value="<c:out value="${loggedUser.getId()}"/>">

                        <p>${credit_card_number}:</p> <input type="text" name="creditCardNumber" class="input-field">
                        <p>${money}:</p> <input type="number" step="0.01" name="money" class="input-field">
                        <br/>

                        <input type="submit" value="${top_up}" class="welcome-btn">
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
