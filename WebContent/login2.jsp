<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/membership/styles.css">
    <title>로그인</title>
<script src="resources/js/jquery-3.6.0.min.js"></script>
</head>

<body>
<%@ include file="header1.jsp" %>
<script type="text/javascript">
function checkLogin(){
	var form = document.loginForm;
	var id = form.userId;
	var password = form.password;
	//데이터 입력 유무 검사
	if(id.value=""){ alert("아이디를 입력해주세요."); id.focus(); return false;}
	else if(password.value=""){alert("비밀번호를 입력해주세요."); password.focus() return false;}
	form.submit();
	}
	
}
</script>
<jsp:useBean id="mDao" class="seoulMate.dao.MemberDAO" scope="session"/>
	<%
	String userId = "";
	String password = "";
	String nickname = mDao.login(userId, password);
	%>
    <section id="section">
        <h1 class="all title">로그인</h1>
        <form action="login_process.jsp" method="post" class="loginform" name="loginForm">
        <div class="id all">
            <p>아이디</p>
            <input type="text" class="input-all" name="userId" id="userId" required>
        </div>
        <div class="password all">
            <p>비밀번호</p>
            <input type="password" class="input-all" name="password" id="password" required>
        </div>
        <div class="btn Login all">
               <button onclick="checkLogin()" class="btn" type="submit">로그인</button>
        </div>
        </form>
        <div class="goSignup all">
            <a href="join.jsp">
                <button class="btn">회원가입</button>
            </a>
        </div>
    </section>
    <%@ include file="footer2.jsp" %>
</body>

<script>
    let a = document.getElementById('section');
    function disappear() {
        a.style.display = "none";
    }
</script>

</html>