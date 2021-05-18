<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="seoulMate.dto.MemberDTO"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>로그인 처리</title>
</head>
<body>
	<jsp:useBean id="dao" class="seoulMate.dao.MemberDAO" scope="session"/>
	<!-- 로그인 폼에서 입력받은 사용자 정보 저장 -->
	<%
	String userId= request.getParameter("userId");
	String password= request.getParameter("password");
	%>
	
	<!-- DB에서 가져와서 비교 -->
	<%
	String nickname = dao.login(userId, password);
	if(nickname==null){
		/*!수정 : 실제로는 일치하는 아이디와 비밀번호가 없다는 로그인 실패 팝업창으로 연결되어야 함*/
		response.sendRedirect("main.jsp");
	} else {
		/*로그인 성공시 회원의 아이디와 닉네임 정보를 세션에 저장*/
		session.setAttribute("userID", userId); 
		session.setAttribute("nickname", nickname);
		/*nickname정보를 잘 가지고 오는지 실험용*/
		out.println(session.getAttribute("nickname").toString() +"님 어서오세요.");
	}
	%>

</body>
</html>