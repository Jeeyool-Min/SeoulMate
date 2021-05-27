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
    <script type="text/javascript">
    function findAddress(){
    	gu = window.open("searchAddress.jsp", "search_keyword","width=940, height=520, resizable, status=1");
    }
    function checkImg(){
    	var imgname = document.getElementById('imgInput').value;
    	if(imgname==""){
    		alert("이미지를 첨부해주세요.");
    		return false;
    	}
    	return true;
    }
    </script>
	<jsp:include page="header2.jsp"/>
    <main>
    <article class="write">
        <h2 class="tit_write"> 혼자 알던 <b>여행지</b>를 공유해요</h2>
    <hr class="hr_write_title">
    <form name="writing_form" id="w_form" action="place_writing_process.jsp" method="post" enctype="multipart/form-data">
     <div class="first_line first">
     <label for="placeName">장소명*</label><input type="text" id="placeName" name="pName" size="5" autofocus required maxlength="15">
     <button type="button" class="btn addSchBtn" onclick="findAddress()">장소 검색</button>
     </div>
     <div class="first_line second">
     <label for="placeSubtitle">부제*</label><input type="text" id="placeSubtitle" name="pSubtitle" required maxlength="30">
     </div>
        <div class="img_preview">
            <img id="preview" src="resources/image/d9d9d9&text=preview.png" alt="image">
            <input type="file" class="hiddenBtn" name="pImage" accept="image/*"
            	id="imgInput"  style="apperance: none; -webkit-apperance: none;">
            <label for="imgInput" class="fileBtn"> 이미지 첨부 </label>
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
            <li><label for="placeFee">이용요금</label><input type="text" id="placeFee" name="pFee" size="10" maxlength="50"></li>
            <li class="placeAccess"><label for="placeOff">휴무일 *</label><input type="text" id="placeOff" name="pOff" size="4" required></li>
            <li class="placeAccess"><label for= "placeTime">이용시간</label><input type="text" id="placeTime" name="pTime" maxlength="50" size="4"></li>
            <li class="placeTel"><label for= "placeTel">문의 및 안내</label><input type="text" id="placeTel" name="pTel" maxlength="15" size="10"></li>
                <li class="line3"><label for="placeAddress">주소 *</label><input type="text" id="placeAddress" name="pAddress" size="10" maxlength="40" required>
        </ul>
        </div>
        <!-- 필터 시작 -->
        <div class="chkInfo">
        <h3>어떤 여행지인지, 어떤 사람에게 추천하는지 <b>체크</b>해보세요. </h3>
        <jsp:include page="filter_writing.jsp"/>
        </div>
        <!-- 여행지 설명란 및 등록버튼 -->
        <textarea name="pInfo" cols="115" rows="5" placeholder="여행지를 자세히 소개하세요 (선택사항)" maxlength="400"></textarea>
        <button type="submit" onclick="return checkImg()" class="btn submitBtn" name="등록"> 등록 </button>
    </form>
    </article>
    </main>
    <jsp:include page="footer.jsp"/>
</body>
</html>