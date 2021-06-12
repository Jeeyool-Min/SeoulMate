<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title> 서울여행 길잡이 SeoulMate </title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/mypage/plan-add.css'>    
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common.css'>
    <script src='main.js'></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
	<jsp:include page="header2.jsp"/>
    <main class="main2">
        <div class="add-form">
        <form>
          <div class="add-information">
            <div class="line1">
            <input class="information information-date" type="date" />
            <span>-</span>
            <input class="information" type="date" />
            <!-- <input type="checkbox" />
            <span>당일</span> -->
            </div>
            <div class="line2">
            <input class="information" type="text" placeholder="제목" size="35" />
            <input class="tripWith" type="text" placeholder="여행메이트">
            </div>
          </div>
          <div class="add-button">
            <input class="button" type="button" value="저장" />
            <input class="button" type="reset" value="초기화" />
          </div>
          <div class="add-detail">
            <span><strong>세부정보</strong></span>
            <div class="add-detail__diary">
                <div class="add-diary__days"><p>Day1</p></div>
                <div class="add-diary__circle">
                         <div class="circle"></div>
                         <p>+</p>
                        </div>
                        <div class="add-diary__circle">
                        <div class="circle"></div>
                        <p>+</p>
                   </div>
                   <div class="add-diary__circle">
                    <div class="circle"></div>
                    <p>+</p>
                  </div>
                  <div class="add-diary__circle">
                    <div class="circle"></div>
                    <p>+</p>
                  </div>
            </div>
          </form>
        </div>
     <jsp:include page="mypage_sidebutton2.jsp"/>
    </main>
   	<%@ include file="footer.jsp" %>
  </body>
</html>