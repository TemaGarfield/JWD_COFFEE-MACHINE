<%@ page import="java.util.List" %>
<%@ page import="by.kotik.bean.Coffee" %><%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 01.06.2021
  Time: 2:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.admin.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.text.coffee" var="locale_coffee"/>
<fmt:message bundle="${locale}" key="locale.text.ingredients" var="locale_ingredients"/>
<fmt:message bundle="${locale}" key="locale.text.type" var="type"/>
<fmt:message bundle="${locale}" key="locale.text.cost" var="cost"/>
<fmt:message bundle="${locale}" key="locale.text.amount" var="amount"/>
<fmt:message bundle="${locale}" key="locale.button.add" var="add"/>
<fmt:message bundle="${locale}" key="locale.admin.button.add_coffee" var="add_coffee"/>
<fmt:message bundle="${locale}" key="locale.admin.button.add_ingredient" var="add_ingredient"/>
<fmt:message bundle="${locale}" key="locale.button.log_out" var="log_out"/>
<fmt:message bundle="${locale}" key="locale.button.delete" var="delete"/>
<fmt:message bundle="${locale}" key="locale.admin.button.edit" var="edit"/>


<html>
<head>
    <title>${title}</title>
    <link href="${pageContext.request.contextPath}/sources/css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/sources/css/admin.css">

</head>
<body>
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
                <h1>${locale_coffee}</h1>
                <table style="text-align: center">
                    <tr>
                        <th>${type}</th>
                        <th>${cost}</th>
                        <th>${amount}</th>
                    </tr>
                    <c:forEach var="coffee" items="${coffeeList}">
                        <tr>
                            <td><c:out value="${coffee.getType()}" /></td>
                            <td><c:out value="${coffee.getCost()}" />$</td>
                            <td><c:out value="${coffee.getAmount()}"/></td>
                            <td><a href="Controller?command=gotoeditcoffeepage&id=<c:out value='${coffee.getId()}'/> ">${edit}</a></td>
                            <td><a href="Controller?command=deletecoffee&id=<c:out value='${coffee.getId()}' />">${delete}</a></td>
                        </tr>
                    </c:forEach>
                </table>

                <button onclick="openForm('add_coffee')" class="admin-btn">${add_coffee}</button>
                <div  id="add_coffee" style="display: none">
                    <form method="post" action="Controller">
                        <input type="hidden" name="command" value="savenewcoffee">
                        ${type}: <input type="text" name="type" class="input-field">
                        ${cost}: <input type="number" step="0.01" name="cost" class="input-field">
                        ${amount}: <input type="number" name="amount" class="input-field">

                        <input type="submit" value="${add}" class="admin-mini-btn">
                    </form>

                </div>

                <h1>${locale_ingredients}</h1>
                <table>
                    <tr>
                        <th>${type}</th>
                        <th>${cost}</th>
                        <th>${amount}</th>
                    </tr>
                    <c:forEach var="ingredient" items="${ingredientList}">
                        <tr>
                            <td><c:out value="${ingredient.getType()}"/></td>
                            <td><c:out value="${ingredient.getCost()}"/>$</td>
                            <td><c:out value="${ingredient.getAmount()}"/></td>
                            <td><a href="Controller?command=gotoeditingredient&id=<c:out value="${ingredient.getId()}"/> ">${edit}</a></td>
                            <td><a href="Controller?command=deleteingredient&id=<c:out value='${ingredient.getId()}'/>">${delete}</a></td>
                        </tr>
                    </c:forEach>
                </table>

                <button onclick="openForm('add_ingredient')" class="admin-btn">${add_ingredient}</button>
                <div  id="add_ingredient" style="display: none">
                    <form method="post" action="Controller">
                        <input type="hidden" name="command" value="savenewingredient">

                        ${type}: <input type="text" name="type" class="input-field">
                        ${cost}: <input type="number" step="0.01" name="cost" class="input-field">
                        ${amount}: <input type="number" name="amount" class="input-field">

                        <input type="submit" value="${add}" class="admin-mini-btn">
                    </form>
                </div>

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
</body>
</html>

<script>
    function openForm(id) {
        document.getElementById(id).style.display = "block";
    }
</script>


