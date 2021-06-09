<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 26.05.2021
  Time: 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="login">
        login: <br/> <input type="text" name="login" value=""><br/>
        password: <br/> <input type="password" name="password" value=""> <br/>

        <input type="submit" value="Log in"> <br/>
    </form>

    <a href="Controller?command=registration">Create account</a>

</body>
</html>
