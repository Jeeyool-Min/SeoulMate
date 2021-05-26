<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="seoulMate.dto.MemberDTO"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>로그인 처리</title>
<script src="resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:useBean id="mDao" class="seoulMate.dao.MemberDAO" scope="session"/>
	<!-- 로그인 폼에서 입력받은 사용자 정보 저장 -->
	<%
	String userId= request.getParameter("userId");
	String password= request.getParameter("password");
	%>
	
	<!-- DB에서 가져와서 비교 -->
	<%
	String nickname = mDao.login(userId, password);
	if(nickname==null){
		%>
<script type="text/javascript">
	alert("일치하는 아이디와 비밀번호가 없습니다.");
	location.href="login.jsp";
</script>
		<%
	} else {
		/*로그인 성공시 회원의 아이디와 닉네임 정보를 세션에 저장*/
		session.setAttribute("userID", userId); 
		session.setAttribute("nickname", nickname);
	%>
	<script>
	opener.location.reload();
	window.close();
	</script>
<%	

	}
%>

</body>
</html>