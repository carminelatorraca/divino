<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="${pageContext.request.contextPath}/shop">Hello Servlet</a>
<% String prova = (String) session.getAttribute("catalog"); %>
<h3><%=prova%></h3>
</body>
</html>