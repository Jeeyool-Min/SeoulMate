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
<title> 장소 수정 </title>
</head>
<body>
	<jsp:useBean id="pDao5" class="seoulMate.dao.PlaceUpdateDAO" scope="session"/>
	<!-- 1단계 요청 파라미터 받아 DTO에 저장 -->
	<%
	
	/*#++++장소명으로 unique 검증해야함*/
	MultipartRequest multi= new MultipartRequest(request, "c:\\upload", 8*1024*1024, "utf-8", new DefaultFileRenamePolicy());
	int pno = Integer.parseInt(multi.getParameter("pno"));
	PlaceDTO dto = PlaceDTO.getInstance();
	String imgUrl=multi.getParameter("noChange"); //여기서 url은 old_name인데 반복 피하기 위해 imgUrl로씀
	
	if(imgUrl=="") //이미지가 변경되었다면
	{
		/*첨부 이미지 파일 경로*/
		//폼페이지에서 전송된 요청 파라미터 중 파일을 받도록 multipartRequest객체 타입의 getFileNames()메소드
		Enumeration files = multi.getFileNames();
		//폼페이지에서 전송된 요청 파라미터 중 파일을 받도록 Enumeration 객체 타입의 nextElement()메소드
		String fname = (String) files.nextElement();
		//폼페이지에서 전송되어 서버에 업로드된 파일을 가져오도록 MultipartRequest객체 타입의 getFilesystemName()메소드
		imgUrl = multi.getFilesystemName(fname);
		System.out.print("파일명 " +imgUrl);
	}
	
	/*place_post*/
	dto.setPno(pno);
	dto.setPtitle(multi.getParameter("pSubtitle"));
	
	/*place_basicInfo*/
	HashMap<String, String> placeBasic = new HashMap();
	placeBasic.put("pname", multi.getParameter("pName"));
	placeBasic.put("category", multi.getParameter("pCategory"));
	placeBasic.put("oprtime", multi.getParameter("pTime"));
	placeBasic.put("offdays", multi.getParameter("pOff"));
	placeBasic.put("fee", multi.getParameter("pFee"));
	placeBasic.put("imgUrl", imgUrl);
	placeBasic.put("tel", multi.getParameter("pTel"));
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
	//줄바꿈 처리
	String pInfo = multi.getParameter("pInfo"); 
	if(pInfo!=null && pInfo.contains("\r\n")){
	pInfo = pInfo.replace("\r\n", "<br>");
	}
	dto.setPinfo(pInfo);
	%>
	
	<!-- DAO의 메소드 호출(데이터베이스 접근) -->
	<!-- place_post : 글정보 자동생성(닉네임, 작성일, pno, plikeno)
	사용자로부터 입력받지 않고 DB와의 비교로 자동 생성되는 데이터이므로 DTO를 거치지 않고 바로 DAO에서 처리한다.-->
	<% //#선행 : 로그인할 때만 글 작성이 가능하도록 web.xml 수정 필수
	//dto.setNickname(session.getAttribute("nickname").toString());
	dto.setPtitle(multi.getParameter("pSubtitle")); //게시글 부제임(장소명X)
	%>
	
	
	<%
	pDao5.modify(pno);
	
	response.sendRedirect("place_detail.jsp?pno="+pno);
	//response.sendRedirect("place_detail.jsp"); //#해당 글로 링크연결되도록 수정해야 함
	%>
	


</body>
</html>