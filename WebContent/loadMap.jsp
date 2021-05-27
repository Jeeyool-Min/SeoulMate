<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <title>주소로 장소 표시하기</title>
    
</head>
<body>
<p style="margin-top:-12px">
    <em class="link">
    <button class="btn_modify toKakao" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
    <span style="user-select: auto;"><i class="fas fa-edit" aria-hidden="true" style="user-select: auto;"></i>오류수정요청</span></button>
    </em>
</p>
<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff320fced3e6ec842c7367acc991287d&libraries=services"></script>
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<script>
//마커에 찍을 주소, 장소명 가져오기
var address = $("#placeAddress").text();
var name = $("#topTitle").text();

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch(address, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div class="pName" style="width:150px;text-align:center;padding:6px 0;">'+ name +'</div>'
        });
        infowindow.open(map, marker);
        
      	//css변경
        $('.pName').parent().parent().css('border-radius','40px');

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
        
    } 
});    
</script>
</body>
</html>