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

import seoulMate.D;
import seoulMate.dto.PlaceDTO;

public class PlaceDAO {
	
	public PlaceDAO() {
		try {
			Class.forName(D.driver);
			DriverManager.setLoginTimeout(20);
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
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
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
			query = "insert into place_basicinfo(pno, pname, category, oprtime, offdays, fee, imgUrl, tel)"
					+ " values(?, ?, ?, ?, ?, ?,?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getPlace().get("pname"));
			pstmt.setString(3, dto.getPlace().get("category"));
			pstmt.setString(4, dto.getPlace().get("oprtime"));
			pstmt.setString(5, dto.getPlace().get("offdays"));
			pstmt.setString(6, dto.getPlace().get("fee"));
			pstmt.setString(7, dto.getPlace().get("imgUrl"));
			pstmt.setString(8, dto.getPlace().get("tel"));
			pstmt.setQueryTimeout(20);
			pstmt.executeUpdate();
			System.out.println("place_basicinfo �Է� ����");
			
			/*place_locinfo*/
			query = "insert into place_locinfo(pno, address) values(?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, pno);
			pstmt.setString(2, dto.getAddress());
			pstmt.executeUpdate();
			System.out.println("place_locinfo �Է� ����");

			
			//age �����Ӽ� �˾Ƴ���
			query = "select to_char((select birthdate from member where nickname = '"+ dto.getNickname() +"'),'YYYY') from dual";
			
			pstmt=con.prepareStatement(query);
			rs = pstmt.executeQuery();
			String age=null;
			while(rs.next()) {
				String birth= rs.getString(1);
				System.out.println(birth);
				int today = Calendar.getInstance().get(Calendar.YEAR);
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
			
			/*place_filter : üũ�ڽ��� �迭�� ���� ���� �����ϱ� ������, �迭�� �������� ������(üũx) ���� �߻�. ���ܹ߻�ó��*/
			/*pstyle 1:�ڿ�/��ġ 2:�޽�/���� 3:����̺� 4:�߰� 5:�̽� 6:���÷��̽� 7:ü��/�н� 8:���繮ȭ 
			 * pperiod 9:����ġ�� 10:1��2�� 11:2��3�� 12:3��4�� | pcomtype 13:���� 14:ģ�� 15:���� 16:ȥ�� |
			 * paccess 17:���߱��� 18:���� 19:�ڵ��� 20:������ | pkeyword �Է°�*/
			query = "insert into place_filter(pno, paccess, pcomtype, pstyle, page, pkeyword, pperiod) values(?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, pno);
			String access = "";
			if(dto.getPcheck().get("paccess")!=null) {
				for(int i=0; i<dto.getPcheck().get("paccess").length; i++) {
					access += dto.getPcheck().get("paccess")[i] + "/";
				}
				access = access.substring(0, access.length()-1);
			}
			pstmt.setString(2, access); //""�� �Է��� ��� DB�� null�� ��
			
			String comtype = "";
			if(dto.getPcheck().get("pcomtype")!=null) {
				for(int i=0; i<dto.getPcheck().get("pcomtype").length; i++) {
					comtype += dto.getPcheck().get("pcomtype")[i] + "/";
				}
				comtype = comtype.substring(0, comtype.length()-1);
			}
			pstmt.setString(3, comtype);
			
			String style = "";
			if(dto.getPcheck().get("pstyle")!=null) {
				for(int i=0; i<dto.getPcheck().get("pstyle").length; i++) {
					style += dto.getPcheck().get("pstyle")[i] + "/";
				}
				style = style.substring(0, style.length()-1);
			}
			pstmt.setString(4, style);
			
			pstmt.setString(5, age);
			
			String keyword = dto.getPcheck().get("pkeyword")[0];
			pstmt.setString(6, keyword);
			
			String period = "";
			if(dto.getPcheck().get("pperiod")!=null) {
				for(int i=0; i<dto.getPcheck().get("pperiod").length; i++) {
					period += dto.getPcheck().get("pperiod")[i] + "/";
				}
				period = period.substring(0, period.length()-1);
			}
			pstmt.setString(7, period);
			pstmt.executeUpdate();
			System.out.println("�Ϸ�");
			
		} //end of try
		catch (Exception e) { e.printStackTrace(); result = e.getMessage(); }
		finally {
			try {
				System.out.print(result);
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (final SQLException e) {e.printStackTrace();}
		}
	} //submit ��
}
