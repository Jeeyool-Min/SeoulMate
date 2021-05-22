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
	
	String driver = "oracle.jdbc.driver.OracleDriver"; //��ҹ��� ����, ��Ÿ����
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
		String result="��������";
		
		try {
			/*place_post: �۹�ȣ, ����, �г���, �ۼ���, ���ƿ�, ��ȸ��, ��Ҽ���*/
//			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			System.out.println("�г��� : "+dto.getNickname());
			con = DriverManager.getConnection(url, userid, passwd);
			String query = "insert into place_post(pno, ptitle, nickname, pwrittenDate, plikeno, pinfo, pview)"
							+ " values(pno_seq.nextval, ?, ?, sysdate, 0, ?, 0)"; //������ ����
			
			pstmt = con.prepareStatement(query);
			pstmt.setQueryTimeout(30);
			pstmt.setString(1, dto.getPtitle());
			pstmt.setString(2, dto.getNickname());
			pstmt.setString(3, dto.getPinfo());
			pstmt.executeUpdate();
			System.out.println("place_post ����");
			
			/*pno�˾ƿ���:�ش� �г��ӿ� ���� �����ͺ��̽��� �� ���� �ֱټ����� ��������*/
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
			System.out.println("place_basicinfo �Է� ����");
			pstmt.setQueryTimeout(10);
			
			/*place_locinfo*/
			query = "insert into place_locinfo(pno, address, url) values(?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getAddress());
			pstmt.setString(3, "API URL"); //������ �ʿ䰡 ������?
			pstmt.executeUpdate();
			System.out.println("place_locinfo �Է� ����");

			
			//age �����Ӽ� �˾Ƴ���
			System.out.println("�����Ӽ� ���ϱ� ����");


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
			
			/*place_filter : üũ�ڽ��� �迭�� ���� ���� �����ϱ� ������, �迭�� �������� ������(üũx) ���� �߻�. ���ܹ߻�ó��*/
			System.out.println("���� ���̺�");
			/*pstyle 1:�ڿ�/��ġ 2:�޽�/���� 3:����̺� 4:�߰� 5:�̽� 6:���÷��̽� 7:ü��/�н� 8:���繮ȭ 
			 * pperiod 9:����ġ�� 10:1��2�� 11:2��3�� 12:3��4�� | pcomtype 13:���� 14:ģ�� 15:���� 16:ȥ�� |
			 * paccess 17:���߱��� 18:���� 19:�ڵ��� 20:������ | pkeyword �Է°�*/
			query = "insert into place_filter(pno, paccess, pcomtype, pstyle, page, pkeyword, pperiod) values(?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, pno);
			String access = "";
			for(int i=0; i<dto.getPcheck().get("paccess").length; i++) {
				access += dto.getPcheck().get("paccess")[i] + "/";
			}
			pstmt.setString(2, access); //�� ���� null�̶��? null�̶� String�� �������ΰ�? �ƴϸ� null�� �ٲٳ�.
			
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
