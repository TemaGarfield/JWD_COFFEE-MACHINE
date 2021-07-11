<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.button.en" var="en_button"/>
<fmt:message bundle="${locale}" key="locale.button.ru" var="ru_button"/>

<a href="Controller?command=switchlanguage&path=${pageContext.request.requestURI}&locale=en">${en_button}</a>
<a href="Controller?command=switchlanguage&path=${pageContext.request.requestURI}&locale=ru">${ru_button}</a>
