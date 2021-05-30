<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> 상세페이지 header+sidebar </title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function login(){
    	window.open("login.jsp","login","width=700, height=520, status=1");
	}
	
</script>
</head>
<body>
<header class="header2">
        <div class = "home detail_home">
            <a class="seoulMate" href='main.jsp'><img class="seoulMate" src="resources/image/SeoulMate2.png"></a>
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
</header>
<aside>
        <div class="btn1" id="btn1">
            <span class="fas fa-bars"></span>
        </div>
        <nav class="sidebar">
            <!-- <div class="text"> 메뉴 </div> -->
            <ul>
                <li><a href="main.jsp"><i class="fas fa-home" aria-hidden="true"></i> 홈</a></li>
                <li><a href="course_main.jsp"><i class="fa fa-map-signs" aria-hidden="true"></i> 추천여행코스</a></li>
                <li><a href="place_main.jsp"><i class="fa fa-map-marked" aria-hidden="true"></i> 인기여행지</a></li>
                <li><a href="#"><i class="fa fa-user" aria-hidden="true"></i> 자유게시판 </a></li>
                <li><a href="plan_main.jsp"><i class="fa fa-cog" aria-hidden="true"></i> 마이페이지 </a></li>
            </ul>
        </nav>
        <script>
            $('.btn1').click(function () {
                $(this).toggleClass("click");
                $('.sidebar').toggleClass("show");
            });
        </script>
    </aside>
</body>
</html>