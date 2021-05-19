<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page import = "java.io.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import ="seoulMate.dto.PlaceDTO"%>
<!DOCTYPE html>
<html>
<head>
<title> 장소 등록 </title>
</head>
<body>
	<jsp:useBean id="dao" class="seoulMate.dao.PlaceDAO" scope="session"/>
	<!-- 1단계 요청 파라미터 받아 DTO에 저장 -->
	<%
	MultipartRequest multi= new MultipartRequest(request, "c:\\upload", 5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
	PlaceDTO dto = PlaceDTO.getInstance();
	
	/*place_basicInfo*/
	HashMap<String, String> placeBasic =null;
	placeBasic.put("pname", multi.getParameter("pName"));
	placeBasic.put("category", multi.getParameter("pCategory"));
	placeBasic.put("oprtime", multi.getParameter("pTime"));
	placeBasic.put("offdays", multi.getParameter("pOff"));
	placeBasic.put("fee", multi.getParameter("pFee"));
	dto.setPlace(placeBasic);
	
	/*place_imgurl : 이미지는 최대 5개까지 올릴 수 있다고 전제 (수정 필요)*/
	//ArrayList<String> imgUrl = null;
	%>
	<%@ include file="multiFile_upload.jsp"%>
	<%	
	dto.setImgUrl(imgUrl);
	
	/*place_locinfo*/
	dto.setAddress(multi.getParameter("pAddress"));
	
	/*place_filter : 다중선택이므로 배열로 받음. age는 유도속성. 안쓸수도 있음*/
	HashMap<String, String[]> placeCheck = null;
	placeCheck.put("paccess", multi.getParameterValues("tripAccess"));
	placeCheck.put("pcomtype", multi.getParameterValues("tripWith"));
	placeCheck.put("pstyle", multi.getParameterValues("tripStyle"));
	placeCheck.put("pkeyword", multi.getParameterValues("tripKeyword"));
	dto.setPcheck(placeCheck);
	
	//ArrayList, HashMap null값 허용하는걸로 알고 있음. 오류나는지 체크해볼 것
	//기타
	dto.setPinfo(multi.getParameter("pInfo"));
	%>
	
	<!-- DAO의 메소드 호출(데이터베이스 접근) -->
	<!-- place_post : 글정보 자동생성(닉네임, 작성일, pno, plikeno)
	사용자로부터 입력받지 않고 DB와의 비교로 자동 생성되는 데이터이므로 DTO를 거치지 않고 바로 DAO에서 처리한다.-->
	<% //#선행 : 로그인할 때만 글 작성이 가능하도록 web.xml 수정 필수
	dto.setNickname(session.getAttribute("nickname").toString());
	dto.setPtitle("pSubtitle"); //게시글 부제임(장소명X)
	
	dao.submit();
	response.sendRedirect("place_detail.jsp"); //#해당 글로 링크연결되도록 수정해야 함
	%>
	


</body>
</html>