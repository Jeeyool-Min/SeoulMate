package seoulMate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seoulMate.D;
import seoulMate.dto.PlaceMainDTO;
import seoulMate.dto.PlaceSearchDTO;

public class placeSearchDAO {
	String rp = ") ";
	String or = "or ";
	String strEnd = "%' ";
	
	public String startQuery() {
		String query = "select s.pno, s.category, s.address, s.pname, s.imgUrl, f.page, f.paccess, f.pkeyword, f.pstyle, f.pcomtype from "; //�⺻����
		String subQ ="(select p.pno, category, address, pname, imgUrl from place_post p "; //�������� ����
		return query+subQ;
	}
	
	//��������(�ʼ���� : Ű����, ����, �������1~8) - ��� ������Ű�� ��� ��������
	
	
	public ArrayList<PlaceSearchDTO> makequery(PlaceSearchDTO dto) {
		String keyword;
		String join1 = "Inner Join place_locinfo l on p.pno = l.pno and (l.address like '%" +dto.getGu() + "%') "; //���� ����
		String join2 = "Inner Join place_basicInfo b on p.pno = b.pno "; //imgUrl ������ ���� ���� ����
		String join3 = "Inner Join place_filter f on p.pno = f.pno ";
		String join3Start = "and (f.pstyle like '%";
		String join3Add = "or f.pstyle like '%";
		//pstyle�� üũ�� ������ŭ �ݺ��� �� rp�� �ݾ���� ��.
		String endSubQ = ") s Inner Join place_filter f ";
		
		//�⺻���� ������
		String condition = "on s.pno = f.pno and (";
		int ctNo =0;
		String condition2 = "f.pcomtype like '%"; //pcomtype :13~16
		
		int acNo=0;
		String condition3 = "f.paccess like '%"; //paccess :18~21
		
		int ageNo=0;
		String condition4 = "f.page like '%"; //page : 9:10��|10:2030|11:4050|12:60+
		
		//���� �� ���� �ݱ�
		String order = ") order by case when "; //+condition2 or 3 or 4 ȣ���ؾ�
		int fcnt = 0; //pstyle, pkeyword ������ ���� üũ�� : 0~3���� �� ����.
		String when ="when ";
		String es = "else "; //fcnt+1
		String end = "end'";
		
		//��������� ����
		String sql = startQuery();
		
		//�������� - from
		sql +=join1 +join2 +join3;
		keyword = dto.getPkeyword();
		String sCondition = "where p.pinfo like '%" +keyword+ "%' or p.ptitle like '%" +keyword+ "%' or f.pkeyword like '%" +keyword+ "%'"; //Ű���� ����
		//join3
		int stNo=0; //tripStyle üũ ��
		if(dto.getPstyle()!=null){ //���� ������ üũ���� �� 
			stNo=dto.getPstyle().length;
			sql +=join3Start + dto.getPstyle()[0] +strEnd;
			for(int i=1; i<stNo; i++) {	sql +=join3Add + dto.getPstyle()[i] + strEnd;}
			sql+=rp;
		}
		
		//�������� - condition �߰�(Ű���尡 �ԷµǾ��� ��)
		if(dto.getPkeyword()!=null) {
			keyword = dto.getPkeyword();
			sql += sCondition + endSubQ;
		}
		
		
		//������ ������ (���Ͱ� üũ�Ǿ� ���� ��)
		if(dto.getPcomtype()!=null) {ctNo=dto.getPcomtype().length; fcnt++;}
		if(dto.getPaccess()!=null) {acNo=dto.getPaccess().length; fcnt++;}
		if(dto.getPage()!=null) {ageNo=dto.getPage().length; fcnt++;}
		
		if (fcnt != 0) { // ���Ͱ� �ϳ��� üũ�Ǿ� �ִٸ� (pstyle ����)
			sql += condition;
			if (ctNo != 0) { //���������� üũ�Ǿ��ִٸ�
				for (int i = 0; i < ctNo; i++) {
					sql += condition2 + dto.getPcomtype()[i] + strEnd;
					order += condition2 + dto.getPcomtype()[i] + strEnd;
					if ((i + 1) != ctNo) {sql += or;	order += or;}
				}
				order += "then 0 ";
			}
			if (acNo != 0) { //���ټ����� üũ�Ǿ��ִٸ�
				if (ctNo != 0) {sql += or; order += when;}
				for (int i = 0; i < acNo; i++) {
					sql += condition3 + dto.getPaccess()[i] + strEnd;
					order += condition3 + dto.getPaccess()[i] + strEnd;
					if ((i + 1) != acNo) {	sql += or;	order += or;}
				}
				order += "then 1 ";
			}
			if (ageNo != 0) { //���ɴ밡 üũ�Ǿ��ִٸ�
				if (acNo != 0 | ctNo != 0) {sql += or; order+=when;}
				for (int i = 0; i < ageNo; i++) {
					sql += condition4 + dto.getPage()[i] + strEnd;
					order += condition4 + dto.getPage()[i] + strEnd;
					if ((i + 1) != ageNo) {	sql += or;	order += or;}
				}
				order += "then 2 ";
			}
			order += "else 3 end";
			sql += order;
		} else {
			sql +=" on s.pno=f.pno";
		}
		System.out.println(sql);
		ArrayList<PlaceSearchDTO> list = new ArrayList<PlaceSearchDTO>();
		list = getResult(sql,list);
		return list;
	}

	private ArrayList<PlaceSearchDTO> getResult(String sql, ArrayList<PlaceSearchDTO> list) {
		Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    	int rsSize=0;
        try {
        	try {
    			Class.forName(D.driver);
    			DriverManager.setLoginTimeout(20);
    		} catch(ClassNotFoundException e) {e.printStackTrace();}
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			PlaceSearchDTO dto =null;
			// s.pno, s.category, s.address, s.pname, s.imgUrl, f.page, f.paccess, f.pkeyword, f.pstyle, f.pcomtype 
			while(rs.next()) {
				rsSize++;
				int pno = rs.getInt("pno");
				String category = rs.getString("category");
				String address = rs.getString("address");
				String gu = address.substring(3,address.indexOf('��')+2);
				String pname = rs.getString("pname");
				String imgUrl = rs.getString("imgUrl");
				//page : 9:10��|10:2030|11:4050|12:60+ (��ȯ �ʿ����)
				String page = "# ��õ���� : " + rs.getString("page");
				
				//paccess :17~20 17 ���߱��� 18 �ý� 19 �ڵ��� 20 ������
				String paccess =rs.getString("paccess");
				if(paccess!=null) {
					
				if(paccess.contains("/")) paccess = paccess.replaceAll("/", " | ");
				if(paccess.contains("17")) paccess = paccess.replace("17", "���߱���");
				if(paccess.contains("18")) paccess = paccess.replace("18", "�ý�"); 
				if(paccess.contains("19")) paccess = paccess.replace("19", "�ڵ���"); 
				if(paccess.contains("20")) paccess = paccess.replace("20", "������");
				paccess ="# ���ټ� : "+paccess; 
				} else {
					paccess="";
				}
				
				String pkeyword = rs.getString("pkeyword");
				if(pkeyword!=null) {
					if(!pkeyword.contains("#")) pkeyword = "#" + pkeyword;
				}else {
					pkeyword="";
				}
				
				//pstyle:1~8 1 �ڿ�/��ġ 2 �޽�/���� 3 ����̺� 4 �߰� 5�̽� 6 ���÷��̽� 7 ü��/�н� 8 ����/��ȭ
				String pstyle = rs.getString("pstyle");
				if(pstyle!=null) {
					if(pstyle.contains("/")) pstyle = pstyle.replaceAll("/", " | ");
					if(pstyle.contains("1")) pstyle = pstyle.replace("1", "�ڿ�/��ġ");
					if(pstyle.contains("2")) pstyle = pstyle.replace("2", "�޽�/����");
					if(pstyle.contains("3")) pstyle = pstyle.replace("3", "����̺�");
					if(pstyle.contains("4")) pstyle = pstyle.replace("4", "�߰�");
					if(pstyle.contains("5")) pstyle = pstyle.replace("5", "�̽�");
					if(pstyle.contains("6")) pstyle = pstyle.replace("6", "���÷��̽�");
					if(pstyle.contains("7")) pstyle = pstyle.replace("7", "ü��/�н�");
					if(pstyle.contains("8")) pstyle = pstyle.replace("8", "����/��ȭ");
					pstyle = "# "+pstyle;
				} else {
					pstyle ="";
				}
				
				//pcomtype :13~16 13 ���� 14 ģ�� 15 ���� 16 ȥ��
				String pcomtype = rs.getString("pcomtype");
				if(pcomtype!=null) {
					if(pcomtype.contains("/")) pcomtype = pcomtype.replaceAll("/", " | ");
					if(pcomtype.contains("13")) pcomtype =  pcomtype.replace("13", "����");
					if(pcomtype.contains("14")) pcomtype = pcomtype.replace("14", "ģ��");
					if(pcomtype.contains("15")) pcomtype = pcomtype.replace("15", "����");
					if(pcomtype.contains("16")) pcomtype = pcomtype.replace("16", "ȥ��");
					pcomtype="# "+pcomtype;
					
				}else {
					pcomtype="";
				}
			
				//public PlaceSearchDTO(String gu, String age, String access, String pkeyword, String style,String comtype, int pno, String category, String pname, String imgUrl) {
				System.out.println(rsSize);
				dto = new PlaceSearchDTO(gu, page, paccess, pkeyword, pstyle, pcomtype, pno, category, pname, imgUrl, rsSize);
				list.add(dto);
				}
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

		/*
		select s.pno, s.category, s.address, s.pname, s.imgUrl, f.page, f.paccess, f.pkeyword, f.pstyle, f.pcomtype from
		from (select p.pno, category, address, pname, imgurl from place_post p
		    Inner Join place_locinfo l on p.pno = l.pno and (l.address like '%����%') 
		    Inner Join place_basicInfo b on p.pno = b.pno 
		    Inner Join place_filter f on p.pno = f.pno and (f.pstyle like '%2%' or f.pstyle like '%8%' or f.pstyle like '%4%') //�������
		    where p.pinfo like '%��%' or p.ptitle like '%��%' or b.pname like '%��%' or f.pkeyword like '%����%'  //Ű����
		    ) s, place_filter f
		where s.pno = f.pno 
		        and (f.pcomtype like '%16%' or f.pcomtype like '%14%'
		            or f.paccess like '%17%'
		            or f.page='30')
		    order by case when f.pcomtype like '%15%' then 0 when f.paccess like '%18%' then 1 when f.page='30' then 0.5 else 3 end;
		 */  
}