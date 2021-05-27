package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import seoulMate.D;
import seoulMate.dto.PlaceDTO;

public class PlaceUpdateDAO 
{
	public PlaceUpdateDAO() {
		try {
			Class.forName(D.driver);
			DriverManager.setLoginTimeout(20);
		} catch(ClassNotFoundException e) {e.printStackTrace();}
	}
	//���� �� ���� �ڷ� �ҷ�����
	public void loadInfo(int pno) { 
		PlaceDTO dto = PlaceDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result="��������";
		
		try {
			/*place_post: �۹�ȣ, ����, �г���, �ۼ���, ���ƿ�, ��ȸ��, ��Ҽ���*/
//			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			String query = "select ptitle, pinfo, nickname from place_post where pno = " + pno;
			
			pstmt = con.prepareStatement(query);
			pstmt.setQueryTimeout(30);
			rs = pstmt.executeQuery();
			while(rs.next()) { //pinfo�� null��� (���⼭ nickname���� �����δ� id�� �����;���)
				String ptitle = rs.getString("ptitle");
				String pinfo = rs.getString("pinfo");
				if(pinfo==null)	pinfo =""; //������ ������ null������ ��µ�
				String nickname = rs.getString("nickname");
				dto.setPtitle(ptitle);
				dto.setPinfo(pinfo);
				dto.setNickname(nickname);
			}
			
			/*place_basicinfo*/
			query = "select pname, category, oprtime, offdays, fee, imgUrl, tel from place_basicinfo where pno = " + pno;
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			HashMap<String, String> basic = new HashMap<String, String>();
			while(rs.next()) { //oprtime, fee, tel null�� ���
				basic.put("pname",rs.getString("pname")); 
				basic.put("category", rs.getString("category"));
				String oprtime = rs.getString("oprtime");
				if(oprtime == null) oprtime= "";
				basic.put("oprtime", oprtime);
				basic.put("offdays", rs.getString("offdays"));
				String fee = rs.getString("fee");
				if(fee == null) fee = "";
				basic.put("fee", fee);
				basic.put("imgUrl", rs.getString("imgUrl"));
				String tel = rs.getString("tel");
				if(tel == null) tel="";
				basic.put("tel", tel);
 			}
			dto.setPlace(basic);
			
			/*place_locinfo*/
			query = "select address from place_locinfo where pno =" + pno;
			pstmt = con.prepareStatement(query);
			rs =pstmt.executeQuery();
			while(rs.next()) dto.setAddress(rs.getString(1));
			
			
			/*place_filter : üũ�ڽ��� �迭�� ���� ���� �����ϱ� ������, �迭�� �������� ������(üũx) ���� �߻�. ���ܹ߻�ó��*/
			/*pstyle 1:�ڿ�/��ġ 2:�޽�/���� 3:����̺� 4:�߰� 5:�̽� 6:���÷��̽� 7:ü��/�н� 8:���繮ȭ 
			 * pperiod 9:����ġ�� 10:1��2�� 11:2��3�� 12:3��4�� | pcomtype 13:���� 14:ģ�� 15:���� 16:ȥ�� |
			 * paccess 17:���߱��� 18:���� 19:�ڵ��� 20:������ | pkeyword �Է°�*/
			query = "select pstyle, pperiod, pcomtype, paccess, pkeyword from place_filter where pno="+ pno;
			pstmt=con.prepareStatement(query);
			rs = pstmt.executeQuery();
			//����üũ�� ���û����̹Ƿ� null���� ���� �� �ֱ� ������ ����ó�� �ʿ�(nullPointerException ����)
			HashMap<String, String[]> pcheck = new HashMap(); //filter �� name�� ���� value���� �迭�� �־��� �ؽ���
			

			while (rs.next()) { //�˻������ 1���� ����
			String pstyle = rs.getString("pstyle");
			String pperiod = rs.getString("pperiod");
			String pcomtype = rs.getString("pcomtype");
			String paccess = rs.getString("paccess");
			String pkeyword = rs.getString("pkeyword");
			
				String[] filterValues = new String[5];
				ArrayList<String> list = new ArrayList();
				for(int i=0; i<filterValues.length; i++) {
					filterValues[i] = rs.getString(i+1); // 1/2/5 | 9/11 | 13/16 | 20 |#Ű���� #������(#���� �ܼ� ���ڿ��ϼ���.Ű����� �ϳ��� ���ڿ��� ó���ϴ� �� ����) ==> �̷������� �Էµ�
				}
				String[] filterNames = {"pstyle", "pperiod","pcomtype", "paccess", "pkeyword"}; 
				for(int i=0; i<filterNames.length; i++) {
					if (filterValues[i] != null) { // üũ�� �ִٸ�
						if (filterValues[i].contains("/")) { // �� �� �̻� ����
							for(int j=0; j<filterValues[i].split("/").length; j++) {
								list.add(filterValues[i].split("/")[j]);
							}
							String[] strArr = list.toArray(new String[0]);
							pcheck.put(filterNames[i], strArr);
							list = new ArrayList();
							
						} else { // �ϳ��� ����
							String[] st = {filterValues[i]};
							pcheck.put(filterNames[i], st);
						}
					} else { // null�̶��(����X)
						pstyle = "";
						pcheck.put(filterNames[i], null); //Map null�� �����
					}
				}
			}
			dto.setPcheck(pcheck);
			
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
	} //�޼ҵ� ��
	
	public void modify(int pno) { 
		PlaceDTO dto = PlaceDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			/*place_post: �۹�ȣ, ����, �г���, �ۼ���, ���ƿ�, ��ȸ��, ��Ҽ���*/
//			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			String query = "update place_post set ptitle=?, pinfo =? where pno = " +dto.getPno(); 
			//dto.getPtitle() +"', pinfo='"+dto.getPinfo()+"' where pno = "+dto.getPno();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPtitle());
			pstmt.setString(2, dto.getPinfo());
			pstmt.setQueryTimeout(30);
			pstmt.executeUpdate();
			System.out.println("place_post �����Ϸ�");
			
			/*place_basicinfo*/
			//query	= "update place_basicinfo set pname =?, category=?, oprtime=?, offdays=?, fee=?, imgUrl =?, tel=? where pno =" + pno;
			query = "update place_basicinfo set pname = '" + dto.getPlace().get("pname") 
					+"', category ='" + dto.getPlace().get("category") + "', oprtime = '"+dto.getPlace().get("oprtime")
					+"', offdays = '" + dto.getPlace().get("offdays") + "', fee ='" +dto.getPlace().get("fee")
					+"', imgUrl = '" +dto.getPlace().get("imgUrl")+"', tel='" +dto.getPlace().get("tel")
					+ "' where pno =" + pno;
				
			pstmt = con.prepareStatement(query);
			pstmt.setQueryTimeout(20);
			pstmt.executeUpdate();
			System.out.println("place_basicinfo �����Ϸ�");
			
			/*place_locinfo*/
			query = "update place_locinfo set address = '"+dto.getAddress() + "' where pno = " + pno;
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("place_locinfo �����Ϸ�");

			
			/*place_filter : üũ�ڽ��� �迭�� ���� ���� �����ϱ� ������, �迭�� �������� ������(üũx) ���� �߻�. ���ܹ߻�ó��*/
			/*pstyle 1:�ڿ�/��ġ 2:�޽�/���� 3:����̺� 4:�߰� 5:�̽� 6:���÷��̽� 7:ü��/�н� 8:���繮ȭ 
			 * pperiod 9:����ġ�� 10:1��2�� 11:2��3�� 12:3��4�� | pcomtype 13:���� 14:ģ�� 15:���� 16:ȥ�� |
			 * paccess 17:���߱��� 18:���� 19:�ڵ��� 20:������ | pkeyword �Է°�*/
			query = "update place_filter set paccess =?, pcomtype =?, pstyle=?, pkeyword=?, pperiod=? where pno =" +pno;
			System.out.println(query);
			pstmt=con.prepareStatement(query);
			String access = "";
			if(dto.getPcheck().get("paccess")!=null) {
				for(int i=0; i<dto.getPcheck().get("paccess").length; i++) {
					access += dto.getPcheck().get("paccess")[i] + "/";
				}
				access = access.substring(0, access.length()-1);
			}
			pstmt.setString(1, access); //""�� �Է��� ��� DB�� null�� ��
			System.out.println(access+":access");
			
			String comtype = "";
			if(dto.getPcheck().get("pcomtype")!=null) {
				for(int i=0; i<dto.getPcheck().get("pcomtype").length; i++) {
					comtype += dto.getPcheck().get("pcomtype")[i] + "/";
				}
				comtype = comtype.substring(0, comtype.length()-1);
			}
			pstmt.setString(2, comtype);
			System.out.println(comtype+"-comtype");
			
			String style = "";
			if(dto.getPcheck().get("pstyle")!=null) {
				for(int i=0; i<dto.getPcheck().get("pstyle").length; i++) {
					style += dto.getPcheck().get("pstyle")[i] + "/";
				}
				style = style.substring(0, style.length()-1);
			}
			pstmt.setString(3, style);
			System.out.println(style+"-style");
			
			String keyword = dto.getPcheck().get("pkeyword")[0];
			pstmt.setString(4, keyword);
			System.out.println("keyword:"+keyword);
			
			String period = "";
			if(dto.getPcheck().get("pperiod")!=null) {
				for(int i=0; i<dto.getPcheck().get("pperiod").length; i++) {
					period += dto.getPcheck().get("pperiod")[i] + "/";
				}
				period = period.substring(0, period.length()-1);
			}
			System.out.println("period:" +period);
			pstmt.setString(5, period);
			pstmt.executeUpdate();
			System.out.println("�Ϸ�");
			
		} //end of try
		catch (Exception e) { e.printStackTrace(); }
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (final SQLException e) {e.printStackTrace();}
		}
	} //submit ��
	
}
