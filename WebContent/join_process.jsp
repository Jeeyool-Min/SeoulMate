<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="seoulMate.dao.MemberDAO"%>
<%@ page import="seoulMate.dto.MemberDTO"%>

<!DOCTYPE html>
<html>
<head>
<title> join_process </title>
</head>
<body>
	<jsp:useBean id="dao" class="seoulMate.dao.MemberDAO" scope="session"/>
	<%
	//userId, name, nickname, email, birthdate
	request.setCharacterEncoding("utf-8");
	//Date형식 1994-05-06
	String bdate = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
	Date birthdate = Date.valueOf(bdate);
	
	MemberDTO dto = MemberDTO.getInstance(); //dto객체의 자료를 DAO에서 사용해야 하기 때문에 자바빈즈가 아닌 static 객체를 얻어온다.
	
	dto.setUserId(request.getParameter("userId"));
	dto.setPassword(request.getParameter("password"));
	dto.setName(request.getParameter("name"));
	dto.setNickname(request.getParameter("nickname"));
	dto.setEmail(request.getParameter("eamil"));
	dto.setBirthdate(birthdate);
	
	dao.join();
	response.sendRedirect("join_complete.jsp");
	
	%>
</body>
</html>