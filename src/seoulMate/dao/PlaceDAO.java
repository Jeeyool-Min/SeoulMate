package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import seoulMate.dto.PlaceDTO;

public class PlaceDAO {
	
	String driver = "oracle.jdbc.driver.OracleDriver"; //대소문자 구분, 오타주의
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String userid = "System";
	String passwd = "wjd7dnjs";
	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
	
	public PlaceDAO() {
		try {
			Class.forName(driver);
			System.out.println(getCurrentTimeStamp());
			DriverManager.setLoginTimeout(20);
			System.out.println(getCurrentTimeStamp());
			
		} catch(ClassNotFoundException e) {e.printStackTrace();}
	}
	
	public void submit() { 
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
			pstmt.setQueryTimeout(30);
			pstmt.setString(1, dto.getPtitle());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, dto.getPinfo());
			pstmt.executeUpdate();
			System.out.println("place_post 성공");
			
			/*pno알아오기:해당 닉네임에 의해 데이터베이스에 들어간 정보 최근순으로 가져오기*/
			query = "select pno from (select pno from place_post where nickname='" + dto.getNickname() + "' order by pwrittenDate desc) where rownum=1";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			int pno = -1;
			while(rs.next()) {
				pno = rs.getInt("pno");
			}
			System.out.println("place_postNo : " +pno);
			
			
			/*place_basicinfo*/
			query = "insert into place_basicinfo(pno, pname, category, oprtime, offdays, fee, imgUrl)"
					+ " values(?, ?, ?, ?, ?, ?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getPlace().get("pname"));
			pstmt.setString(3, dto.getPlace().get("category"));
			pstmt.setString(4, dto.getPlace().get("oprtime"));
			pstmt.setString(5, dto.getPlace().get("offdays"));
			pstmt.setString(6, dto.getPlace().get("fee"));
			pstmt.setString(7, dto.getPlace().get("imgUrl"));
			pstmt.executeUpdate();
			System.out.println("place_basicinfo 입력 성공");
			pstmt.setQueryTimeout(10);
			
			/*place_locinfo*/
			query = "insert into place_locinfo(pno, address, url) values(?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, "API URL"); //저장할 필요가 있을까?
			pstmt.executeUpdate();
			System.out.println("place_locinfo 입력 성공");

			
			//age 유도속성 알아내기
			System.out.println("유도속성 구하기 시작");


			query = "select to_char((select birthdate from member where nickname = '"+ dto.getNickname() +"'),'YYYY') from dual";
			
			pstmt=con.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.print(rs.getString(1));
			String age=null;
			while(rs.next()) {
				String birth= rs.getString(1);
				int today = Calendar.getInstance().YEAR;
				int bYear = Integer.parseInt(birth);
				int ages = (today - bYear +1)/10;
				switch (ages) {
				case 1: age = "10"; break;
				case 2: age = "20"; break;
				case 3: age = "30"; break;
				case 4: age = "40"; break;
				case 5: age = "50"; break;
				default: age = "60+"; break;
				}
			}
			System.out.print(age);
			
			/*place_filter : 체크박스로 배열에 여러 값을 저장하기 때문에, 배열이 존재하지 않으면(체크x) 에러 발생. 예외발생처리*/
			System.out.println("필터 테이블");
			/*pstyle 1:자연/경치 2:휴식/힐링 3:드라이브 4:야경 5:미식 6:핫플레이스 7:체험/학습 8:역사문화 
			 * pperiod 9:당일치기 10:1박2일 11:2박3일 12:3박4일 | pcomtype 13:가족 14:친구 15:연인 16:혼자 |
			 * paccess 17:대중교통 18:도보 19:자동차 20:자전거 | pkeyword 입력값*/
			query = "insert into place_filter(pno, paccess, pcomtype, pstyle, page, pkeyword, pperiod) values(?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, pno);
			String access = "";
			for(int i=0; i<dto.getPcheck().get("paccess").length; i++) {
				access += dto.getPcheck().get("paccess")[i] + "/";
			}
			pstmt.setString(2, access); //이 값이 null이라면? null이란 String을 넣을것인가? 아니면 null로 바꾸나.
			
			String comtype = "";
			for(int i=0; i<dto.getPcheck().get("pcomtype").length; i++) {
				comtype += dto.getPcheck().get("pcomtype")[i] + "/";
			}
			pstmt.setString(3, comtype);
			
			String style = "";
			for(int i=0; i<dto.getPcheck().get("pstyle").length; i++) {
				style += dto.getPcheck().get("pstyle")[i] + "/";
			}
			pstmt.setString(4, style);
			
			pstmt.setString(5, age);
			
			String keyword = "";
			for(int i=0; i<dto.getPcheck().get("pkeyword").length; i++) {
				keyword += dto.getPcheck().get("pkeyword")[i] + "/";
			}
			pstmt.setString(6, keyword);
			
			String period = "";
			for(int i=0; i<dto.getPcheck().get("pperiod").length; i++) {
				period += dto.getPcheck().get("pperiod")[i] + "/";
			}
			pstmt.setString(7, period);
			pstmt.executeUpdate();
			
		} //end of try
		catch (SQLException e) { e.printStackTrace(); result = e.getMessage(); }
		finally {
			try {
				System.out.print(result);
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (final SQLException e) {e.printStackTrace();}
		}
	}
}
