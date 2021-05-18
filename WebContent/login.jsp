<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/membership/styles.css">
    <title>로그인</title>
</head>

<body>
    <section id="section">
        <div id="close-div" onclick="disappear()">
            <img src="resources/image/ui-02-512.png" class="close" alt="">
        </div>
        <h1 class="all title">로그인</h1>
        <form action="login_process.jsp" method="post" name="">
        <div class="id all">
            <p>아이디</p>
            <input type="text" class="input-all" name="userId" id="userId">
        </div>
        <div class="password all">
            <p>비밀번호</p>
            <input type="password" class="input-all" name="password" id="password">
        </div>
        <div class="btn Login all">
               <button onclick="" type="submit">로그인</button>
        </div>
        </form>
        <div class="goSignup all">
            <a href="join.jsp">
                <button>회원가입</button>
            </a>
        </div>
    </section>
</body>

<script>
    let a = document.getElementById('section');
    function disappear() {
        a.style.display = "none";
    }
</script>

</html>