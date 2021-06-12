<%@page import="seoulMate.dto.MemberDTO"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원정보수정</title>
</head>
<body>
	<jsp:useBean id="dao" class="seoulMate.dao.MemberDAO" scope="session"/>
<%
	request.setCharacterEncoding("utf-8");

	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	String nickname = request.getParameter("nickname");
	String bdate = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
	Date birthdate = Date.valueOf(bdate);
	
	MemberDTO dto = MemberDTO.getInstance(); //dto객체의 자료를 DAO에서 사용해야 하기 때문에 자바빈즈가 아닌 static 객체를 얻어온다.
	
	dto.setUserId(request.getParameter("userId"));
	dto.setPassword(request.getParameter("password"));
	dto.setNickname(request.getParameter("nickname"));
	dto.setEmail(request.getParameter("eamil"));
	dto.setBirthdate(birthdate);
	
	dao.update(userId);
	response.sendRedirect("main.jsp");

%>
</body>
</html>