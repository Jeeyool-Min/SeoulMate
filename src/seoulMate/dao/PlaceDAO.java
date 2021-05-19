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

	String driver = "oracle.jdbc.driver.OracleDriver"; //대소문자 구분, 오타주의
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
			/*place_post: 글번호, 부제, 닉네임, 작성일, 좋아요, 조회수, 장소설명*/
			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into place_post(pno, ptitle, nickname, pwrittenDate, plikeno, pinfo, pview)"
							+ " values(pno_seq.nextval, ?, ?, sysdate, 0, ?, 0)"; //쿼리문 생성
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(2, dto.getPtitle());
			pstmt.setString(6, dto.getPinfo());
			pstmt.executeUpdate();
			
			/*pno알아오기:해당 닉네임에 의해 데이터베이스에 들어간 정보 최근순으로 가져오기*/
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
			pstmt.setString(3, "API URL 어떻게 얻어오는지 알아야됨, 그리고 저장할 필요가 있는지 체크");
			pstmt.executeUpdate();

			/*place_imgage*/
			query = "insert into place_image(pno, url1, url2, url3, url4, url5) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			for(int i=0; i<dto.getImgUrl().size(); i++) {
				pstmt.setString(i+2, dto.getImgUrl().get(i));
			}
			pstmt.executeUpdate();

			//age 유도속성 알아내기
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
