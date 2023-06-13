<%--
  Created by IntelliJ IDEA.
  User: WEN
  Date: 2023/6/13
  Time: 下午 03:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="obj.Currency" %>
<%@ page errorPage="error.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    int amount = Integer.parseInt(request.getParameter("amount"));
    Currency currency = new Currency(amount);
%>

<h2>USD : <%= currency.getUsd()%></h2>
<h2>JPN : <%= currency.getJpn()%></h2>
<h2>CNY : <%= currency.getCny()%></h2>

</body>
</html>
