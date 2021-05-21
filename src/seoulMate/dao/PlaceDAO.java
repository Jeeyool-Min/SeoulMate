package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import seoulMate.dto.PlaceDTO;

public class PlaceDAO {

	String driver = "oracle.jdbc.driver.OracleDriver"; //대소문자 구분, 오타주의
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "System";
	String passwd = "wjd7dnjs";
	
	public PlaceDAO() {
		try {Class.forName(driver);
		} catch(ClassNotFoundException e) {e.printStackTrace();}
	}
	
	public String submit() { 
		PlaceDTO dto = PlaceDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result="오류없음";
		
		try {
			/*place_post: 글번호, 부제, 닉네임, 작성일, 좋아요, 조회수, 장소설명*/
//			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			System.out.println("닉네임 : "+dto.getNickname());
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into place_post(pno, ptitle, nickname, pwrittenDate, plikeno, pinfo, pview)"
							+ " values(pno_seq.nextval, ?, ?, sysdate, 0, ?, 0)"; //쿼리문 생성
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPtitle());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, dto.getPinfo());
			pstmt.executeUpdate();
			System.out.println("place_post 성공");
			
			/*pno알아오기:해당 닉네임에 의해 데이터베이스에 들어간 정보 최근순으로 가져오기*/
			query = "select pno from (select pno from place_post where nickname='" + dto.getNickname() + "' order by pwrittenDate desc) where rownum=1";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.println("반복문 시작");
			int pno = -1;
			while(rs.next()) {
				pno = rs.getInt("pno");
			}
			System.out.println("반복문끝");
			System.out.println("place_postNo : " +pno);
			
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
			System.out.println("place_basicinfo 입력 성공");
			
			/*place_locinfo*/
			query = "insert into place_locinfo(pno, address, url) values(?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, "API URL"); //저장할 필요가 있을까?
			pstmt.executeUpdate();
			System.out.println("place_locinfo 입력 성공");

			/*place_imgage ArrayList이기 때문에 들어가는 데이터 개수에 따라 쿼리문이 달라짐*/
			query = "insert into place_image(pno, ";
			//쿼리문-컬럼 만들기
			for(int i=0; i<dto.getImgUrl().size(); i++) {
				System.out.println(dto.getImgUrl().get(i).toString());
				query += "url" + (i+1); //url1
				if(i+1==dto.getImgUrl().size()) {query+=") values(?"; break;} //마지막 요소일 때
				else query += ", ";
			} //예시. size=2 → insert into place_image(pno, url1, url2) values(?
			//쿼리문-values 만들기
			for(int i=0; i<=dto.getImgUrl().size(); i++) {
				if(i==dto.getImgUrl().size()) {query+=")"; break;} //마지막반복차수
				else query +=",?";
			}//예시. size=2 → insert into place_image(pno, url1, url2) values(?,?,?)
			pstmt.setInt(1, pno);
			for(int i=0; i<dto.getImgUrl().size(); i++) {
				pstmt.setString(i+2, dto.getImgUrl().get(i));
			}
			pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("place_image 입력 성공");
			
			//age 유도속성 알아내기
			System.out.println("유도속성 구하기 시작");
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
			System.out.println("필터 테이블");
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
		} //end of try
		catch (SQLException e) { e.printStackTrace(); result = e.getMessage(); }
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {e.printStackTrace();}
			return result;
		}
	}
}
