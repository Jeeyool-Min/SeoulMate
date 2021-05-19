<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>지역선택 팝업</title>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/popup_site.css'>
    <script src='main.js'></script>
</head>
<body>
<div id="districtWrap" class="wrap_pop" style="display:block; position:absolute;">
    <div id="districtPop" class="pop" style="position:absolute; left:50%; top:100.286px;">
        <div class="tit"> <h2> <b> 여행지 </b> 선택</h2>
            <button type="button" class="btn_close1" onclick="close()";> 닫기 </button></div>
           <!--<button type="button" class="btn_close1" onclick="hidePopup('spArea')";> 닫기 </button></div>-->
        <div id="districtBox" class="choice">
            <ul>
            <!-- <li class="top_move" style="user-select: auto;"><button type="button" class="c_btn" onclick="moveUp('11680')" style="user-select: auto;">서울 전체</button></li> -->
            <li id="All"><button id="btn_all" onclick="setGuNm('0')" type="button" value="전체" class="c_btn">전체</button></li>
            <li id="gu1"><button id="btn_gu1" onclick="setGuNm('1')" type="button" value="강남구" class="c_btn">강남구</button></li>
            <li id="gu2"><button id="btn_gu2" onclick="setGuNm('2')" type="button" value="강동구" class="c_btn">강동구</button></li>
            <li id="gu3"><button id="btn_gu3" onclick="setGuNm('3')" type="button" value="강북구" class="c_btn">강북구</button></li>
            <li id="gu4"><button id="btn_gu4" onclick="setGuNm('4')" type="button" value="강서구" class="c_btn">강서구</button></li>
            <li id="gu5"><button id="btn_gu5" onclick="setGuNm('5')" type="button" value="관악구" class="c_btn">관악구</button></li>
            <li id="gu6"><button id="btn_gu6" onclick="setGuNm('6')" type="button" value="광진구" class="c_btn">광진구</button></li>
            <li id="gu7"><button id="btn_gu7" onclick="setGuNm('7')" type="button" value="구로구" class="c_btn">구로구</button></li>
            <li id="gu8"><button id="btn_gu8" onclick="setGuNm('8')" type="button" value="금천구" class="c_btn">금천구</button></li>
            <li id="gu9"><button id="btn_gu9" onclick="setGuNm('9')" type="button" value="노원구" class="c_btn">노원구</button></li>
            <li id="gu10"><button id="btn_gu10" onclick="setGuNm('10')" type="button" value="도봉구" class="c_btn">도봉구</button></li>
            <li id="gu11"><button id="btn_gu11" onclick="setGuNm('11')" type="button" value="동대문구" class="c_btn">동대문구</button></li>
            <li id="gu12"><button id="btn_gu12" onclick="setGuNm('12')" type="button" value="동작구" class="c_btn">동작구</button></li>
            <li id="gu13"><button id="btn_gu13" onclick="setGuNm('13')" type="button" value="마포구" class="c_btn">마포구</button></li>
            <li id="gu14"><button id="btn_gu14" onclick="setGuNm('14')" type="button" value="서대문구" class="c_btn">서대문구</button></li>
            <li id="gu15"><button id="btn_gu15" onclick="setGuNm('15')" type="button" value="서초구" class="c_btn">서초구</button></li>
            <li id="gu16"><button id="btn_gu16" onclick="setGuNm('16')" type="button" value="성동구" class="c_btn">성동구</button></li>
            <li id="gu17"><button id="btn_gu17" onclick="setGuNm('17')" type="button" value="성북구" class="c_btn">성북구</button></li>
            <li id="gu18"><button id="btn_gu18" onclick="setGuNm('18')" type="button" value="송파구" class="c_btn">송파구</button></li>
            <li id="gu19"><button id="btn_gu19" onclick="setGuNm('19')" type="button" value="양천구" class="c_btn">양천구</button></li>
            <li id="gu20"><button id="btn_gu20" onclick="setGuNm('20')" type="button" value="영등포구" class="c_btn">영등포구</button></li>
            <li id="gu21"><button id="btn_gu21" onclick="setGuNm('21')" type="button" value="용산구" class="c_btn">용산구</button></li>
            <li id="gu22"><button id="btn_gu22" onclick="setGuNm('22')" type="button" value="은평구" class="c_btn">은평구</button></li>
            <li id="gu23"><button id="btn_gu23" onclick="setGuNm('23')" type="button" value="종로구" class="c_btn">종로구</button></li>
            <li id="gu24"><button id="btn_gu24" onclick="setGuNm('24')" type="button" value="중구" class="c_btn">중구</button></li>
            <li id="gu25"><button id="btn_gu25" onclick="setGuNm('25')" type="button" value="중랑구" class="c_btn">중랑구</button></li></ul>
	</div>
</div>
</div>
</body>
</html>