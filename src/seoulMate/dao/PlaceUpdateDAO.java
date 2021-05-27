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
	//수정 시 지난 자료 불러오기
	public void loadInfo(int pno) { 
		PlaceDTO dto = PlaceDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result="오류없음";
		
		try {
			/*place_post: 글번호, 부제, 닉네임, 작성일, 좋아요, 조회수, 장소설명*/
//			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			String query = "select ptitle, pinfo, nickname from place_post where pno = " + pno;
			
			pstmt = con.prepareStatement(query);
			pstmt.setQueryTimeout(30);
			rs = pstmt.executeQuery();
			while(rs.next()) { //pinfo는 null허용 (여기서 nickname말고 실제로는 id를 가져와야함)
				String ptitle = rs.getString("ptitle");
				String pinfo = rs.getString("pinfo");
				if(pinfo==null)	pinfo =""; //해주지 않으면 null값으로 출력됨
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
			while(rs.next()) { //oprtime, fee, tel null값 허용
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
			
			
			/*place_filter : 체크박스로 배열에 여러 값을 저장하기 때문에, 배열이 존재하지 않으면(체크x) 에러 발생. 예외발생처리*/
			/*pstyle 1:자연/경치 2:휴식/힐링 3:드라이브 4:야경 5:미식 6:핫플레이스 7:체험/학습 8:역사문화 
			 * pperiod 9:당일치기 10:1박2일 11:2박3일 12:3박4일 | pcomtype 13:가족 14:친구 15:연인 16:혼자 |
			 * paccess 17:대중교통 18:도보 19:자동차 20:자전거 | pkeyword 입력값*/
			query = "select pstyle, pperiod, pcomtype, paccess, pkeyword from place_filter where pno="+ pno;
			pstmt=con.prepareStatement(query);
			rs = pstmt.executeQuery();
			//필터체크는 선택사항이므로 null값을 가질 수 있기 때문에 예외처리 필요(nullPointerException 주의)
			HashMap<String, String[]> pcheck = new HashMap(); //filter 각 name에 대한 value들의 배열을 넣어줄 해쉬맵
			

			while (rs.next()) { //검색결과는 1개만 나옴
			String pstyle = rs.getString("pstyle");
			String pperiod = rs.getString("pperiod");
			String pcomtype = rs.getString("pcomtype");
			String paccess = rs.getString("paccess");
			String pkeyword = rs.getString("pkeyword");
			
				String[] filterValues = new String[5];
				ArrayList<String> list = new ArrayList();
				for(int i=0; i<filterValues.length; i++) {
					filterValues[i] = rs.getString(i+1); // 1/2/5 | 9/11 | 13/16 | 20 |#키워드 #ㅇㅇㅇ(#없고 단순 문자열일수도.키워드는 하나의 문자열로 처리하는 게 날듯) ==> 이런식으로 입력됨
				}
				String[] filterNames = {"pstyle", "pperiod","pcomtype", "paccess", "pkeyword"}; 
				for(int i=0; i<filterNames.length; i++) {
					if (filterValues[i] != null) { // 체크값 있다면
						if (filterValues[i].contains("/")) { // 두 개 이상 선택
							for(int j=0; j<filterValues[i].split("/").length; j++) {
								list.add(filterValues[i].split("/")[j]);
							}
							String[] strArr = list.toArray(new String[0]);
							pcheck.put(filterNames[i], strArr);
							list = new ArrayList();
							
						} else { // 하나만 선택
							String[] st = {filterValues[i]};
							pcheck.put(filterNames[i], st);
						}
					} else { // null이라면(선택X)
						pstyle = "";
						pcheck.put(filterNames[i], null); //Map null값 허용함
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
	} //메소드 끝
	
	public void modify(int pno) { 
		PlaceDTO dto = PlaceDTO.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			/*place_post: 글번호, 부제, 닉네임, 작성일, 좋아요, 조회수, 장소설명*/
//			ArrayList<PlaceDTO> list = new ArrayList<PlaceDTO>();
			con = DriverManager.getConnection(D.url, D.userid, D.passwd);
			String query = "update place_post set ptitle=?, pinfo =? where pno = " +dto.getPno(); 
			//dto.getPtitle() +"', pinfo='"+dto.getPinfo()+"' where pno = "+dto.getPno();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPtitle());
			pstmt.setString(2, dto.getPinfo());
			pstmt.setQueryTimeout(30);
			pstmt.executeUpdate();
			System.out.println("place_post 수정완료");
			
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
			System.out.println("place_basicinfo 수정완료");
			
			/*place_locinfo*/
			query = "update place_locinfo set address = '"+dto.getAddress() + "' where pno = " + pno;
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			System.out.println("place_locinfo 수정완료");

			
			/*place_filter : 체크박스로 배열에 여러 값을 저장하기 때문에, 배열이 존재하지 않으면(체크x) 에러 발생. 예외발생처리*/
			/*pstyle 1:자연/경치 2:휴식/힐링 3:드라이브 4:야경 5:미식 6:핫플레이스 7:체험/학습 8:역사문화 
			 * pperiod 9:당일치기 10:1박2일 11:2박3일 12:3박4일 | pcomtype 13:가족 14:친구 15:연인 16:혼자 |
			 * paccess 17:대중교통 18:도보 19:자동차 20:자전거 | pkeyword 입력값*/
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
			pstmt.setString(1, access); //""로 입력할 경우 DB엔 null로 들어감
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
			System.out.println("완료");
			
		} //end of try
		catch (Exception e) { e.printStackTrace(); }
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (final SQLException e) {e.printStackTrace();}
		}
	} //submit 끝
	
}
