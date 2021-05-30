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
		String query = "select s.pno, s.category, s.address, s.pname, s.imgUrl, f.page, f.paccess, f.pkeyword, f.pstyle, f.pcomtype from "; //기본쿼리
		String subQ ="(select p.pno, category, address, pname, imgUrl from place_post p "; //서브쿼리 시작
		return query+subQ;
	}
	
	//서브쿼리(필수요건 : 키워드, 지역, 여행목적1~8) - 모두 만족시키는 결과 가져오기
	
	
	public ArrayList<PlaceSearchDTO> makequery(PlaceSearchDTO dto) {
		String keyword;
		String join1 = "Inner Join place_locinfo l on p.pno = l.pno and (l.address like '%" +dto.getGu() + "%') "; //지역 선택
		String join2 = "Inner Join place_basicInfo b on p.pno = b.pno "; //imgUrl 가지고 오기 위해 조인
		String join3 = "Inner Join place_filter f on p.pno = f.pno ";
		String join3Start = "and (f.pstyle like '%";
		String join3Add = "or f.pstyle like '%";
		//pstyle의 체크된 개수만큼 반복한 후 rp로 닫아줘야 함.
		String endSubQ = ") s Inner Join place_filter f ";
		
		//기본쿼리 조건절
		String condition = "on s.pno = f.pno and (";
		int ctNo =0;
		String condition2 = "f.pcomtype like '%"; //pcomtype :13~16
		
		int acNo=0;
		String condition3 = "f.paccess like '%"; //paccess :18~21
		
		int ageNo=0;
		String condition4 = "f.page like '%"; //page : 9:10대|10:2030|11:4050|12:60+
		
		//정렬 및 쿼리 닫기
		String order = ") order by case when "; //+condition2 or 3 or 4 호출해야
		int fcnt = 0; //pstyle, pkeyword 제외한 필터 체크수 : 0~3가질 수 있음.
		String when ="when ";
		String es = "else "; //fcnt+1
		String end = "end'";
		
		//쿼리만들기 시작
		String sql = startQuery();
		
		//서브쿼리 - from
		sql +=join1 +join2 +join3;
		keyword = dto.getPkeyword();
		String sCondition = "where p.pinfo like '%" +keyword+ "%' or p.ptitle like '%" +keyword+ "%' or f.pkeyword like '%" +keyword+ "%'"; //키워드 만족
		//join3
		int stNo=0; //tripStyle 체크 수
		if(dto.getPstyle()!=null){ //여행 목적을 체크했을 때 
			stNo=dto.getPstyle().length;
			sql +=join3Start + dto.getPstyle()[0] +strEnd;
			for(int i=1; i<stNo; i++) {	sql +=join3Add + dto.getPstyle()[i] + strEnd;}
			sql+=rp;
		}
		
		//서브쿼리 - condition 추가(키워드가 입력되었을 때)
		if(dto.getPkeyword()!=null) {
			keyword = dto.getPkeyword();
			sql += sCondition + endSubQ;
		}
		
		
		//본쿼리 조건절 (필터가 체크되어 있을 때)
		if(dto.getPcomtype()!=null) {ctNo=dto.getPcomtype().length; fcnt++;}
		if(dto.getPaccess()!=null) {acNo=dto.getPaccess().length; fcnt++;}
		if(dto.getPage()!=null) {ageNo=dto.getPage().length; fcnt++;}
		
		if (fcnt != 0) { // 필터가 하나라도 체크되어 있다면 (pstyle 제외)
			sql += condition;
			if (ctNo != 0) { //동반유형이 체크되어있다면
				for (int i = 0; i < ctNo; i++) {
					sql += condition2 + dto.getPcomtype()[i] + strEnd;
					order += condition2 + dto.getPcomtype()[i] + strEnd;
					if ((i + 1) != ctNo) {sql += or;	order += or;}
				}
				order += "then 0 ";
			}
			if (acNo != 0) { //접근수단이 체크되어있다면
				if (ctNo != 0) {sql += or; order += when;}
				for (int i = 0; i < acNo; i++) {
					sql += condition3 + dto.getPaccess()[i] + strEnd;
					order += condition3 + dto.getPaccess()[i] + strEnd;
					if ((i + 1) != acNo) {	sql += or;	order += or;}
				}
				order += "then 1 ";
			}
			if (ageNo != 0) { //연령대가 체크되어있다면
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
				String gu = address.substring(3,address.indexOf('구')+2);
				String pname = rs.getString("pname");
				String imgUrl = rs.getString("imgUrl");
				//page : 9:10대|10:2030|11:4050|12:60+ (변환 필요없음)
				String page = "# 추천연령 : " + rs.getString("page");
				
				//paccess :17~20 17 대중교통 18 택시 19 자동차 20 자전거
				String paccess =rs.getString("paccess");
				if(paccess!=null) {
					
				if(paccess.contains("/")) paccess = paccess.replaceAll("/", " | ");
				if(paccess.contains("17")) paccess = paccess.replace("17", "대중교통");
				if(paccess.contains("18")) paccess = paccess.replace("18", "택시"); 
				if(paccess.contains("19")) paccess = paccess.replace("19", "자동차"); 
				if(paccess.contains("20")) paccess = paccess.replace("20", "자전거");
				paccess ="# 접근성 : "+paccess; 
				} else {
					paccess="";
				}
				
				String pkeyword = rs.getString("pkeyword");
				if(pkeyword!=null) {
					if(!pkeyword.contains("#")) pkeyword = "#" + pkeyword;
				}else {
					pkeyword="";
				}
				
				//pstyle:1~8 1 자연/경치 2 휴식/힐링 3 드라이브 4 야경 5미식 6 핫플레이스 7 체험/학습 8 역사/문화
				String pstyle = rs.getString("pstyle");
				if(pstyle!=null) {
					if(pstyle.contains("/")) pstyle = pstyle.replaceAll("/", " | ");
					if(pstyle.contains("1")) pstyle = pstyle.replace("1", "자연/경치");
					if(pstyle.contains("2")) pstyle = pstyle.replace("2", "휴식/힐링");
					if(pstyle.contains("3")) pstyle = pstyle.replace("3", "드라이브");
					if(pstyle.contains("4")) pstyle = pstyle.replace("4", "야경");
					if(pstyle.contains("5")) pstyle = pstyle.replace("5", "미식");
					if(pstyle.contains("6")) pstyle = pstyle.replace("6", "핫플레이스");
					if(pstyle.contains("7")) pstyle = pstyle.replace("7", "체험/학습");
					if(pstyle.contains("8")) pstyle = pstyle.replace("8", "역사/문화");
					pstyle = "# "+pstyle;
				} else {
					pstyle ="";
				}
				
				//pcomtype :13~16 13 가족 14 친구 15 연인 16 혼자
				String pcomtype = rs.getString("pcomtype");
				if(pcomtype!=null) {
					if(pcomtype.contains("/")) pcomtype = pcomtype.replaceAll("/", " | ");
					if(pcomtype.contains("13")) pcomtype =  pcomtype.replace("13", "가족");
					if(pcomtype.contains("14")) pcomtype = pcomtype.replace("14", "친구");
					if(pcomtype.contains("15")) pcomtype = pcomtype.replace("15", "연인");
					if(pcomtype.contains("16")) pcomtype = pcomtype.replace("16", "혼자");
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
		    Inner Join place_locinfo l on p.pno = l.pno and (l.address like '%서울%') 
		    Inner Join place_basicInfo b on p.pno = b.pno 
		    Inner Join place_filter f on p.pno = f.pno and (f.pstyle like '%2%' or f.pstyle like '%8%' or f.pstyle like '%4%') //여행목적
		    where p.pinfo like '%다%' or p.ptitle like '%가%' or b.pname like '%가%' or f.pkeyword like '%빨미%'  //키워드
		    ) s, place_filter f
		where s.pno = f.pno 
		        and (f.pcomtype like '%16%' or f.pcomtype like '%14%'
		            or f.paccess like '%17%'
		            or f.page='30')
		    order by case when f.pcomtype like '%15%' then 0 when f.paccess like '%18%' then 1 when f.page='30' then 0.5 else 3 end;
		 */  
}