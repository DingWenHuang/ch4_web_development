<%--
  Created by IntelliJ IDEA.
  User: WEN
  Date: 2023/6/13
  Time: 下午 04:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="counter" class="obj.Counter" scope="application"></jsp:useBean>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% counter.increaseCount(); %>
<p>You are visitor number <%= counter.getCount() %></p>
</body>
</html>
