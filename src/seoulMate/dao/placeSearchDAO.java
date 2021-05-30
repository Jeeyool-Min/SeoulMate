package seoulMate.dao;

import seoulMate.dto.PlaceSearchDTO;

public class placeSearchDAO {
	PlaceSearchDTO dto = PlaceSearchDTO.getInstance();
	String rp = ") ";
	String or = "or ";
	String keyword;
	String strEnd = "%' ";
	public String startQuery() {
		String query = "select s.pno, s.category, s.address, s.pname, s.imgUrl, f.page, f.paccess, f.pkeyword, f.pstyle, f.pcomtype from "; //�⺻����
		String subQ ="(select p.pno, category, address, pname, imgUrl from place_post p "; //�������� ����
		return query+subQ;
	}
	
	//��������(�ʼ���� : Ű����, ����, �������1~8) - ��� ������Ű�� ��� ��������
	public String getjoin1() {
		String gu = dto.getGu();
		String join1 = "Inner Join place_locinfo l on p.pno = l.pno and (l.address like '%" +gu + "%') "; //���� ����
		return join1;
	}
	
	String join2 = "Inner Join place_basicInfo b on p.pno = b.pno "; //imgUrl ������ ���� ���� ����
	String join3 = "Inner Join place_filter f on p.pno = f.pno and (f.pstyle like '%";
	String join3Add = "or f.pstyle like '%";
	 			//pstyle�� üũ�� ������ŭ �ݺ��� �� rp�� �ݾ���� ��.
	String endSubQ = ") s, place_filter f "; 
	
	public String getSCondition() {
		if(dto.getPkeyword()!=null) {keyword = dto.getPkeyword();}
		String sCondition = "where p.pinfo like '%" +keyword+ "%' or p.ptitle like '%" +keyword+ "%' or f.pkeyword like '%" +keyword+ "%'"; //Ű���� ����
		return sCondition;
	}
	
	//�⺻���� ������
	String condition = "where s.pno = f.pno and (";
	int i;
	int ctNo;
	String condition2 = "f.pcomtype like '%"; //pcomtype :13~16
	
	int acNo;
	String condition3 = "f.paccess like '%"; //paccess :18~21
	
	int ageNo;
	String condition4 = "f.page like '%"; //page : 9:10��|10:2030|11:4050|12:60+
	
	//���� �� ���� �ݱ�
	String order = ") order by case when "; //+condition2 or 3 or 4 ȣ���ؾ�
	int fcnt; //pstyle, pkeyword ������ ���� üũ�� : 0~3���� �� ����.
	String when ="when ";
	String es = "else "; //fcnt+1
	String end = "end'";
	
	public String makequery() {
		String sql = startQuery();
		
		//�������� - from
		sql +=getjoin1() +join2;
		//join3
		int stNo=0; //tripStyle üũ ��
		if(dto.getPstyle()!=null){ //���� ������ üũ���� �� 
			stNo=dto.getPstyle().length;
			sql +=join3 + dto.getPstyle()[0] +strEnd;
			for(int i=1; i<stNo; i++) {	sql +=join3Add + dto.getPstyle()[i] + strEnd;}
			sql+=rp;
		}
		
		//�������� - condition �߰�(Ű���尡 �ԷµǾ��� ��)
		if(dto.getPkeyword()!=null) {
			keyword = dto.getPkeyword();
			sql += getSCondition() + endSubQ;
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
		}
		return sql; 
	}
		
		
	public String searchPlace() {
		
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
		
		return null;
	}
}
	

