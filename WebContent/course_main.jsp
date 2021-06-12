<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> HOME </title>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/main/main_content.css'>
</head>
<body>
	<%@ include file="header1.jsp" %>
<main>
    <div class="main_title">
    <h1> 추천여행코스 </h1>
    </div>
    <hr class="hr_title">
    <section id="content">
		<article>
			<h2>서울의 핫플레이스</h2>
			<div class="preview">
				<span class="thumbnail_txt"> 서울 압구정 핫플레이스 투어 </span>
				<div class="thumbnail_img"
					style="background: url(https://th.bing.com/th/id/R4e70100e5871736071b9788927d373e7?rik=hBMLBRma6cSmUg&amp;riu=http%3a%2f%2fblogthumb2.naver.net%2f20160221_144%2fanniekm9_14560638325109Xr5I_JPEG%2f20160218_130943.jpg%3ftype%3dw2&amp;ehk=OmDOp8cYXXtIbq%2bmcmHywHtoFbnLnHWXOYVw%2b9wP%2fkM%3d&amp;risl=&amp;pid=ImgRaw) 50% 50%/cover no-repeat;">
					<a class="hover_place" href="course_detail.jsp"></a>
				</div>
				<ul class="ul_course">
					<span class="vertical_line"></span>
					<li class="li_course">도산공원 거리</li>
					<li class="li_course">신사동 가로수길</li>
					<li class="li_course">한강공원 잠원지구</li>
					<li class="li_course"></li>
					<small>총 거리:3.4km</small>
				</ul>
			</div>
			<div class="preview">
				<span class="thumbnail_txt"> 고즈넉함 따라 걷는 부암동 문화산책 </span>
				<div class="thumbnail_img"
					style="background-image: url('https://d2uh4olaxaj5eq.cloudfront.net/fit-in/0x1018/b4d64f7a-3ed7-4160-a162-8b2839b76d68.jpg')"></div>
				<ul class="ul_course">
					<span class="vertical_line"></span>
					<li class="li_course">윤동주 시인의 언덕 <br>
					</li>
					<li class="li_course">청운공원 <br>
					</li>
					<li class="li_course">창의문 <br>
					</li>
					<li class="li_course">계열사 <br>
					</li>
					<small>총 거리:2.4km</small>
				</ul>
			</div>
			<div class="preview">
				<span class="thumbnail_txt"> 걷기좋은 서울의 골목, 서촌으로 </span>
				<div class="thumbnail_img"
					style="background-image: url('resources/image/timothy-ries-Wko1Ld0-z9U-unsplash.jpg')"></div>
				<ul class="ul_course">
					<span class="vertical_line"></span>
					<li class="li_course">경복궁역 2번출구<br>
					</li>
					<li class="li_course">이상의집<br>
					</li>
					<li class="li_course">대오서점<br>
					</li>
					<li class="li_course">박노수가옥<br>
					</li>
					<small>총 거리:2.4km</small>
				</ul>
			</div>
		</article>
		<%@ include file="main_course.jsp" %>
	</section>
	    <ul class="side-bar long">
        <li><a href="places_writing.jsp"> 나만의 코스 공유하기</a></li>
        </ul>
</main>
	<%@ include file="footer.jsp" %>
</body>
</html>