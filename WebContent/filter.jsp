<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> filter </title>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_filter.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/popup_site.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_buttonChkbox.css'>
<script type="" src="resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function chkReset(){$('.reset1').prop('checked', false);}
</script>
</head>

<body class="popup_body">
	<div class="filterOutWrap">
    <form name="schInfo" id="schInfo" style="display: block;" method="POST">
        <h2 class="tit_sch"> <b>맞춤형</b> 여행정보 찾기</h2>
        <input type="checkbox" class="unfoldBtn" id="unfoldBtn"><label for="unfoldBtn" class="labelBtn" onclick=""><img src="resources/image/unfold.png" alt=""></label>
        <div class ="filterWrap">
            <ul class="filter_ul">
                <li><span class="title"><label for="destination"> 지역 </label></span>
                    <div class="tripSite">
                    <input type="text" id="destination" placeholder="여행지 선택" readonly="">
                    <!--onclick은 자바스크립트-->
                    <button type="button" id="dstBtn" class="btn schBtn" onclick="popup()"><span class="web_only">여행지 </span>선택</button></div>
                </li>
                <li class="line2"><span class="title"><label> 여행목적 </label></span>
                        <div class="tripStyle"> <!--div: 모든 체크박스 묶음 | span : 하나의 체크박스와 목록명 묶음 | input : 체크박스 label:목록명-->
                            <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk01" value="1"><label for="chk01"> 자연/경치 </label></span>
                            <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk02" value="2"><label for="chk02"> 휴식/힐링 </label></span>
                            <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk03" value="3"><label for="chk03"> 드라이브 </label></span>
                            <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk04" value="4"><label for="chk04"> 야경 </label></span>
                            <br>
                            <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk05" value="5"><label for="chk05"> 미식 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk06" value="6"><label for="chk06"> 핫플레이스 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk07" value="7"><label for="chk07"> 체험/학습 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripStyle" id="chk08" value="8"><label for="chk08"> 역사/문화 </label></span>
                    </div>
                </li>
                <li><span class="title"><label for="tripDays"> 여행기간 </label></span>
                    <div class="tripDays"> 
                        <span class="set"><input type="checkbox" class="reset1" name="tripDays" id="chk09" value="9"><label for="chk09"> 당일치기 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripDays" id="chk10" value="10"><label for="chk10"> 1박2일 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripDays" id="chk11" value="11"><label for="chk11"> 2박3일 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripDays" id="chk12" value="12"><label for="chk12"> 3박이상 </label></span>
                    </div>
                </li>
                <li><span class="title"><label for="tripWith"> 동반인원 </label></span>
                    <div class="tripWith"> <!--div: 모든 체크박스 묶음 | span : 하나의 체크박스와 목록명 묶음 | input : 체크박스 label:목록명-->
                        <span class="set"><input type="checkbox" class="reset1" name="tripWith" id="chk14" value="14"><label for="chk14"> 가족 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripWith" id="chk15" value="15"><label for="chk15"> 친구 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripWith" id="chk16" value="16"><label for="chk16"> 연인 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripWith" id="chk17" value="17"><label for="chk17"> 혼자 </label></span><br>
                    </div>
                </li>
                <li><span class="title"><label for="tripAccess"> 이동수단 </label></span>
                    <div class="tripAccess"> <!--div: 모든 체크박스 묶음 | span : 하나의 체크박스와 목록명 묶음 | input : 체크박스 label:목록명-->
                        <span class="set"><input type="checkbox" class="reset1" name="tripAccess" id="chk18" value="18"><label for="chk18"> 대중교통 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripAccess" id="chk19" value="19"><label for="chk19"> 택시 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripAccess" id="chk20" value="20"><label for="chk20"> 자동차 </label></span>
                        <span class="set"><input type="checkbox" class="reset1" name="tripAccess" id="chk21" value="21"><label for="chk21"> 자전거 </label></span><br>
                    </div>
                </li>
                
                <li><span class="title"><label for="tripKeyword"> 추가 키워드 </label></span>
                    <div class="tripKeyword">
                        <input type="text" id="tripKeyword" placeholder="기타 키워드 입력(선택사항)"/>
                        <button class="btn resetBtn" id="resetBtn1" type="button" onclick="chkReset()"> 선택모두해제 </button>
                        <input type="submit" class="btn submitBtn" value="검색"></div>
                </li>
            </ul>
        </div>
    </form>
    </div>
</body>
</html>