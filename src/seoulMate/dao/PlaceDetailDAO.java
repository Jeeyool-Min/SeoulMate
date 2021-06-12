package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seoulMate.D;
import seoulMate.dto.PlaceDetailDTO;
import seoulMate.dto.PlaceMainDTO;

public class PlaceDetailDAO {
	
	public PlaceDetailDTO loadPost(String pno) {
		PlaceDetailDTO dto = null;
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
		
        
        String sql="select ptitle, pview, plikeno, pname, imgurl, pinfo, category, offdays, tel, oprtime, fee, address ";
        sql+="from place_post p INNER JOIN place_basicinfo b ON p.pno = b.pno ";
        sql+="INNER JOIN place_locinfo l ON l.pno = p.pno where p.pno="+pno;
        try {
        	try {
    			Class.forName(D.driver);
    			DriverManager.setLoginTimeout(20);
    		} catch(ClassNotFoundException e) {e.printStackTrace();}
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String ptitle = rs.getString("ptitle");
				int pview = rs.getInt("pview")+1;
				int plikeno = rs.getInt("plikeno");
				String pname = rs.getString("pname");
				String imgUrl = rs.getString("imgUrl");
				String pinfo ="";
				if(rs.getString("pinfo")!=null)	pinfo  = rs.getString("pinfo"); 
				String category = rs.getString("category");
				String offdays = rs.getString("offdays");
				String tel = rs.getString("tel");
				String oprtime ="";
				if(rs.getString("oprtime")!=null) oprtime = rs.getString("oprtime");
				String fee ="";
				if(rs.getString("fee")!=null) fee=rs.getString("fee");
				String address = rs.getString("address");
				String gu = address.substring(3,address.indexOf('备')+2);
				dto = new PlaceDetailDTO(ptitle, pview, plikeno, pname, imgUrl, pinfo, category,
						offdays, tel, oprtime, fee, address, gu);
			}
			//炼雀荐贸府
			sql="update place_post set pview = pview+1 where pno =" +pno;
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;	
	}//皋家靛场
	
	

}
