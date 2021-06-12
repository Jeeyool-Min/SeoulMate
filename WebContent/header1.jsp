<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>main</title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function login(){
    	window.open("login.jsp","login","width=700, height=520, status=1");
	}
</script>
</head>
<body>
	<header>
		<div class="home">
			<a class="seoulMate" href='main.jsp'><img class="seoulMate"
				src="resources/image/SeoulMate2.png"></a>
		</div>
		<%
 if(session.getAttribute("nickname")!=null){
	 String nickname = session.getAttribute("nickname").toString();
%>
		<span class="top_button"> <span class="nickname"> <%=nickname %></span>
			<a id="join" href="update_member.jsp">회원수정</a> <a id="login"
			href="logout.jsp">로그아웃</a>
		</span>
		<%
	} else{
		%>
		<span class="top_button"> <a id="join" href="join.jsp">회원가입</a>
			<a id="login" onclick="login()">로그인</a>
		</span>
		<%
	}
%>
		<nav id="menu">
			<ul id="menu_ul">
				<li class="menu_li"><a href="course_main.jsp"> 01 추천여행코스 </a></li>
				<li class="menu_li"><a href="place_main.jsp"> 02 인기여행지 </a></li>
				<li class="menu_li"><a href="#"> 03 자유게시판 </a></li>
				<li class="menu_li"><a href="#"> 04 공지사항 </a></li>
				<li class="menu_li"><a href="plan_main.jsp"> 05 마이페이지 </a></li>
			</ul>
		</nav>
	</header>
</body>
</html>