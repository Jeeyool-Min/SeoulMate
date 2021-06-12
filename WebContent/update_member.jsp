<%@page import="seoulMate.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_header.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/membership/join.css'>
    <script src="resources/js/jquery-3.6.0.min.js"></script>
    
    <title>개인정보수정</title>
</head>
<jsp:useBean id="dao2" class="seoulMate.dao.MemberDAO" scope="session"/>
<%
	MemberDTO dto = dao2.selectAll(session.getAttribute("nickname").toString());
	String year = "19" + dto.getBirthdate().getYear();
	int month =dto.getBirthdate().getMonth()+1;
	int day = dto.getBirthdate().getDate();
%>
<body>
	<%@ include file="header1.jsp" %>
	<main class="jmain" style="height:720px;">
    <section id="section">
        <form name="joinForm" action="member_update_process.jsp" method="post">
        <div class="wrapper">
        <h1 class="all title">개인정보수정</h1>
        <div class="id all">
            <p class="p-all">아이디</p>
            <input type="text" class="input-all" name="userId" id="userId" required autofocus readonly value="<%=dto.getUserId()%>">
        </div>
        <div class="password all">
            <p class="p-all">비밀번호</p>
            <input type="password" class="input-all" name="password" id="password" required>
        </div>
        <div class="password all">
            <p class="p-all">비밀번호 확인</p>
            <input type="password" class="input-all" name="passwordChk" id="passwordChk" required>
        </div>
        <div class="name all">
            <p class="p-all">이름</p>
            <input type="text" class="input-all" name="name" id="name" required value="<%=dto.getName()%>" readonly>
        </div>
        <div class="email all">
            <p class="p-all">이메일</p>
            <input type="email" class="input-all" name="eamil" id="eamil" value="<%=dto.getEmail()%>">
        </div>
        <div class="email all">
            <p class="p-all">닉네임</p>
            <input type="text" class="input-all" name="nickname" id="nickname" required value="<%=dto.getNickname()%>">
        </div>
        <div class="birth all">
            <p class="p-all">생년월일</p>
            <input class="year" type="text" placeholder="년(4자)" name="year" id="year" required value="<%=year%>">
            <input type="hidden" id="bMonth" value="<%=month%>">
            <select name="month" id="month">
                <option value="">월</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <script type="text/javascript">
            var m = document.getElementById('bMonth').value;
            var sel = document.getElementById("month"); 
            	for(var i=0; i<sel.length; i++){
            		if(sel[i].value==m){ 
            			sel[i].selected = true; 
            			}
            		}
            </script>

            <input class="date" type="text" name="day" id="day" placeholder="일" value="<%=day%>">
        </div>
        <div class="btn all">
            <a href="./Complete.html">
                <button type="submit" onclick="">수정완료</button>
            </a>
        </div>
        </div>
        </form>
    </section>
    </main>
    	<%@ include file="footer2.jsp" %>
</body>

<script>
    let a = document.getElementById('section');
    function disappear() {
        a.style.display = "none";
    }
</script>

</html>