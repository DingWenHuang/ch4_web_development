<%--
  Created by IntelliJ IDEA.
  User: WEN
  Date: 2023/6/13
  Time: 下午 03:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    double jpn = 4.4382;
    double usd = 0.0345;
    double cny = 0.2300;
    int amount = Integer.parseInt(request.getParameter("amount"));
%>

<h2>USD : <%= amount * usd%></h2>
<h2>JPN : <%= amount * jpn%></h2>
<h2>CNY : <%= amount * cny%></h2>

</body>
</html>
