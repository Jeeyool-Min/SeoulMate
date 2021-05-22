package seoulMate.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seoulMate.dto.MemberDTO;

//데이터베이스랑 연결하기, SQL문 보내고 받기
public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();

	String driver = "oracle.jdbc.driver.OracleDriver"; //대소문자 구분, 오타주의
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String userid = "System";
	String passwd = "wjd7dnjs";
	
	public MemberDAO() {
		try { Class.forName(driver);  //오라클 드라이버 사용
		} catch (ClassNotFoundException e) {e.printStackTrace();} 
	} //생성자 끝
	
	public static MemberDAO getInstance() {	return instance;}
	
	//회원의 모든 정보 불러오기 [지금 당장은 안쓰지만, 나중에 위해]
	public ArrayList<MemberDTO> selectAll(){
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //executeQuery의 리턴타입
				
		try { 
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from member"; //쿼리문 생성
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); //SQL 안 select 쿼리 실행. 질의결과를 rs에 받는다 
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setName(rs.getString("name")); //인덱스는 필드명
				dto.setNickname(rs.getString("nickname"));
				dto.setUserId(rs.getString("userId"));
				dto.setPassword(rs.getString("password"));
				dto.setBirthdate(rs.getDate("birthdate"));
				dto.setEmail(rs.getString("email"));
				list.add(dto);
			}
		}
		catch (SQLException e) { e.printStackTrace(); }
		finally {
			try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null)
				con.close();
				} catch (SQLException e) {	e.printStackTrace();	}
		}
		return list;
	}//the end of selectAll method
	
	public String login(String userId, String password){
		String nickname = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //executeQuery의 리턴타입
				
		try { 
			con = DriverManager.getConnection(url, userid, passwd);
			//입력받은 아이디로 아이디, 비밀번호
			String query = "select userId, nickname from member"; 
			String condition = " where userId = '" + userId + "'";
			String condition2 = " and password = '" + password + "'";
			query += condition + condition2;
			
			/*!추가 : 아이디, 닉네임을 세션에 저장*/
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); //SQL 안 select 쿼리 실행. 질의결과를 rs에 받는다
			
			while(rs.next()) {
			nickname = (rs.getString("nickname")); /*알고 싶은 값의 컬럼 이름*/
				System.out.println("로그인 성공");
			}
		}
		catch (SQLException e) { 
			e.printStackTrace();
			System.out.println("일치하는 아이디와 비밀번호가 없습니다.");
		}
		finally {
			try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null)
				con.close();
				} catch (SQLException e) {	e.printStackTrace();	}
		}
		return nickname;
	}//the end of selectAll method
	
	//회원가입
	Date birthdate= Date.valueOf("1990-09-05");	/*date 테스트*/
	public void join() {
		MemberDTO dto = MemberDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//DB연결 및 SQL문작성
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into member(userId, password, name, nickname, email, birthdate) values(?,?,?,?,?,?)";
			
			
			//value값 dto로부터 불러와서 저장
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNickname());
			pstmt.setString(5, dto.getEmail());
			pstmt.setDate(6, dto.getBirthdate());
			pstmt.executeUpdate();  /*삽입, 수정, 삭제 관련 쿼리 실행*/
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
				} catch (SQLException e) {e.printStackTrace();}
		}
	} //the end of join method
	
	
}
 