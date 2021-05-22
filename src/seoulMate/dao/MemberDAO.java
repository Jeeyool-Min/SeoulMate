package seoulMate.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seoulMate.dto.MemberDTO;

//�����ͺ��̽��� �����ϱ�, SQL�� ������ �ޱ�
public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();

	String driver = "oracle.jdbc.driver.OracleDriver"; //��ҹ��� ����, ��Ÿ����
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String userid = "System";
	String passwd = "wjd7dnjs";
	
	public MemberDAO() {
		try { Class.forName(driver);  //����Ŭ ����̹� ���
		} catch (ClassNotFoundException e) {e.printStackTrace();} 
	} //������ ��
	
	public static MemberDAO getInstance() {	return instance;}
	
	//ȸ���� ��� ���� �ҷ����� [���� ������ �Ⱦ�����, ���߿� ����]
	public ArrayList<MemberDTO> selectAll(){
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //executeQuery�� ����Ÿ��
				
		try { 
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "select * from member"; //������ ����
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); //SQL �� select ���� ����. ���ǰ���� rs�� �޴´� 
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setName(rs.getString("name")); //�ε����� �ʵ��
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
		ResultSet rs = null; //executeQuery�� ����Ÿ��
				
		try { 
			con = DriverManager.getConnection(url, userid, passwd);
			//�Է¹��� ���̵�� ���̵�, ��й�ȣ
			String query = "select userId, nickname from member"; 
			String condition = " where userId = '" + userId + "'";
			String condition2 = " and password = '" + password + "'";
			query += condition + condition2;
			
			/*!�߰� : ���̵�, �г����� ���ǿ� ����*/
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery(); //SQL �� select ���� ����. ���ǰ���� rs�� �޴´�
			
			while(rs.next()) {
			nickname = (rs.getString("nickname")); /*�˰� ���� ���� �÷� �̸�*/
				System.out.println("�α��� ����");
			}
		}
		catch (SQLException e) { 
			e.printStackTrace();
			System.out.println("��ġ�ϴ� ���̵�� ��й�ȣ�� �����ϴ�.");
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
	
	//ȸ������
	Date birthdate= Date.valueOf("1990-09-05");	/*date �׽�Ʈ*/
	public void join() {
		MemberDTO dto = MemberDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//DB���� �� SQL���ۼ�
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into member(userId, password, name, nickname, email, birthdate) values(?,?,?,?,?,?)";
			
			
			//value�� dto�κ��� �ҷ��ͼ� ����
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNickname());
			pstmt.setString(5, dto.getEmail());
			pstmt.setDate(6, dto.getBirthdate());
			pstmt.executeUpdate();  /*����, ����, ���� ���� ���� ����*/
			
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
 