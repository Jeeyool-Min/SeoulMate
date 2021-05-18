<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> HOME </title>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_header.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/main/main_content.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_buttonChkbox.css'>
</head>
<body>
	<%@ include file="header1.jsp" %>
<main>
	<jsp:include page="filter.jsp"/>
	<section id="content">
	<%@ include file="main_course.jsp" %>
	 <hr class="hr_divider">
	<%@ include file="main_place.jsp" %>    
	</section>
</main>
	<%@ include file="footer.jsp" %>
</body>
</html>