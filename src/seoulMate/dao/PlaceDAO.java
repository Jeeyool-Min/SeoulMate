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

	String driver = "oracle.jdbc.driver.OracleDriver"; //��ҹ��� ����, ��Ÿ����
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
		String result="��������";
		
		try {
			/*place_post: �۹�ȣ, ����, �г���, �ۼ���, ���ƿ�, ��ȸ��, ��Ҽ���*/
//			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			System.out.println("�г��� : "+dto.getNickname());
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into place_post(pno, ptitle, nickname, pwrittenDate, plikeno, pinfo, pview)"
							+ " values(pno_seq.nextval, ?, ?, sysdate, 0, ?, 0)"; //������ ����
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPtitle());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, dto.getPinfo());
			pstmt.executeUpdate();
			System.out.println("place_post ����");
			
			/*pno�˾ƿ���:�ش� �г��ӿ� ���� �����ͺ��̽��� �� ���� �ֱټ����� ��������*/
			query = "select pno from (select pno from place_post where nickname='" + dto.getNickname() + "' order by pwrittenDate desc) where rownum=1";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			System.out.println("�ݺ��� ����");
			int pno = -1;
			while(rs.next()) {
				pno = rs.getInt("pno");
			}
			System.out.println("�ݺ�����");
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
			System.out.println("place_basicinfo �Է� ����");
			
			/*place_locinfo*/
			query = "insert into place_locinfo(pno, address, url) values(?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, "API URL"); //������ �ʿ䰡 ������?
			pstmt.executeUpdate();
			System.out.println("place_locinfo �Է� ����");

			/*place_imgage ArrayList�̱� ������ ���� ������ ������ ���� �������� �޶���*/
			query = "insert into place_image(pno, ";
			//������-�÷� �����
			for(int i=0; i<dto.getImgUrl().size(); i++) {
				System.out.println(dto.getImgUrl().get(i).toString());
				query += "url" + (i+1); //url1
				if(i+1==dto.getImgUrl().size()) {query+=") values(?"; break;} //������ ����� ��
				else query += ", ";
			} //����. size=2 �� insert into place_image(pno, url1, url2) values(?
			//������-values �����
			for(int i=0; i<=dto.getImgUrl().size(); i++) {
				if(i==dto.getImgUrl().size()) {query+=")"; break;} //�������ݺ�����
				else query +=",?";
			}//����. size=2 �� insert into place_image(pno, url1, url2) values(?,?,?)
			pstmt.setInt(1, pno);
			for(int i=0; i<dto.getImgUrl().size(); i++) {
				pstmt.setString(i+2, dto.getImgUrl().get(i));
			}
			pstmt=con.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("place_image �Է� ����");
			
			//age �����Ӽ� �˾Ƴ���
			System.out.println("�����Ӽ� ���ϱ� ����");
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
			System.out.println("���� ���̺�");
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
