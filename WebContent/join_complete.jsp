<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/common/common_header.css'>
    <link rel='stylesheet' type='text/css' media='screen' href='resources/css/membership/join.css'>
    <title>회원가입 완료</title>
    
</head>
<script type="text/javascript">
	function login(){
		
    	window.open("login.jsp","login","width=700, height=520, status=1");
	}
</script>
<body>
	<%@ include file="header1.jsp" %>
	<main class="jmain" style="height:720px;">
    <section id="section">
        <div class="complete">
            <img class="check" src="resources/image/check.png" alt="">
            <h1 class="title">회원가입이 완료되었습니다</h1>
            <p>로그인하시면 더욱 다양한 서비스와 혜택을 제공받으실 수 있습니다.</p>
            <div class="btn loginBtn">
               <a id="login" class="goLogin" onclick="login()">
                    <button>로그인</button>
                </a>
            </div>
        </div>
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