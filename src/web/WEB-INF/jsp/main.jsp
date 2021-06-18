<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 26.05.2021
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
    <form action="Controller?command=registation">
        <input type="hidden" name="command" value="registration">
        <button>Register</button>
    </form>

    <form action="Controller?command=logination">
        <input type="hidden" name="command" value="logination">
        <button>Log in</button>
    </form>
</body>
</html>
