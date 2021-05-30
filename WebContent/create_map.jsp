<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>지도 생성하기</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff320fced3e6ec842c7367acc991287d"></script>    
</head>
<body>
<!-- 지도를 표시할 div 입니다 -->
<div id="map" style="width:500px;height:400px;"></div>

<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };
    


// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 
</script>
</body>
<jsp:useBean id="pDao4" class="seoulMate.dao.PlaceUpdateDAO" scope="session"/>
<%
pDao4.loadInfo(39);
String a = null;
out.println(a);
%>
</html>