<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 18.06.2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.text.type" var="type"/>
<fmt:message bundle="${locale}" key="locale.text.cost" var="cost"/>
<fmt:message bundle="${locale}" key="locale.button.back" var="back"/>
<fmt:message bundle="${locale}" key="locale.button.submit" var="submit"/>
<fmt:message bundle="${locale}" key="locale.welcome.cart.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.button.delete" var="delete"/>
<fmt:message bundle="${locale}" key="locale.text.coffee" var="locale_coffee"/>
<fmt:message bundle="${locale}" key="locale.text.ingredients" var="locale_ingredients"/>

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
                <div class="tables">
                    <c:if test="${!order.getCoffeeList().isEmpty()}">
                        <h1>${locale_coffee}</h1>
                        <table style="text-align: center">
                            <tr>
                                <th>${type}</th>
                                <th>${cost}</th>
                            </tr>
                            <c:forEach var="coffee" items="${order.getCoffeeList()}" varStatus="loop">
                                <tr>
                                    <td><c:out value="${coffee.getType()}" /></td>
                                    <td><c:out value="${coffee.getCost()}" />$</td>
                                    <td><a href="Controller?command=cartdeletecoffee&indexCoffee=${loop.index}">${delete}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

                    <c:if test="${!order.getIngredientList().isEmpty()}">
                        <h1>${locale_ingredients}</h1>
                    <table style="text-align: center">
                        <tr>
                            <th>${cost}</th>
                            <th>${type}</th>
                        </tr>
                        <c:forEach var="ingredient" items="${order.getIngredientList()}" varStatus="loop">
                            <tr>
                                <td><c:out value="${ingredient.getCost()}"/>$</td>
                                <td><c:out value="${ingredient.getType()}"/></td>
                                <td><a href="Controller?command=cartdeleteingredient&indexIngredient=${loop.index}">${delete}</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    </c:if>

                    <h1>${cost}: <c:out value="${order.getCost()}"/>$</h1>

                    <c:if test="${!message.isEmpty()}">
                        <c:out value="${message}"/>
                        <c:set var="message" scope="session" value=""/>
                    </c:if>

                    <form method="post" action="Controller">
                        <input type="hidden" value="saveneworder" name="command">
                        <input type="submit" value="${submit}" class="welcome-btn">
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
