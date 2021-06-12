<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>파일 업로드</title>
</head>
<body>
<form id="go_form" name="go_form" method="post" enctype="multipart/form-data" action="upload_process.jsp">
    이름<input type="text" placeholder="이름을 입력하세요" name="user_name" maxlength="20" id="user_name" multiple="multiple">
    파일<input type="file" id="user_img" name="user_img">
    <button type="button" id="delFile" onclick="del_file()">파일 삭제</button>
    <button type="submit">submit</button>
</form>


</body>
</html>