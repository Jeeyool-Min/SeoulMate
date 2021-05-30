<%@page import="seoulMate.dto.PlaceSearchDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>검색기능</title>
</head>
<body>
	<jsp:useBean id="pDao7" class="seoulMate.dao.placeSearchDAO" scope="session"/>
	<%
	PlaceSearchDTO dto = PlaceSearchDTO.getInstance();
	
	request.setCharacterEncoding("utf-8");
	String tripArea = request.getParameter("tripArea");
	String[] tripStyle = request.getParameterValues("tripStyle");
	String[] tripAges = request.getParameterValues("tripAges");
	String[] tripWith = request.getParameterValues("tripWith");
	String[] tripAccess = request.getParameterValues("tripAccess");
	String tripKeyword = request.getParameter("tripKeyword");
	
	if(tripStyle!=null){
		dto.setPstyle(tripStyle);
		for(int i=0; i<tripStyle.length; i++){out.print(tripStyle[i] +"  ");}
		out.print("========="+"<br>");
	}
		
	if(tripAges!=null){
		dto.setPage(tripAges);
		for(int i=0; i<tripAges.length; i++){out.print(tripAges[i]+"  ");}
		out.print("========="+"  ");
	}
	if(tripWith!=null){
		dto.setPcomtype(tripWith);
		for(int i=0; i<tripWith.length; i++){out.print(tripWith[i]+"  ");}
		out.print("========="+"  ");
	}
	
	if(tripAccess!=null){
		dto.setPaccess(tripAccess);
		for(int i=0; i<tripAccess.length; i++){	out.print(tripAccess[i]+"  ");	}
		out.print("========="+"<br>");
	}
	if(tripArea.equals("전체")){
		dto.setGu("서울");
	}else{
		dto.setGu(tripArea);
		out.print(tripArea+"<br>");
	}
	if(tripKeyword !=null){
		dto.setPkeyword(tripKeyword);
		out.print(tripKeyword+"<br>");
	}
	
	out.print(pDao7.makequery() +"<br>");
	out.print(pDao7);
	%>
	<a href="main.jsp">메인으로</a>
	

</body>
</html>