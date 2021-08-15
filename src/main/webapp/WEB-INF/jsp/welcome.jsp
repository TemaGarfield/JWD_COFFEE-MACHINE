<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 31.05.2021
  Time: 3:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.welcome.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.welcome.text.welcome_text" var="welcome_text"/>
<fmt:message bundle="${locale}" key="locale.welcome.text.balance" var="balance"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.top_up_balance" var="top_up_balance"/>
<fmt:message bundle="${locale}" key="locale.text.type" var="type"/>
<fmt:message bundle="${locale}" key="locale.text.cost" var="cost"/>
<fmt:message bundle="${locale}" key="locale.text.amount" var="amount"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.add_to_cart" var="add_to_cart"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.go_to_cart" var="go_to_cart"/>
<fmt:message bundle="${locale}" key="locale.welcome.button.show_all_orders" var="show_all_orders"/>
<fmt:message bundle="${locale}" key="locale.button.log_out" var="log_out"/>
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
                    <h1 class="product-name-coffee">${locale_coffee}</h1>
                    <table>
                        <tr>
                            <th>${type}</th>
                            <th>${cost}</th>
                            <th>${amount}</th>
                        </tr>
                        <c:forEach var="coffee" items="${coffeeList}">

                            <tr>
                                <c:if test="${coffee.getAmount() > 0}">
                                    <td><c:out value="${coffee.getType()}" /></td>
                                    <td><c:out value="${coffee.getCost()}" />$</td>
                                    <td><c:out value="${coffee.getAmount()}"/></td>
                                    <td>
                                        <a href="Controller?command=addtocart&idCoffee=<c:out value="${coffee.getId()}"/>">${add_to_cart}</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>

                    <h1 class="product-name-ingredient">${locale_ingredients}</h1>
                    <table>
                        <tr>
                            <th>${type}</th>
                            <th>${cost}</th>
                            <th>${amount}</th>
                        </tr>
                        <c:forEach var="ingredient" items="${ingredientList}">
                            <tr>
                                <c:if test="${ingredient.getAmount() > 0}">
                                    <td><c:out value="${ingredient.getType()}"/></td>
                                    <td><c:out value="${ingredient.getCost()}"/>$</td>
                                    <td><c:out value="${ingredient.getAmount()}"/></td>
                                    <td><a href="Controller?command=addtocart&idIngredient=<c:out value="${ingredient.getId()}"/>">${add_to_cart}</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <h1>${cost}: <c:out value="${order.getCost()}"/>$</h1>
                    <form action="Controller" method="post">
                        <input type="hidden" name="command" value="gotocart">
                        <input type="submit" value="${go_to_cart}" class="welcome-btn">
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
