<%@ page import="java.sql.*" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html>
<html>
<head>
<title>데이터베이스 연결하기</title>
</head>
<body>
	<%
		java.sql.Connection conn = null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String user = "System";
			String password = "wjd7dnjs";

			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException ex) {
			System.out.println("오라클 연결 실패");
			System.out.println("SQLException: " + ex.getMessage());
		}
		
	%>
</body>
</html>