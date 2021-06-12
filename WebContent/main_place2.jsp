<%@page import="seoulMate.dto.PlaceMainDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>HOME - placePost</title>
</head>
<script src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	$('.hover_place_ul').hover(function(){
	$(this).children(".pname").css("font-size","14px");
	$(this).children(".pname").css("font-weight","800");
	},
	function(){
		$(this).children(".pname").css("font-size","0");
	});
})
</script>
<body>
	<article id="section2_place">
		<h2>이달의 인기 여행지</h2>
		<jsp:useBean id="pDao2" class="seoulMate.dao.PlaceMainDAO" scope="session" />
            <%
	        PlaceMainDTO[] dto = pDao2.selectTop6();
          	 for(int i=0; i<dto.length; i++){
			%>
		<div class="preview">
			<span class="thumbnail_txt"> <%=dto[i].getPname() %> </span>
			<div class="thumbnail_img"
				style="background-image: url('upload/<%=dto[i].getImgUrl()%>')">
				<a class="hover_place" href="./place_detail.jsp?pno=<%=dto[i].getPno()%>">
					<ul class="hover_place_ul">
						<li class="hover_place_li pname"> <%=dto[i].getPname() %> </li>
						<li class="hover_place_li"> <%=dto[i].getPtitle() %> </li>
						<li class="hover_place_li"><%=dto[i].getAddress() %></li>
						<li class="hover_place_li"><%=dto[i].getCategory() %></li>
					</ul></a>
			</div>
		</div>
		<%
          	 }
   %>
	</article>
</body>
</html>