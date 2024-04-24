<%--
  Created by IntelliJ IDEA.
  User: work
  Date: 4/21/2024
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Registration Successful for  <%= request.getAttribute("name") %></h1>
<form action="login" method="post"></form>
<button type="submit"> <a href="login" class="btn">Now You can Log in </a></button>

</body>

</html>
