package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seoulMate.D;
import seoulMate.dto.PlaceMainDTO;

public class PlaceMainDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public PlaceMainDTO[] selectTop6() {
		ArrayList<PlaceMainDTO> list = new ArrayList<PlaceMainDTO>();
		
        String sql="select * from (select rank() over(order by p.pview desc, p.pno desc) as rank,"
        		+ " p.pview, p.pno, b.pname, p.ptitle, b.category, b.imgUrl, l.address from place_post p "
        		+ "INNER JOIN place_basicinfo b ON p.pno = b.pno INNER JOIN place_locinfo l ON p.pno=l.pno) where rank <=6";
        try {
        	try {
    			Class.forName(D.driver);
    			DriverManager.setLoginTimeout(20);
    		} catch(ClassNotFoundException e) {e.printStackTrace();}
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int rank = rs.getInt("rank");
				int pview = rs.getInt("pview");
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				String ptitle = rs.getString("ptitle");
				String category = rs.getString("category");
				String imgUrl = rs.getString("imgUrl");
				String address = rs.getString("address");
				address = address.substring(3,address.indexOf('구')+2);
				
				PlaceMainDTO dto = new PlaceMainDTO(rank, pview, pno, pname, ptitle, category, imgUrl, address);
				list.add(dto);
			}
			
        } catch (SQLException e) {
        	e.printStackTrace();
        } finally {
        	quitDB();
		}
        
        int size = list.size();
        PlaceMainDTO [] main = new PlaceMainDTO[size];
        list.toArray(main);
		return main;
	}
	
	public PlaceMainDTO[] selectByView(int startPost, int endPost) {
		ArrayList<PlaceMainDTO> list = new ArrayList<PlaceMainDTO>();
		
        String sql="select * from (select rank() over(order by p.pview desc, p.pno desc) as rank,"
        		+ " p.pview, p.pno, b.pname, p.ptitle, b.category, b.imgUrl, l.address from place_post p "
        		+ "INNER JOIN place_basicinfo b ON p.pno = b.pno INNER JOIN place_locinfo l ON p.pno=l.pno) where rank between ? and ?";
        try {
        	try {
    			Class.forName(D.driver);
    			DriverManager.setLoginTimeout(20);
    		} catch(ClassNotFoundException e) {e.printStackTrace();}
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startPost);
			pstmt.setInt(2, endPost);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int rank = rs.getInt("rank");
				int pview = rs.getInt("pview");
				int pno = rs.getInt("pno");
				String pname = rs.getString("pname");
				String ptitle = rs.getString("ptitle");
				String category = rs.getString("category");
				String imgUrl = rs.getString("imgUrl");
				String address = rs.getString("address");
				address = address.substring(3,address.indexOf('구')+2);
				
				PlaceMainDTO dto = new PlaceMainDTO(rank, pview, pno, pname, ptitle, category, imgUrl, address);
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			quitDB();
		}
        
        int size = list.size();
        PlaceMainDTO [] main = new PlaceMainDTO[size];
        list.toArray(main);
		return main;
	}
	
	public int getCount(){
		int count = 0;
		String sql = "select count(*) from place_post";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			quitDB();
		}
		return count; // 총 레코드 수 리턴
	}

	
	private void quitDB() {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null)	con.close();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	private Connection getConnection() {
		try {
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}