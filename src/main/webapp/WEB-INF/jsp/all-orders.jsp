<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 20.06.2021
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.welcome.all_orders.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.welcome.all_orders.text.products" var="products"/>
<fmt:message bundle="${locale}" key="locale.button.back" var="back"/>
<fmt:message bundle="${locale}" key="locale.text.cost" var="cost"/>


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
                <div class="orders">
                    <c:forEach var="order" items="${orders}">
                        <div class="order">
                            <table>
                                <tr>
                                    <th>${products}</th>
                                </tr>
                                <c:forEach var="coffee" items="${order.getCoffeeList()}">
                                    <tr>
                                        <td><c:out value="${coffee.getType()}"/></td>
                                    </tr>
                                </c:forEach>

                                <c:forEach var="ingredient" items="${order.getIngredientList()}">
                                    <tr>
                                        <td><c:out value="${ingredient.getType()}"/></td>
                                    </tr>
                                </c:forEach>

                            </table>
                            <p>${cost}:<c:out value="${order.getCost()}"/>$</p>
                        </div>
                    </c:forEach>
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
