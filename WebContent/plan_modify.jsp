<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title> 일정계획 | 쓰기(수정)모드 </title>
    <link rel="stylesheet" href="resources/css/mypage/plan-detail.css" />
    <link rel="stylesheet" href="resources/css/mypage/plan-modify.css" />
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common.css'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
  </head>
<body>
	<jsp:include page="header2.jsp"/>
    <main class="main2">
     <div class="plan-content">
        <div class="plan-title">
          <p title="더블클릭시 제목 수정">맛집투어 [21.04.20] with 김지윤</p><button class="btn modifyBtn" type="button">수정완료</button>
        </div>
        <div class="plan-diary">
          <div class="plan-diary__days"><p>Day1</p></div>
          <div class="plan-diary__images first">
            <div class="plan-diary__image">
              <img src="resources/image/한강.jpg" width="100px" height="100px" />
              <p>한강</p>
            </div>
            <div class="plan-diary__memo plan-diary__memo-up">
              <p>배고플 때까지 걸어다니기</p>
            </div>
          </div>
          <div class="plan-diary__images">
            <div class="plan-diary__image">
              <img src="resources/image/음식1.jpg" width="100px" height="100px" />
              <p>팟타이</p>
            </div>
            <div class="plan-diary__memo plan-diary__memo-down">
              <p>
                12:30쯤에 밥 먹으러 출발~~~ <br />
                1시간 정도 먹은 후 카페 ㅎㅎㅎ
              </p>
            </div>
          </div>
          <div class="plan-diary__images">
            <div class="plan-diary__image">
              <img src="resources/image/카페1.jpg" width="100px" height="100px" />
              <p>후식</p>
            </div>
            <div class="plan-diary__memo plan-diary__memo-up">
              <p>뭐 시킬지 고민~~</p>
            </div>
          </div>
          <div class="plan-diary__images">
            <div class="plan-diary__image">
              <img src="resources/image/한강.jpg" width="100px" height="100px" />
              <p>한강</p>
            </div>
            <div class="plan-diary__memo plan-diary__memo-down">
              <p>걸어다니기</p>
            </div>
          </div>
        </div>
    </div>
     <jsp:include page="mypage_sidebutton2.jsp"/>
    </main>
   	<%@ include file="footer.jsp" %>
  </body>
</html>
