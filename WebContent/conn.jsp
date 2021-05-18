<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>자바연동확인</title>
</head>
<body>
<%
	Connection conn=null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "System";
	String password="wjd7dnjs";
	Boolean connect = false;
		
	try{
	    Class.forName(driver);
	    conn=DriverManager.getConnection(url,userid,password); //자신의 아이디와 비밀번호
	    connect = true;
	    conn.close();
	}catch(Exception e){
	    connect = false;
	    e.printStackTrace();
	}
%>
<%
	if(connect==true){%>
	    연결되었습니다.
	<%}else{ %>
	    연결에 실패하였습니다.
	<%}
%> 