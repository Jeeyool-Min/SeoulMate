<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title> 서울여행 길잡이 SeoulMate </title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/place.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/course/course_detail.css'>
    <script src='main.js'></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <script src="../js/jquery-3.6.0.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>
  <jsp:include page="header2.jsp"/>
    <main class="main2">
        <div class="titleType1">
            <div class="tit">
                <h2><em class="tit_cos">1코스</em>압구정 투어</h2>
            </div>
            <div class="area_address">
                <span class="address">서울시 강남구 압구정동</span>
                <span id="dist">코스 총거리 : 16.4km</span>
            </div>
            <div class="post_area">
                <div class="wrap_post_button">
                    <button type="button" class="btn_like" onclick="setLike();"><span class="ico">좋아요</span><span
                            class="num" id="conLike"> 0 </span></button>
                    <button type="button" class="btn_sharing" onclick="openShare();"><span class="ico">공유하기</span><span
                            class="num" id="conShare"> 0 </span></button>
                    <button type="button" class="btn_bookmark" onclick="setFavoContentDetail();"><span
                            class="ico">즐겨찾기</span></button>
                    <span class="num_view"><em class="tit"> 조회수 </em><span class="num" id="conRead">64</span></span>
                    <!-- <button type="button" class="btn_cos" onclick="myCourseCartDetail('C','12','');"><span
                            class="ico">코스 담기</span></button> -->
                </div>
            </div>
        </div>
        <div class="course_detail">
            <div class="schedule_info">
                <ul class="schedule">
                    <li> <span class="img"><img src="resources/image/schedule.gif" alt=""></span> <em>일정</em>
                        <strong>당일여행</strong>
                    </li>
                    <li> <span class="img"><img src="resources/image/theme.gif" alt=""></span> <em>테마</em>
                        <strong>친구코스</strong>
                    </li>
                </ul>
            </div>
            <div class="wrap_contView">
                <div class="area_txtView top" style="padding-bottom: 0px;">
                    <div class="inr_wrap" style="overflow: visible; height: auto;">
                        <div class="inr">
                            <p>서울 압구정 투어 당일 코스로 즐기기<br>요즘 핫한 압구정에서 즐거움을 만끽하세요 ~!</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="relation_cos">
                <div class="cos_wrap">
                    <div class="pc js_slider">
                        <div class="swiper-container">
                            <ul class="swiper-wrapper">
                                <li class="swiper-slide">
                                    <em>1</em><a href="#cosTab01">
                                        <span class="img"
                                            style="background:url(https://th.bing.com/th/id/R4e70100e5871736071b9788927d373e7?rik=hBMLBRma6cSmUg&riu=http%3a%2f%2fblogthumb2.naver.net%2f20160221_144%2fanniekm9_14560638325109Xr5I_JPEG%2f20160218_130943.jpg%3ftype%3dw2&ehk=OmDOp8cYXXtIbq%2bmcmHywHtoFbnLnHWXOYVw%2b9wP%2fkM%3d&risl=&pid=ImgRaw) 50% 50% / cover no-repeat;"></span>
                                        <div class="tit"><span>도산공원 거리</span></div>
                                    </a>
                                </li>
                                <li class="swiper-slide"><em>2</em><a href="#cosTab02"><span class="img"
                                            style="background:url(https://th.bing.com/th/id/R209e519bf9b97986d6f1038db25ec5ad?rik=EhDCt%2fDWnk3juw&riu=http%3a%2f%2fcfile23.uf.tistory.com%2fimage%2f235D53345272DAD8186CBF&ehk=pQAZ2tebgTUv%2fi7wyTootqqBeR4Q%2bIyE7TIjgvJrTzM%3d&risl=&pid=ImgRaw) 50% 50% / cover no-repeat;"></span>
                                        <div class="tit"><span>신사동 가로수길</span></div>
                                    </a></li>
                                <li class="swiper-slide"><em>3</em><a href="#cosTab03"><span class="img"
                                            style="background:url(https://i.pinimg.com/originals/52/50/de/5250de5c9cb266ae481c64acefd0f680.jpg) 50% 50% / cover no-repeat;"></span>
                                        <div class="tit"><span>한강공원 잠원지구</span></div>
                                    </a></li>
                            </ul>
                            <div class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide"
                                aria-disabled="false"></div>
                            <div class="swiper-button-prev swiper-button-disabled" tabindex="0" role="button"
                                aria-label="Previous slide" aria-disabled="true"></div>
                            <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span>
                        </div>
                    </div>
                </div>

                <!-- tab cont -->
                <div id="tabCont">
                    <div class="cos_cont active" id="cosTab01">
                        <div class="detail_box">
                            <div class="title">
                                <div class="tit_wrap"> <em>1</em> <strong><a href="#">도산공원 거리</a></strong>
                                </div> <span>서울특별시 강남구 신사동 도산대로45길 20</span><span> </span>
                            </div>
                            <div class="info_area pc">
                                <div class="wrap">
                                    <div>
                                        <a href="#"
                                            style="background:url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSQ5p_qFlaF-NH8zbAhhOpeKtNWFn0I4oufA&usqp=CAU) 50% 50% / cover no-repeat;">도산공원
                                        </a>
                                    </div>
                                    <div>
                                        <a href="#"
                                            style="background:url(https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRCMrzmKoRanBE1U0RoRUcVY0kUNaQk5zBVw&usqp=CAU) 50% 50% / cover no-repeat;">펠트
                                            도산 카페
                                        </a>
                                    </div>
                                    <div>
                                        <a href="#"
                                            style="background:url(https://emmaru.com/matzip/include/pics/2020/03/22/m_55514B154721_1.jpg) 50% 50% / cover no-repeat;">클랩
                                            피자
                                            2</a>
                                    </div>
                                </div>
                            </div>

                            <ul class="tag">
                                <li><a
                                        href="javascript:goTagList('12','핫플레이스','3f36ca4b-6f45-45cb-9042-265c96a4868c');">#핫플레이스</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','도산공원','7aee2c79-31d9-45bf-a4f3-6b439c508508');">#도산공원</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','카페','698762dd-9958-11e9-9488-02001c6b0001');">#카페</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','SNS','24e11c8c-d110-11e8-932f-02001c6b0001');">#SNS</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','펠트도산','3e04cc9c-d1cc-11e8-932f-02001c6b0001');">#펠트도산</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','클랩피자','c24d515f-3202-45e5-834e-1a091901aeff');">#클랩피자</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','맛집','1a0be97f-17fd-4469-9b37-04b3d1691912');">#맛집</a>
                                </li>
                            </ul>
                            <div class="recommend" id="n1">
                                <div class="pc">
                                    <div class="wrap"> <strong>도산공원</strong> <em>핫플레이스</em>
                                        <ul class="tag_list">
                                            <li><a href="#">목록1</a></li>
                                            <li><a href="#">목록2</a></li>
                                            <li><a href="#">목록3</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cos_cont active" id="cosTab02">
                        <div class="detail_box">
                            <div class="title">
                                <div class="tit_wrap"> <em>2</em> <strong><a
                                            href="/detail/detail_view.do?cotid=982ea676-de79-401f-b5d7-803ba6b34e55">신사동 가로수길</a></strong>
                                </div> <span>충청남도 홍성군 서부면 남당항로 689</span><span>내 위치에서 13169.1km</span>
                            </div>
                            <div class="info_area pc">
                                <div class="wrap">
                                    <div><a href="/detail/detail_view.do?cotid=982ea676-de79-401f-b5d7-803ba6b34e55"
                                            style="background:url(https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&amp;id=db9df48b-39de-4569-9020-4d451ff4017f) 50% 50% / cover no-repeat;">속동전망대
                                            0</a></div>
                                    <div><a href="/detail/detail_view.do?cotid=982ea676-de79-401f-b5d7-803ba6b34e55"
                                            style="background:url(https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&amp;id=ee40c112-524f-41db-96f1-91bf081cbe91) 50% 50% / cover no-repeat;">속동전망대
                                            1</a></div>
                                    <div><a href="/detail/detail_view.do?cotid=982ea676-de79-401f-b5d7-803ba6b34e55"
                                            style="background:url(https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&amp;id=be30018f-9ce4-4abc-bca2-aa91fc6168d8) 50% 50% / cover no-repeat;">속동전망대
                                            2</a></div>
                                </div>
                            </div>

                            <ul class="tag">
                                <li><a
                                        href="javascript:goTagList('12','핫플레이스','3f36ca4b-6f45-45cb-9042-265c96a4868c');">#핫플레이스</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','도산공원','7aee2c79-31d9-45bf-a4f3-6b439c508508');">#도산공원</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','카페','698762dd-9958-11e9-9488-02001c6b0001');">#카페</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','SNS','24e11c8c-d110-11e8-932f-02001c6b0001');">#SNS</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','펠트도산','3e04cc9c-d1cc-11e8-932f-02001c6b0001');">#펠트도산</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','클랩피자','c24d515f-3202-45e5-834e-1a091901aeff');">#클랩피자</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','맛집','1a0be97f-17fd-4469-9b37-04b3d1691912');">#맛집</a>
                                </li>
                            </ul>
                            <div class="recommend" id="n2">
                                <div class="wrap"> <strong>신사동 가로수길</strong> <em>핫플레이스</em>
                                    <ul class="tag_list">
                                        <li><a href="#">목록1</a></li>
                                        <li><a href="#">목록2</a></li>
                                        <li><a href="#">목록3</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="cos_cont active" id="cosTab03">
                        <div class="detail_box">
                            <div class="title">
                                <div class="tit_wrap"> <em>3</em> <strong><a
                                            href="/detail/detail_view.do?cotid=c81dd9ba-de78-4aaa-bcb2-9b25e9180564">한강공원 잠원지구</a></strong>
                                </div> <span>서울특별시 서초구 잠원동 잠원로 221-124</span><span></span>
                            </div>
                            <div class="info_area pc">
                                <div class="wrap">
                                    <div><a href="#"
                                            style="background:url(https://korean.visitseoul.net/comm/getImage?srvcId=MEDIA&parentSn=22655&fileTy=MEDIA&fileNo=1&thumbTy=L) 50% 50% / cover no-repeat;">한강공원
                                            0</a></div>
                                    <div><a href="#"
                                            style="background:url(data:image/jpeg;base64,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) 50% 50% / cover no-repeat;">스타벅스 서울웨이브아트센터점
                                            1</a></div>
                                    <div><a href="#"
                                            style="background:url(http://www.greenpostkorea.co.kr/news/photo/201504/46837_23443_art_1428484183.jpg) 50% 50% / cover no-repeat;">한강 유람선
                                            2</a></div>
                                </div>
                            </div>

                            <ul class="tag">
                                <li><a
                                        href="javascript:goTagList('12','핫플레이스','3f36ca4b-6f45-45cb-9042-265c96a4868c');">#핫플레이스</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','도산공원','7aee2c79-31d9-45bf-a4f3-6b439c508508');">#한강공원</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','카페','698762dd-9958-11e9-9488-02001c6b0001');">#산책</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','SNS','24e11c8c-d110-11e8-932f-02001c6b0001');">#SNS</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','펠트도산','3e04cc9c-d1cc-11e8-932f-02001c6b0001');">#자전거</a>
                                </li>
                                <li><a
                                        href="javascript:goTagList('12','클랩피자','c24d515f-3202-45e5-834e-1a091901aeff');">#한강</a>
                                </li>
                            </ul>
                            <div class="recommend" id="n3">
                                <div class="pc">
                                    <div class="wrap"> <strong>한강공원 잠원지구</strong> <em>핫플레이스</em>
                                        <ul class="tag_list">
                                            <li><a href="#">목록1</a></li>
                                            <li><a href="#">목록2</a></li>
                                            <li><a href="#">목록3</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="wrap_contView">
            <hr>
            <div class="wrap_contView">
                <div id="map" style="width:100%; height: 50vh;"></div>
                <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBVqSWvaBsKOA8jHR_ZgvN9PBYHuAj5vo&callback=initMap"
                type="text/javascript"></script>
                <script>
                    function initMap() {
                    var center = { lat: 37.5233626 ,lng: 127.0218782 }; //지도 중앙
                    var seoul = { lat: 37.5244813 ,lng: 127.033178 }; //도산공원
                    var sinsa = { lat: 37.5246257 ,lng: 127.0142177 }; //가로수길
                    var jamwon = { lat: 37.5206865 ,lng: 127.0100837 }; //한강
                    var map = new google.maps.Map(
                        document.getElementById('map'), {
                        zoom: 15,
                        center: center
                        });
                    
                        new google.maps.Marker({
                            position: seoul,
                            map: map,
                            label: "도산공원"
                        });

                        new google.maps.Marker({
                            position: sinsa,
                            map: map,
                            label: "신사동 가로수길"
                        });

                        new google.maps.Marker({
                            position: jamwon,
                            map: map,
                            label: "한강시민공원 잠원지구"
                        });
                    }
                </script>
            </div>

            <div class="wrap_contView" id="detailinfoview">
                <!-- 세부 정보 -->
                <div class="area_txtView_bottom" style="padding-bottom: 50px;">
                    <div class="inr_wrap" style="overflow: hidden; height: 125px;">
                        <div class="inr">
                            <ul>
                                <li></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="replyGo">
                <div class="replyWrap">
                    <h3 class="tit_reply">여행톡</h3>
                    <div class="write">
                        <div class="form">
                            <form name="tform" id="tform">
                                <span class="writeForm"><textarea name="comment" rows="3px" cols="125px" id="comment"
                                        placeholder="로그인 후 소중한 댓글을 남겨주세요." cols="" readonly="readonly"></textarea>
                                    <button type="submit" class="btn_apply_ContentComment" name="comment"> 등록 </button>
                                </span>
                                <!-- <a href="javascript:" class="btn_apply_ContentComment">등록</a> -->
                            </form>
                        </div>
                    </div>
                    <br>
                    <div class="wrap_reply">
                        <p class="reply_none">등록된 댓글이 없습니다.</p>
                    </div>
                </div>
            </div>
        </div>
    </main>
	<jsp:include page="footer.jsp"/>
</body>
</html>