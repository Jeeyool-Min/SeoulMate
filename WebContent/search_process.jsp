<%@page import="java.util.ArrayList"%>
<%@page import="seoulMate.dto.PlaceSearchDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_header.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/main/search.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_buttonChkbox.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_paging.css'>
<meta charset="utf-8">
<title>검색기능</title>
</head>
	<jsp:useBean id="pDao7" class="seoulMate.dao.PlaceSearchDAO" scope="session"/>
	<%
	request.setCharacterEncoding("utf-8");
	String pageNum = request.getParameter("pageNum"); //현재페이지
	int pageSize = 6; //한페이지에 6개씩 출력
	
		PlaceSearchDTO dto = new PlaceSearchDTO();
		request.setCharacterEncoding("utf-8");
		if(pageNum==null){
			String tripArea = request.getParameter("tripArea");
			String[] tripStyle = request.getParameterValues("tripStyle");
			String[] tripAges = request.getParameterValues("tripAges");
			String[] tripWith = request.getParameterValues("tripWith");
			String[] tripAccess = request.getParameterValues("tripAccess");
			String tripKeyword = request.getParameter("tripKeyword");
			
			if(tripStyle!=null){dto.setPstyle(tripStyle);}
			if(tripAges!=null){	dto.setPage(tripAges);}
			if(tripWith!=null){	dto.setPcomtype(tripWith);}
			if(tripAccess!=null){dto.setPaccess(tripAccess);}
			if(tripArea!=null & tripArea.equals("전체")){
				dto.setGu("서울");
			}else{
				dto.setGu(tripArea);}
			if(tripKeyword !=null){	dto.setPkeyword(tripKeyword);}
		}else{
		}
	if(pageNum ==null){ pageNum="1";} //디폴트는 1번(클릭하지 않을 때 첫페이지)
	%>
<body>
	<%@ include file="header1.jsp" %>
<main>
			<%
			
			int currentPage =Integer.parseInt(pageNum);

			// 해당 페이지에서 시작할 레코드 / 마지막 레코드
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;

			int fCount = 0;
			String sql = pDao7.makequery(dto);
			fCount = pDao7.getfCount(sql);
				ArrayList<PlaceSearchDTO> searchDTO = new ArrayList();
				searchDTO = pDao7.getResult(sql, startRow, endRow); 
			
			%>
	<article id="section2_place">
		<h2>총 <%=fCount%>건의 검색 결과가 있습니다.</h2>
		<a class="back" href="main.jsp" style="transform:rotate(-0.03deg); ">메인으로</a>
            <%
            if(fCount>0){
	          	for(int i=0; i<searchDTO.size(); i++){
	    			%>
	    			<div class="preview">
	    				<span class="thumbnail_txt"> <%=searchDTO.get(i).getGu() %> </span>
	    				<div class="thumbnail_img"
	    					style="background-image: url('upload/<%=searchDTO.get(i).getImgUrl()%>')">
	    					<a class="hover_place" href="./place_detail.jsp?pno=<%=searchDTO.get(i).getPno()%>&pageNum=<%=currentPage%>">
	    						<ul class="hover_place_ul">
	    							<li class="hover_place_li"><%=searchDTO.get(i).getStyle() %></li>
	    							<li class="hover_place_li"><%=searchDTO.get(i).getComtype() %></li>
	    							<li class="hover_place_li"><%=searchDTO.get(i).getAccess() %></li>
	    							<li class="hover_place_li"><%=searchDTO.get(i).getAge() %></li>
	    						</ul></a>
	    						<div class="description">
	    							<span> <%=searchDTO.get(i).getCategory() %></span>
	    							<span> <%=searchDTO.get(i).getPname() %></span>
	    							<span style="font-size:12px;"> <%=searchDTO.get(i).getKeyword()%></span>
	    						</div>
	    				</div>
	    			</div>
	    		<%
	          	}
            }
            
            //페이징처리 (fCount>0일때, 여기선 항상 0보다 크다.)
            //검색된 레코드의 총 페이지 수
           int pageCount = fCount / pageSize + (fCount%pageSize == 0 ? 0: 1);
           //한 페이지에 보여줄 페이지 블럭 수
           int pageBlock = 5;
           //한 페이지에 보여줄 시작 및 끝번호 (1~5)
           int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
           int endPage = startPage + pageBlock -1;
           
           //마지막 페이지가 총 페이지 수보다 크면 endPage를 pageCount로 만듦(즉 마지막페이지수를 총페이지수로 맞춰줌 17페이지까지 있을 떄 20페이지인데 17로 조정)
           if(endPage >pageCount){
        	   endPage = pageCount;
           }
           %>
     	<div class="paging search">
     	<%
           if(startPage >pageBlock){ //5페이지 이상일때 이전 표시 해줘야함
     %>
				<a href="search_process.jsp?pageNum=<%=startPage-pageBlock%>"> ◀ </a>	
	<%			
			}
			for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
				if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
	%>
			<span class="curr">
					<%=i%>
			</span>
	<%									
				}else{ // 현재 페이지가 아닌 경우 링크 설정
	%>
					<a href="search_process.jsp?pageNum=<%=i%>&searchDTO=<%=searchDTO%>"><%=i%></a>
	<%	
				}
			} // for end
			
			if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
	%>
				<a href="search_process.jsp?pageNum=<%=startPage + pageBlock %>"> ▶ </a>
		</div>
		<%
			}
			
		%>
	</article>
	</main>
	<%@ include file="footer.jsp" %>
</body>
</html>