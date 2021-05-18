<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> HOME </title>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/main/main_content.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/course.css'>
</head>
<body>
	<%@ include file="header1.jsp" %>
<main>
	<div class="main_title">
    	<h1> 인기여행지 </h1>
    </div>
    <hr class="hr_title">
	<section id="content">
	<%@ include file="main_place.jsp" %>    
	</section>
	    <ul class="side-bar long">
        <li><a href="place_writing.jsp"> 인기 여행지 소개하기</a></li>
        </ul>
</main>
	<%@ include file="footer.jsp" %>
</body>
</html>