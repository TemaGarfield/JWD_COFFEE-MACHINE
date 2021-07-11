<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 17.06.2021
  Time: 6:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.admin.edit_ingredient.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.text.amount" var="amount"/>
<fmt:message bundle="${locale}" key="locale.text.cost" var="cost"/>
<fmt:message bundle="${locale}" key="locale.text.type" var="type"/>
<fmt:message bundle="${locale}" key="locale.admin.button.edit" var="edit"/>
<fmt:message bundle="${locale}" key="locale.button.back" var="back"/>

<html>
<head>
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/sources/css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/admin.css">
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
              <div id="edit_ingredient">
                  <form method="post" action="Controller">
                      <input type="hidden" name="command" value="editingredient">
                      <input type="hidden" name="id" value="<c:out value='${ingredient.getId()}'/>">

                      <p>${type}:</p> <input type="text" name="type" value="<c:out value='${ingredient.getType()}'/>" class="input-field">
                      <p>${cost}:</p> <input type="number" step="0.01" name="cost" value="<c:out value='${ingredient.getCost()}'/>" class="input-field">
                      <p>${amount}:</p> <input type="number" name="amount" value="<c:out value='${ingredient.getAmount()}'/>" class="input-field">

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
