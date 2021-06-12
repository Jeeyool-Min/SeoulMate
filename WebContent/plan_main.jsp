<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title> 마이페이지(일정계획) 메인 </title>
<link rel="stylesheet" href='resources/css/common/common_sidebutton.css'>
<link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_header.css'>
<link rel="stylesheet" type='text/css' media='screen' href='resources/css/mypage/mypage-main.css'>

<script>
      function showConfirm() {
        if (confirm("삭제하시겠습니까?")) {
          alert("삭제 되었습니다.");
        } else {
          alert("취소 되었습니다.");
        }
      }
</script>
</head>
<body>
<div class ="whole_wrapper">
	<%@ include file="header1.jsp" %>
<main class="main_content">
<table class="plan-table">
        <thead>
          <tr class="tr-border">
            <th class="first-column">No</th>
            <th class="second-column">제목</th>
            <th class="third-column">여행기간</th>
            <th class="fourth-column">여행메이트</th>
            <th class="fifth-cloumn">옵션</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="first-column">1</td>
            <td class="second-column">
              <a class="table-title" href="mypage/mypage-plan/plan1.html"
                >맛집투어</a
              >
            </td>
            <td class="third-column">21.04.20</td>
            <td class="fourth-column">김지윤</td>
            <td class="fifth-cloumn">
              <button type="button">공유</button>
              <button
                type="button" onclick="location.href='plan_modify.jsp'"
              >
                수정
              </button>
              <button type="button" onclick="showConfirm()">삭제</button>
            </td>
          </tr>
          <tr class="even">
            <td class="first-column">2</td>
            <td class="second-column">
              <a class="table-title" href="plan_detail.jsp"
                >원지 서울투어 시켜 주기</a
              >
            </td>
            <td class="third-column">21.05.01~21.05.04</td>
            <td class="fourth-column">최지원,김혜리</td>
            <td class="fifth-cloumn">
              <button type="button">공유</button>
              <button type="button">수정</button>
              <button type="button" onclick="showConfirm()">삭제</button>
            </td>
          </tr>
          <tr>
            <td class="first-column">3</td>
            <td class="second-column">
              <a class="table-title" href="#">서촌 투어</a>
            </td>
            <td class="third-column">21.05.27</td>
            <td class="fourth-column">선재원,김혜리</td>
            <td class="fifth-cloumn">
              <button type="button">공유</button>
              <button type="button">수정</button>
              <button type="button" onclick="showConfirm()">삭제</button>
            </td>
          </tr>
          <tr class="even">
            <td class="first-column">4</td>
            <td class="second-column">
              <a class="table-title" href="#">혼자 노는 날</a>
            </td>
            <td class="third-column">21.07.15</td>
            <td class="fourth-column"></td>
            <td class="fifth-cloumn">
              <button type="button">공유</button>
              <button type="button">수정</button>
              <button type="button" onclick="showConfirm()">삭제</button>
            </td>
          </tr>
          <tr>
            <td class="first-column">5</td>
            <td class="second-column">
              <a class="table-title" href="#">주제 없음</a>
            </td>
            <td class="third-column">공백</td>
            <td class="fourth-column">공백</td>
            <td class="fifth-cloumn">
              <button type="button">공유</button>
              <button type="button">수정</button>
              <button type="button" onclick="showConfirm()">삭제</button>
            </td>
          </tr>
          <tr>
            <td class="colspan-button" colspan="5">
              <button
                type="button" class="btn addBtn"
                onclick="location.href='plan_add.jsp'"
              >
                추가하기
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    <%@ include file="mypage_sidebutton.jsp"%>
    </main>
	<%@ include file="footer.jsp" %>
</div>
</body>
</html>