<%@page import="seoulMate.dto.PlaceMainDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>HOME - placePost</title>
</head>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_paging.css'>
<script src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	$('.hover_place_ul').hover(function(){
	$(this).children(".pname").css("font-size","14px");
	$(this).children(".pname").css("font-weight","800");
	},
	function(){
		$(this).children(".pname").css("font-size","0");
	});
})
</script>
<jsp:useBean id="pDao2" class="seoulMate.dao.PlaceMainDAO" scope="session" />
<%
	int pageSize = 6; // 한 페이지에 출력할 레코드 수
	
	// 페이지 링크를 클릭한 번호 / 현재 페이지
	String pageNum = request.getParameter("pageNum");
	if (pageNum == null){ // 클릭한게 없으면 1번 페이지
		pageNum = "1";
	}
	// 연산을 하기 위한 pageNum 형변환 / 현재 페이지
	int currentPage = Integer.parseInt(pageNum);
	
	// 해당 페이지에서 시작할 레코드 / 마지막 레코드
	int startRow = (currentPage - 1) * pageSize + 1;
	int endRow = currentPage * pageSize;
	
	int count = 0;
	count = pDao2.getCount(); // 데이터베이스에 저장된 총 갯수

%>
<body>
	<article id="section2_place">
		<h2>이달의 인기 여행지</h2>
            <%
            if(count >0){
            	
	        PlaceMainDTO[] dto = pDao2.selectByView(startRow,endRow);
          	 for(int i=0; i<dto.length; i++){
			%>
		<div class="preview">
			<span class="thumbnail_txt"> <%=dto[i].getPname() %> </span>
			<div class="thumbnail_img"
				style="background-image: url('upload/<%=dto[i].getImgUrl()%>')">
				<a class="hover_place" href="./place_detail.jsp?pno=<%=dto[i].getPno()%>&pageNum=<%=currentPage%>">
					<ul class="hover_place_ul">
						<li class="hover_place_li pname"> <%=dto[i].getPname() %> </li>
						<li class="hover_place_li"> <%=dto[i].getPtitle() %> </li>
						<li class="hover_place_li"><%=dto[i].getAddress() %></li>
						<li class="hover_place_li"><%=dto[i].getCategory() %></li>
					</ul></a>
			</div>
		</div>
		<%
          	 }
            } else {
            	%>
            	<h1>게시글이 없습니다.</h1>
            	<%
            }
            	// 페이징  처리
				if(count > 0){ //데이터가 존재하면
				// 총 페이지의 수
				int pageCount = count / pageSize + (count%pageSize == 0 ? 0 : 1);
				// 한 페이지에 보여줄 페이지 블럭(링크) 수
				int pageBlock = 5;
				// 한 페이지에 보여줄 시작 및 끝 번호(예 : 1, 2, 3 ~ 5 / 6, 7, 8, 9, 10)
				int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
				int endPage = startPage + pageBlock - 1;
				
				// 마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
				if(endPage > pageCount){endPage = pageCount;}
				%>
			<div class="paging">
			<%
				if(startPage > pageBlock){ // 페이지 블록수보다 startPage가 클경우 이전 링크 생성
		%>
					<a href="place_main.jsp?pageNum=<%=startPage-pageBlock%>"> ◀ </a>	
		<%			
				}
				for(int i=startPage; i <= endPage; i++){ // 페이지 블록 번호
					if(i == currentPage){ // 현재 페이지에는 링크를 설정하지 않음
		%>
				<span class="curr">
						<%=i %>
				</span>
		<%									
					}else{ // 현재 페이지가 아닌 경우 링크 설정
		%>
						<a href="place_main.jsp?pageNum=<%=i%>"><%=i %></a>
		<%	
					}
				} // for end
				
				if(endPage < pageCount){ // 현재 블록의 마지막 페이지보다 페이지 전체 블록수가 클경우 다음 링크 생성
		%>
					<a href="place_main.jsp?pageNum=<%=startPage + pageBlock %>"> ▶ </a>
			</div>
		<%			
				}
			}
		%>

	</article>
</body>
</html>