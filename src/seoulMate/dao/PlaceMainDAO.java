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
	
	public PlaceMainDTO[] selectTop6() {
		ArrayList<PlaceMainDTO> list = new ArrayList<PlaceMainDTO>();
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
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
				address = address.substring(3,address.indexOf('±¸')+2);
				
				PlaceMainDTO dto = new PlaceMainDTO(rank, pview, pno, pname, ptitle, category, imgUrl, address);
				list.add(dto);
			}
			
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        int size = list.size();
        PlaceMainDTO [] main = new PlaceMainDTO[size];
        list.toArray(main);
		return main;
	}
	
	public PlaceMainDTO[] selectAll() {
		ArrayList<PlaceMainDTO> list = new ArrayList<PlaceMainDTO>();
		
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql="select rank() over(order by p.pview desc, p.pno desc) as rank,"
        		+ " p.pview, p.pno, b.pname, p.ptitle, b.category, b.imgUrl, l.address from place_post p "
        		+ "INNER JOIN place_basicinfo b ON p.pno = b.pno INNER JOIN place_locinfo l ON p.pno=l.pno";
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
				address = address.substring(3,address.indexOf('±¸')+2);
				
				PlaceMainDTO dto = new PlaceMainDTO(rank, pview, pno, pname, ptitle, category, imgUrl, address);
				list.add(dto);
			}
			
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        int size = list.size();
        PlaceMainDTO [] main = new PlaceMainDTO[size];
        list.toArray(main);
		return main;
	}
}