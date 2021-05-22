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
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>
	<jsp:include page="header2.jsp"/>
    <main>
    <article class="write">
        <h2 class="tit_write"> 혼자 알던 <b>여행지</b>를 공유해요</h2>
    <hr class="hr_write_title">
    <form name="writing_form" id="w_form" action="place_writing_process.jsp" method="post" enctype="multipart/form-data">
        <div class="img_preview">
            <span id="preview" style="background-image:url('')"></span>
            <input type="file" class="hiddenBtn" name="pImage" accept="image/*"
            	id="bizFile"   style="apperance: none; -webkit-apperance: none;">
            <label for="bizFile" class="fileBtn"> 이미지 첨부 </label>
            <!-- <span id="fileName">선택된 파일없음</span> -->
        </div>
        <div class="basicInfo">
        <ul>
            <li><label for="placeName">장소명*</label><input type="text" id="placeName" name="pName" size="5" autofocus required maxlength="15"></li>
            <li><label for="placeSubtitle">부제*</label><input type="text" id="placeSubtitle" name="pSubtitle" size="10" required maxlength="20"></li>
            <li><label for="placeCategory">장소분류*</label><select id="placeCategory" name="pCategory" required>
                <option value="store"> 상점 </option>
                <option value="shopping"> 쇼핑몰 </option>
                <option value="culture"> 문화시설 </option>
                <option value="history"> 역사 </option>
                <option value="park"> 공원 </option>
                <option value="leports"> 레포츠 </option>
                <option value="restaurant"> 음식점 </option>
                <option value="cafe"> 카페 </option>
                <option value="activity"> 이색체험공간 </option>
                <option value="etc"> 기타 </option>
            </select></li>            
            <li><label for="placeFee">이용요금</label><input type="text" id="placeFee" name="pFee" size="10" maxlength="50"></li>
            <li class="placeAccess"><label for="placeOff">휴무일 *</label><input type="text" id="placeOff" name="pOff" placeholder="월,수" size="4"></li>
            <li class="placeAccess"><label for= "placeTime">이용시간</label><input type="text" id="placeTime" name="pTime" placeholder="12:00~19:00" maxlength="30" size="4"></li>
                <li class="line3"><label for="placeAddress">주소 *</label><input type="text" id="placeAddress" name="pAddress" size="10" maxlength="40" required>
                <button type="button" class="btn addSchBtn" onclick="findAddress.kakaoShow();">주소 검색</button></li>
        </ul>
        </div>
        <!-- 필터 시작 -->
        <div class="chkInfo">
        <h3>어떤 여행지인지, 어떤 사람에게 추천하는지 <b>체크</b>해보세요. </h3>
        <jsp:include page="filter_writing.jsp"/>
        </div>
        <!-- 여행지 설명란 및 등록버튼 -->
        <textarea name="pInfo" cols="115" rows="5" placeholder="여행지를 자세히 소개하세요 (선택사항)" maxlength="400"></textarea>
        <button type="submit" class="btn submitBtn" name="등록"> 등록 </button>
    </form>
    </article>
    </main>
    <jsp:include page="footer.jsp"/>
</body>
</html>