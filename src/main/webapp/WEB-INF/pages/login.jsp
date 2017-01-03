<%--
  Created by IntelliJ IDEA.
  User: wyjwy
  Date: 2017/1/4
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/loginP" method="post">
    <div class="header">
        <h2 class="logo png"></h2>
    </div>
    <ul>
        <li><label>用户名</label><input name="username" type="text"></li>
        <li/>
        <li><label>密　码</label><input name="password" type="password"></li>
        <li/>
        <li class="submits">
            <input class="submit" type="submit" value="登录" />
        </li>
    </ul>
    <div class="copyright">© 2013 - 2014 |</div>
</form:form>
</body>
</html>
