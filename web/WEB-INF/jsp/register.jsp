<%--
  Created by IntelliJ IDEA.
  User: floma
  Date: 26.05.2021
  Time: 2:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form>
        <input type="hidden" name="command" value="savenewuser">
        login: <br/> <input type="email" name="login" value="" placeholder="kotik@example.com"> <br/>
        password: <br/> <input type="password" name="password" value=""> <br/>

        <input type="submit" value="Register">
    </form>

</body>
</html>
