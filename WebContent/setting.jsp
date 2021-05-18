<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> 마이페이지 | 환경 설정 </title>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/mypage/setting-main.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/course.css'>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
	<%@ include file="header1.jsp" %>
	<main>
	<table>
        <tr>
          <th class="first-column">
            <a href="#"><i class="fas fa-user fa-2x"></i></a>
          </th>
          <td class="second-column"><a href="#">내 계정</a></td>
        </tr>
        <tr class="even">
          <th class="first-column">
            <a href="#"><i class="fas fa-language fa-2x"></i></i></a>
          </th>
          <td class="second-column"><a href="#">언어 설정</a></td>
        </tr>
        <tr>
          <th class="first-column">
            <a href="#"><i class="fas fa-bell fa-2x"></i></i></a>
          </th>
          <td class="second-column"><a href="#">알림</a></td>
        </tr>
        <tr class="even">
          <th class="first-column">
            <a href="#"><i class="far fa-question-circle fa-2x"></i></a>
          </th>
          <td class="second-column"><a href="#">고객 센터</a></td>
        </tr>
        <tr>
          <th class="first-column">
            <a href="#"><i class="fas fa-comments fa-2x"></i></i></a>
          </th>
          <td class="second-column"><a href="#">문의 하기</a></td>
        </tr>
      </table>
    <%@ include file="mypage_sidebutton.jsp"%>
	</main>
	<%@ include file="footer.jsp" %>
</body>
</html>