package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seoulMate.dto.PlaceDTO;

public class PlaceDAO {

	String driver = "oracle.jdbc.driver.OracleDriver"; //��ҹ��� ����, ��Ÿ����
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "System";
	String passwd = "wjd7dnjs";
	
	public PlaceDAO() {
		try {Class.forName(driver);
		}catch(ClassNotFoundException e) {e.printStackTrace();}
	}
	
	public void submit() { 
		PlaceDTO dto = PlaceDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			/*place_post: �۹�ȣ, ����, �г���, �ۼ���, ���ƿ�, ��ȸ��, ��Ҽ���*/
			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into place_post(pno, ptitle, nickname, pwrittenDate, plikeno, pinfo, pview)"
							+ " values(pno_seq.nextval, ?, ?, sysdate, 0, ?, 0)"; //������ ����
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(2, dto.getPtitle());
			pstmt.setString(6, dto.getPinfo());
			pstmt.executeUpdate();
			
			/*pno�˾ƿ���:�ش� �г��ӿ� ���� �����ͺ��̽��� �� ���� �ֱټ����� ��������*/
			ResultSet rs = null;
			query = "select pno from (select pno from place_post where nickname='" + dto.getNickname() + "' order by pwrittenDate desc) where rownum=1";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			int pno = rs.getInt("pno");
			
			/*place_basicinfo*/
			query = "insert into place_basicinfo(pno, pname, category, oprtime, offdays, fee)"
					+ " values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getPlace().get("pname"));
			pstmt.setString(3, dto.getPlace().get("category"));
			pstmt.setString(4, dto.getPlace().get("oprtime"));
			pstmt.setString(5, dto.getPlace().get("offdays"));
			pstmt.setString(6, dto.getPlace().get("fee"));
			pstmt.executeUpdate();
			
			/*place_locinfo*/
			query = "insert into place_locinfo(pno, address, url) values(?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, "API URL ��� �������� �˾ƾߵ�, �׸��� ������ �ʿ䰡 �ִ��� üũ");
			pstmt.executeUpdate();

			/*place_imgage*/
			query = "insert into place_image(pno, url1, url2, url3, url4, url5) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			for(int i=0; i<dto.getImgUrl().size(); i++) {
				pstmt.setString(i+2, dto.getImgUrl().get(i));
			}
			pstmt.executeUpdate();

			//age �����Ӽ� �˾Ƴ���
			query = "select birthdate from member where nickname = '" + dto.getNickname() + "'";
			pstmt=con.prepareStatement(query);
			rs = pstmt.executeQuery();
			String birth= rs.getDate(1).toString();
			int today = Calendar.getInstance().YEAR;
			int bYear = Integer.parseInt(birth.substring(0, 3));
			int ages = (today - bYear +1)/10;
			String age=null;
			switch (ages) {
			case 1: age = "10"; break;
			case 2: age = "20"; break;
			case 3: age = "30"; break;
			case 4: age = "40"; break;
			case 5: age = "50"; break;
			default: age = "60+"; break;
			}
			
			/*place_filter*/
			query = "insert into place_filter(pno, paccess, pcomtype, pstyle, page, pkeyword) values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, pno);
			String access = null;
			for(int i=0; i<dto.getPcheck().get("paccess").length; i++) {
				access += dto.getPcheck().get("paccess")[i] + "/";
			}
			pstmt.setString(2, access);
			
			String comtype = null;
			for(int i=0; i<dto.getPcheck().get("pcomtype").length; i++) {
				comtype += dto.getPcheck().get("pcomtype")[i] + "/";
			}
			pstmt.setString(3, comtype);
			
			String style = null;
			for(int i=0; i<dto.getPcheck().get("pstyle").length; i++) {
				style += dto.getPcheck().get("pstyle")[i] + "/";
			}
			pstmt.setString(4, style);
			
			pstmt.setString(5, age);
			
			String keyword = null;
			for(int i=0; i<dto.getPcheck().get("pkeyword").length; i++) {
				style += dto.getPcheck().get("pkeyword")[i] + "/";
			}
			pstmt.setString(6, keyword);
			
			pstmt.executeUpdate();
			
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally {
			try {
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
				} catch (SQLException e) {	e.printStackTrace();	}
		}
	}
	
	
}
