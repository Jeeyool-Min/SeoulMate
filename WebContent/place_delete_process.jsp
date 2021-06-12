<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="pDao6" class="seoulMate.dao.PlaceDeleteDAO" scope="session"/>
	
	<%
	String pno = request.getParameter("pno");
	 pDao6.deletePlace(Integer.parseInt(pno));
	 
	 response.sendRedirect("place_main.jsp");
	 %>
</body>
</html>