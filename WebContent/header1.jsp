<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> main </title>
<meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
<header>
        <div class = "home">
            <a class="seoulMate" href='main.jsp'><img class="seoulMate" src="resources/image/SeoulMate2.png"></a>
        </div>
        <span class="top_button">
            <a id = "join" href="join.jsp" >회원가입</a>
            <a id = "login" href="login.jsp" >로그인</a>
        </span>
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