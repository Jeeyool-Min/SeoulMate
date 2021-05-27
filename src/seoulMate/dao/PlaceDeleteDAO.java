package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import seoulMate.D;

public class PlaceDeleteDAO {

	public PlaceDeleteDAO() {
		try {
			Class.forName(D.driver);
			DriverManager.setLoginTimeout(20);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void deletePlace(int pno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			String query ="delete from place_filter where pno = " + pno;
			pstmt = con.prepareStatement(query);
			pstmt.executeQuery();
			
			query ="delete from place_locinfo where pno = " + pno;
			pstmt = con.prepareStatement(query);
			pstmt.executeQuery();
			
			query ="delete from place_basicInfo where pno = " + pno;
			pstmt = con.prepareStatement(query);
			pstmt.executeQuery();
			
			query = "delete from place_post where pno = " + pno;
			pstmt = con.prepareStatement(query);
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
