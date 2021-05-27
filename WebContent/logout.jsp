<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>logout</title>
</head>
<body>
	<%
	session.invalidate();
	response.sendRedirect("main.jsp");
	%>

</body>
</html>