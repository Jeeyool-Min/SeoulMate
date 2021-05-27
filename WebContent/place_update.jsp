<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@page import="seoulMate.dto.PlaceDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title> 서울여행 길잡이 SeoulMate </title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/main.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/place.css'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="resources/js/search.js"></script>
    <script src="resources/js/filechk.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff320fced3e6ec842c7367acc991287d&libraries=services"></script>
</head>

<body>
	<jsp:useBean id="pDao4" class="seoulMate.dao.PlaceUpdateDAO" scope="session"/>
	<%
		int pno = Integer.parseInt(request.getParameter("pno")); 
		//pDao4.loadPost(request.getParameter("pno")); //DTO에 담아줬음
		pDao4.loadInfo(pno);
		PlaceDTO dto = PlaceDTO.getInstance();
	%>
    <script type="text/javascript">
    function findAddress(){
    	gu = window.open("searchAddress.jsp", "search_keyword","width=940, height=520, resizable, status=1");
    }
    
    //파일 새롭게 첨부되었는지 파악
    function checkImgChange(){
    	//첨부된 파일이 없다면 hidden input으로 지금 파일명을 넘겨준다.
    	var imgname = document.getElementById('imgInput').value;
    	var imgDTO='<%=dto.getPlace().get("imgUrl")%>';
    	if(imgname==""){//파일을 따로 첨부하지 않았다면 value는 ""이다. **null도 아님**
    		var result =$('#imgNoChange').val(imgDTO);
    		//if(confirm("입력하신 이미지의 이름이 " + $('#imgNoChange').val()+"입니까?")){return true;}else{return false;}
    	//}else{var result =$('#imgNoChange').val(); 파일 첨부했다면 해당 값은 변함없이 ""이어야함(테스트용)if(confirm(result=="")) return true; else return false;
    		///if(confirm("입력하신 이미지의 이름이 " +imgname+"입니까?"))return true; else return false;
    	}
    }
    
    </script>
	<jsp:include page="header2.jsp"/>
    <main>
    <article class="write">
        <h2 class="tit_write"> 혼자 알던 <b>여행지</b>를 공유해요</h2>
    <hr class="hr_write_title">
    <form name="writing_form" id="w_form" action="place_update_process.jsp" method="post" enctype="multipart/form-data">
     <div class="first_line first">
     <input type="hidden" name="pno" value='<%=pno%>'>
     <input type="hidden" name="noChange" id="imgNoChange">
     <label for="placeName">장소명*</label><input type="text" id="placeName" name="pName" size="5" autofocus maxlength="15" value='<%=dto.getPlace().get("pname")%>'>
     <button type="button" class="btn addSchBtn" onclick="findAddress()">장소 검색</button>
     </div>
     <div class="first_line second">
     <label for="placeSubtitle">부제*</label><input type="text" id="placeSubtitle" name="pSubtitle" required maxlength="30" value='<%=dto.getPtitle()%>'>
     </div>
        <div class="img_preview">
            <img id="preview" src="upload/<%=dto.getPlace().get("imgUrl")%>" alt="image">
            <input type="file" class="hiddenBtn" name="pImage" accept="image/*" 
            	id="imgInput"  style="apperance: none; -webkit-apperance: none;"> 
            <label for="imgInput" class="fileBtn"> 이미지 수정 </label>
            <!-- <span id="fileName">선택된 파일없음</span> -->
        </div>
        <div class="basicInfo">
        <ul>
            <li><label for="placeCategory">장소분류*</label><select id="placeCategory" name="pCategory" required>
                <option value="쇼핑"> 쇼핑 </option>
                <option value="문화시설"> 문화시설 </option>
                <option value="역사"> 역사 </option>
                <option value="공원"> 공원 </option>
                <option value="레포츠"> 레포츠 </option>
                <option value="음식점"> 음식점 </option>
                <option value="카페"> 카페 </option>
                <option value="이색체험공간"> 이색체험공간 </option>
                <option value="기타"> 기타 </option>
            </select></li>
            <script type="text/javascript">
            $(function(){
	            if($('option').val=='<%=dto.getPlace().get("category")%>'){
	            	$(this).prop("selected",true);
	            }
	            //다른 방법 : $('#testSelect').val('test2').prop("selected",true);
            	
            }());
            </script>        
            <li><label for="placeFee">이용요금</label><input type="text" id="placeFee" name="pFee" size="10" maxlength="50" value='<%=dto.getPlace().get("fee")%>'></li>
            <li class="placeAccess"><label for="placeOff">휴무일 *</label><input type="text" id="placeOff" name="pOff" size="4" required value='<%=dto.getPlace().get("offdays")%>'></li>
            <li class="placeAccess"><label for= "placeTime">이용시간</label><input type="text" id="placeTime" name="pTime" maxlength="50" size="4" value='<%=dto.getPlace().get("oprtime")%>'></li>
            <li class="placeTel"><label for= "placeTel">문의 및 안내</label><input type="text" id="placeTel" name="pTel" maxlength="15" size="10" value='<%=dto.getPlace().get("tel")%>'></li>
                <li class="line3"><label for="placeAddress">주소 *</label><input type="text" id="placeAddress" name="pAddress" size="10" maxlength="40" required value='<%=dto.getAddress()%>'>
        </ul>
        </div>
        <!-- 필터 시작 -->
        <div class="chkInfo">
        <h3>어떤 여행지인지, 어떤 사람에게 추천하는지 <b>체크</b>해보세요. </h3>
        <jsp:include page="filter_editing.jsp"/>
        </div>
        <!-- 여행지 설명란 및 등록버튼 -->
        <textarea id="pInfo" name="pInfo" cols="115" rows="5" placeholder="여행지를 자세히 소개하세요 (선택사항)" maxlength="400"><%=dto.getPinfo()%></textarea>
        <button type="submit" class="btn submitBtn" onclick="return checkImgChange()" name="수정"> 수정완료 </button>
    </form>
    	<script type="text/javascript">
    	var str = document.getElementById("pInfo").value;
    	str = str.replaceAll(/<br>/g,'\r\n');
    	document.getElementById("pInfo").value = str;
    		
    	</script>
    </article>
    </main>
    <jsp:include page="footer.jsp"/>
</body>
</html>