<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_header.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/membership/join.css'>
    
    <title>Document</title>
</head>

<body>
	<%@ include file="header1.jsp" %>
	<main class="jmain" style="height:720px;">
    <section id="section">
        <form name="joinForm" action="join_process.jsp" method="post">
        <div class="wrapper">
        <h1 class="all title">회원가입</h1>
        <div class="id all">
            <p class="p-all">아이디</p>
            <input type="text" class="input-all" name="userId" id="userId" required autofocus>
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
            <input type="text" class="input-all" name="name" id="name" required>
        </div>
        <div class="email all">
            <p class="p-all">이메일</p>
            <input type="email" class="input-all" name="eamil" id="eamil">
        </div>
        <div class="email all">
            <p class="p-all">닉네임</p>
            <input type="text" class="input-all" name="nickname" id="nickname" required>
        </div>
        <div class="birth all">
            <p class="p-all">생년월일</p>
            <input class="year" type="text" placeholder="년(4자)" name="year" id="year" required>
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
            <input class="date" type="text" name="day" id="day" placeholder="일">
        </div>
        <div class="btn all">
            <a href="./Complete.html">
                <button type="submit" onclick="">회원가입</button>
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