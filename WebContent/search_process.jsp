<%@page import="java.util.ArrayList"%>
<%@page import="seoulMate.dto.PlaceSearchDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_header.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/main/search.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_buttonChkbox.css'>
<meta charset="utf-8">
<title>검색기능</title>
</head>
	<jsp:useBean id="pDao7" class="seoulMate.dao.placeSearchDAO" scope="session"/>
	<%
	PlaceSearchDTO dto = new PlaceSearchDTO();
	
	request.setCharacterEncoding("utf-8");
	String tripArea = request.getParameter("tripArea");
	String[] tripStyle = request.getParameterValues("tripStyle");
	String[] tripAges = request.getParameterValues("tripAges");
	String[] tripWith = request.getParameterValues("tripWith");
	String[] tripAccess = request.getParameterValues("tripAccess");
	String tripKeyword = request.getParameter("tripKeyword");
	
	if(tripStyle!=null){dto.setPstyle(tripStyle);}
	if(tripAges!=null){	dto.setPage(tripAges);}
	if(tripWith!=null){	dto.setPcomtype(tripWith);}
	if(tripAccess!=null){dto.setPaccess(tripAccess);}
	if(tripArea!=null & tripArea.equals("전체")){
		dto.setGu("서울");
	}else{
		dto.setGu(tripArea);}
	if(tripKeyword !=null){	dto.setPkeyword(tripKeyword);}
	%>
<body>
	<%@ include file="header1.jsp" %>
<main>
			<%ArrayList<PlaceSearchDTO> searchDTO = pDao7.makequery(dto); %>
	<article id="section2_place">
		<h2>총 <%=searchDTO.get(searchDTO.size()-1).getRsSize()%>건의 검색 결과가 있습니다.</h2>
            <%
          	 for(int i=0; i<searchDTO.size(); i++){
			%>
		<div class="preview">
			<span class="thumbnail_txt"> <%=searchDTO.get(i).getGu() %> </span>
			<div class="thumbnail_img"
				style="background-image: url('upload/<%=searchDTO.get(i).getImgUrl()%>')">
				<a class="hover_place" href="./place_detail.jsp?pno=<%=searchDTO.get(i).getPno()%>">
					<ul class="hover_place_ul">
						<li class="hover_place_li"><%=searchDTO.get(i).getStyle() %></li>
						<li class="hover_place_li"><%=searchDTO.get(i).getComtype() %></li>
						<li class="hover_place_li"><%=searchDTO.get(i).getAccess() %></li>
						<li class="hover_place_li"><%=searchDTO.get(i).getAge() %></li>
					</ul></a>
					<div class="description">
						<span> <%=searchDTO.get(i).getCategory() %></span>
						<span> <%=searchDTO.get(i).getPname() %></span>
						<span style="font-size:12px;"> <%=searchDTO.get(i).getKeyword()%></span>
					</div>
			</div>
		</div>
		<%
          	 }
   %>
	</article>
	<a class="back" href="main.jsp" style="transform:rotate(-0.03deg); ">메인으로</a>
	</main>
	<%@ include file="footer.jsp" %>
</body>
</html>