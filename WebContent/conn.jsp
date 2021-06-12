<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="seoulMate.DBConnection"%> <!-- DB연결 클래스를 import한다. -->
<html>
 
<head>
</head>
<body>
        <table border="3" bordercolor="skyblue">
        <tr bgcolor="skyblue"><td>이름<td>직업</tr>
        
        <%
                // 쿼리문
                        String query="select userid, nickname from member";
                        
                        // 커넥션 연결
                        Connection conn = DBConnection.getConnection();
                        
                        // DB에 쿼리문을 보낸다.
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        // 쿼리문의 결과값을 rs에 담는다.
                        ResultSet rs = pstmt.executeQuery();
                        
                        // 결과값을 출력한다.
                        while(rs.next()){
                            out.println("<tr>");
                            out.println("<td>"+rs.getString("userid"));
                            out.println("<td>"+rs.getString("nickname"));
                            out.println("</tr>");
                        }
                %>
        </table>
</body>
</html>