package seoulMate;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	public static void main(String[] args) throws SQLException {
		java.sql.Connection conn =null;
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "System";
			String password = "wjd7dnjs";
			
			Class.forName(driver);
			System.out.println("jdbc driver �ε� ����");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("����Ŭ ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver �ε� ����");
		} catch (SQLException ex) {
			System.out.println("����Ŭ ���� ����");
			System.out.println("SQLException: " + ex.getMessage());
		} finally {
			if(conn!=null) conn.close();
		}
	}
}
