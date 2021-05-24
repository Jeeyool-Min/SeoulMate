<%@page import="jdk.internal.misc.FileSystemOption"%>
<%@page import="seoulMate.dto.PlaceDetailDTO"%>
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
	<jsp:useBean id="pDao3" class="seoulMate.dao.PlaceDetailDAO" scope="session"/>
    <main class="main2">
   	 <%
   	String pno = request.getParameter("pno");
   	 System.out.print(pno==null);
    PlaceDetailDTO dto = pDao3.loadPost(pno);
		%>
			
        <div class="titleType1">
            <h1 id="topTitle"><%=dto.getPname() %></h1>
            <div class="area_address" id="topAddr"><span><%=dto.getGu() %></span></div>
            <br>
            <div class="area_subtitle"><%=dto.getPtitle() %></div>
            <br><br>
            <div class="post_area">
                <div class="wrap_post_button">
                <button type="button" class="btn_like" onclick="setLike();"><span class="ico">좋아요</span><span
                        class="num" id="conLike"> <%=dto.getPlikeno() %> </span></button>
                <button type="button" class="btn_sharing" onclick="openShare();"><span class="ico">공유하기</span><span
                        class="num" id="conShare"> 0 </span></button>
                        <button type="button" class="btn_bookmark" onclick="setFavoContentDetail();"><span
                                class="ico">즐겨찾기</span></button>
                <span class="num_view"><em class="tit"> 조회수 </em><span class="num" id="conRead"><%=dto.getPview() %></span></span>
                    <!-- <button type="button" class="btn_cos" onclick="myCourseCartDetail('C','12','');"><span
                            class="ico">코스 담기</span></button> -->
                </div>
            </div>
            <div class="detail_tab">
                <ul>
                    <li><a href="javascript:tapChange('galleryGo')";><span> 사진보기 </span></a></li>
                    <li><a href="javascript:tapChange('detailGo')";><span> 상세정보 </span></a></li>
                    <li><a href="javascript:tapChange('replyGo')";><span> 댓글 </span></a></li>
                    <li><a href="javascript:tapChange('courseGo')";><span> 추천코스 </span></a></li>
                </ul>
            </div>
            <br>
        </div>
        <br>
        <ul class="slides">
            <input type="radio" name="radio-btn" id="img-1" checked />
            <li class="slide-container">
                <div class="slide">
                    <img class="place_img" src="upload/<%=dto.getImgUrl()%>"/>
                </div>
                <div class="nav">
                    <label for="img-3" class="prev">&#x2039;</label>
                    <label for="img-2" class="next">&#x203a;</label>
                </div>
            </li>

            <input type="radio" name="radio-btn" id="img-2" />
            <li class="slide-container">
                <div class="slide">
                    <img class="place_img" src="resources/image/theHyundai.jpg"/>
                </div>
                <div class="nav">
                    <label for="img-1" class="prev">&#x2039;</label>
                    <label for="img-3" class="next">&#x203a;</label>
                </div>
            </li>

            <input type="radio" name="radio-btn" id="img-3" />
            <li class="slide-container">
                <div class="slide">
                    <img class="place_img" src="resources/image/hyundai1.jpg"/>
                </div>
                <div class="nav">
                    <label for="img-2" class="prev">&#x2039;</label>
                    <label for="img-1" class="next">&#x203a;</label>
                </div>
            </li>

            <li class="nav-dots">
                <label for="img-1" class="nav-dot" id="img-dot-1"></label>
                <label for="img-2" class="nav-dot" id="img-dot-2"></label>
                <label for="img-3" class="nav-dot" id="img-dot-3"></label>
            </li>
        </ul>
        <br>
        <div class="wrap_contView">
            <div class="title_contView">
            <h3>상세정보</h3>
            <button class="btn_modify" onclick="goJikimi();"><span><i class="fas fa-edit" aria-hidden="true"></i>정보수정요청</span></button>
            </div>
            <hr class="hr_detail" width="100%" size="3">
            <div class="area_txtView top ms_detail" style="padding-bottom: 50px;">
                <div class="inr_wrap">
                    <div class="inr">
                        <p><%=dto.getPinfo() %></p>
                    </div>
                </div>
            </div>

            <div class="mapView">
                <iframe
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3164.2925471921517!2d126.92321921526866!3d37.524600579805806!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357c9fa9acf4bb85%3A0x94ff30722a96cbde!2z642UIO2YhOuMgCDshJzsmrg!5e0!3m2!1sko!2skr!4v1620116026562!5m2!1sko!2skr"
                    width="700" height="350" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
            </div>

            <div class="wrap_contView" id="detailinfoview">
                <!-- 세부 정보 -->
                <div class="area_txtView_bottom" style="padding-bottom: 50px;">
                    <div class="inr_wrap" style="overflow: hidden; height: 125px;">
                        <div class="inr">
                            <ul>
                                <li><strong>장소분류  </strong><span> <%=dto.getCategory() %></span></li>
                                <li><strong>문의 및 안내  </strong><span><%=dto.getTel() %></span></li>
                                <li><strong>주소  </strong><span> <%=dto.getAddress() %></span></li>
                                <li><strong>이용시간  </strong><span><%=dto.getOprtime() %><br></span></li>
                                <li><strong>휴무일  </strong><span><%=dto.getOffdays() %></span></li>
                                <li><strong>이용요금  </strong><span> <%=dto.getFee() %></span></li>
                            </ul>
                        </div>
                    </div>
                <div class="cont_more">
                    <button type="button" class="btn_more" title="더보기"><span> 더보기 </span>
                </div>
            </div>
            <div id="replyGo">
                <div class="replyWrap">
                    <h3 class="tit_reply">여행톡</h3>
                    <div class="write">
                        <div class="form">
                            <form name="tform" id="tform">
                                <span class="writeForm"><textarea name="comment" rows="3px" cols="125px" id="comment"
                                        placeholder="로그인 후 소중한 댓글을 남겨주세요." cols=""
                                        readonly="readonly"></textarea>
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
            <br><br>
            <div id="courseGo">
                <div class="relation_course">
                    <h3 class="tit_course">연관 코스</h3>
                    <hr class="hr_detail" width="100%" size="3">
                    <strong class="stit">더현대서울<span>, 뭐가 다를까?</span></strong>
                    <div class="cos_wrap">
                        <div class="title">
                            <em>4코스</em><strong><a href='#'>MZ세대의 취향저격 공간 탐방기</a></strong>
                            <div><span>서울 영등포구</span><span class="distance">코스 총 거리 : 2.5km</span></div>                           </div>
                        <div class="swiper-container">
                            <ul class="swiper-wrapper">
                                <li class="swiper-slide">
                                    <em>1</em><a href='#'>
                                    <span class="img" style="background-image:url('http://the-edit.co.kr/wp-content/uploads/2021/03/1400_retouched_-1010176.jpg')"></span>
                                    <div class="tit"><span>B2 사무엘스몰즈</span></div></a></li>
                                <li class="swiper-slide"><em>2</em><a href='#'><span class="img" style="background-image:url('http://the-edit.co.kr/wp-content/uploads/2021/03/1400_retouched_-1010200.jpg')"></span><div class="tit"><span>B2 스틸북스</span></div></a></li>
                                <li class="swiper-slide"><em>3</em><a href='#'><span class="img" style="background-image:url('https://t1.daumcdn.net/cfile/tistory/99E145465F28EE0F32')"></span><div class="tit"><span>F4 아키타입 & 챕터원</span></div></a></li>
                                <li class="swiper-slide"><em>4</em><a href='#'><span class="img" style="background-image:url('http://the-edit.co.kr/wp-content/uploads/2021/03/1400_retouched_-1010134.jpg')"></span><div class="tit"><span>F5 사운즈 포레스트</span></div></a></li>
                            </ul>
                            <div class="siwper-button-next"></div>
                            <div class="siwper-button-prev"></div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <br><br><br>
            <hr class="hr_detail" width="100%" size="3">
        </main>
<jsp:include page="footer.jsp"/>

</body>

</html>