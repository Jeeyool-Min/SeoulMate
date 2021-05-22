<%@page import="java.util.Enumeration"%>
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
	<jsp:useBean id="pDao" class="seoulMate.dao.PlaceDAO" scope="session"/>
	<!-- 1단계 요청 파라미터 받아 DTO에 저장 -->
	<%
	/*#++++장소명으로 unique 검증해야함*/
	MultipartRequest multi= new MultipartRequest(request, "c:\\upload", 8*1024*1024, "utf-8", new DefaultFileRenamePolicy());
	PlaceDTO dto = PlaceDTO.getInstance();
	
	/*첨부 이미지 파일 경로*/
	//폼페이지에서 전송된 요청 파라미터 중 파일을 받도록 multipartRequest객체 타입의 getFileNames()메소드
	Enumeration files = multi.getFileNames();
	//폼페이지에서 전송된 요청 파라미터 중 파일을 받도록 Enumeration 객체 타입의 nextElement()메소드
	String fname = (String) files.nextElement();
	//폼페이지에서 전송되어 서버에 업로드된 파일을 가져오도록 MultipartRequest객체 타입의 getFilesystemName()메소드
	String imgUrl = multi.getFilesystemName(fname);
	System.out.print("파일명 " +imgUrl);
	
	/*place_post*/
	dto.setPtitle(multi.getParameter("pSubtitle"));
	
	/*place_basicInfo*/
	HashMap<String, String> placeBasic = new HashMap();
	placeBasic.put("pname", multi.getParameter("pName"));
	placeBasic.put("category", multi.getParameter("pCategory"));
	placeBasic.put("oprtime", multi.getParameter("pTime"));
	placeBasic.put("offdays", multi.getParameter("pOff"));
	placeBasic.put("fee", multi.getParameter("pFee"));
	placeBasic.put("imgUrl", imgUrl);
	dto.setPlace(placeBasic);
	
	%>

	<%	
	//dto.setImgUrl(imgUrl);
	//out.println(dto.getImgUrl().get(0));
	//out.println(dto.getImgUrl().get(1));
	
	
	/*place_locinfo*/
	dto.setAddress(multi.getParameter("pAddress"));
	
	/*place_filter : 다중선택이므로 배열로 받음. age는 유도속성. 안쓸수도 있음*/
	HashMap<String, String[]> placeCheck = new HashMap();
	placeCheck.put("paccess", multi.getParameterValues("tripAccess"));
	placeCheck.put("pcomtype", multi.getParameterValues("tripWith"));
	placeCheck.put("pstyle", multi.getParameterValues("tripStyle"));
	placeCheck.put("pperiod", multi.getParameterValues("tripDays"));
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
	dto.setPtitle(multi.getParameter("pSubtitle")); //게시글 부제임(장소명X)
	%>
	
	<img src="upload/<%=dto.getPlace().get("imgUrl")%>" style="width:300px" alt="이미지">
	
	<%
	
	out.println("장소명: " +dto.getPlace().get("pname") +"<br>");
	out.println("장소 부제: " +dto.getPtitle() +"<br>");
	out.println("카테고리: " +dto.getPlace().get("category")+"<br>");
	out.println("운영시간: " +dto.getPlace().get("oprtime")+"<br>");
	out.println("휴무: " +dto.getPlace().get("offdays")+"<br>");
	out.println("이용요금: " +dto.getPlace().get("fee")+"<br>");
	out.println("파일명: " +dto.getPlace().get("imgUrl")+"<br>");
	out.println("====================================="+"<br>");
	out.println("글쓴이: " +dto.getNickname()+"<br>");
	out.println("장소설명: " +dto.getPinfo()+"<br>");
	
	if(dto.getPcheck().get("paccess")!=null)
	for(int i=0; i<dto.getPcheck().get("paccess").length; i++){
	out.println("접근수단: " + dto.getPcheck().get("paccess")[i]+"<br>");
	} else{out.println("접근 수단 입력값이 없습니다.");}
	
	if(dto.getPcheck().get("pcomtype")!=null)
	for(int i=0; i<dto.getPcheck().get("pcomtype").length; i++){
	out.println("동반: " + dto.getPcheck().get("pcomtype")[i]+"<br>");
	}else{out.print("동반 형태 체크X");}
	
	if(dto.getPcheck().get("pstyle")!=null)
	for(int i=0; i<dto.getPcheck().get("pstyle").length; i++){
	out.println("여행스타일: " + dto.getPcheck().get("pstyle")[i]+"<br>");
	}else{out.println("여행스타일 체크X");}
	
	if(dto.getPcheck().get("pkeyword")!=null)
	for(int i=0; i<dto.getPcheck().get("pkeyword").length; i++){
	out.println("키워드: " + dto.getPcheck().get("pkeyword")[i]+"<br>");
	}
	if(dto.getPcheck().get("pperiod")!=null)
	for(int i=0; i<dto.getPcheck().get("pperiod").length; i++){
	out.println("여행기간: " + dto.getPcheck().get("pperiod")[i]+"<br>");
	}
	else out.println("키워드 체크X");
	
	
	pDao.submit();
	
	//response.sendRedirect("place_detail.jsp"); //#해당 글로 링크연결되도록 수정해야 함
	%>
	


</body>
</html>