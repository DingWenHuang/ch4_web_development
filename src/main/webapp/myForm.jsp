<%--
  Created by IntelliJ IDEA.
  User: WEN
  Date: 2023/6/12
  Time: 下午 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/formHandling" method="get">
    <%-- 表示這個表格我們按交出的時候，會用get的方式送到前述的網址去 --%>
    <%//我是註解%>
    <label>Book name:</label>
    <input type="text" name="name">
    <br>
    <label>Price:</label>
    <input type="number" name="price">
    <br>
    <label>Author:</label>
    <input type="text" name="author">
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
