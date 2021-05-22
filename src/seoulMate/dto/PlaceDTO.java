package seoulMate.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class PlaceDTO implements Serializable {
	/*place_post : 장소 게시글 기본정보 테이블*/
	private int pno; 
	private String ptitle; //게시물 부제 :subtitle
	private String nickname; 
	private Date writtenDate;
	private int plikeno;
	private int pview;
	private String pinfo;
	
	
	/*장소 기본정보 테이블 pname, category, oprtime, offdays, fee, imgUrl*/
	private HashMap<String, String> place;
	
	/*이미지 url테이블*/
//	private ArrayList<String> imgUrl = new ArrayList(5); 
	
	/*위치정보*/
	private String address;
	private String locUrl;
	
	/*작성자 체크 필터정보 paccess, pcomtype, pstyle, page[10대 50+], pkeyword*/
	private HashMap<String, String[]> pcheck;

	private static PlaceDTO instance = new PlaceDTO();
	
	public PlaceDTO(){}
	public static PlaceDTO getInstance() {return instance;}
	public int getPno() {return pno;}
	public void setPno(int pno) {this.pno = pno;}
	public String getPtitle() {return ptitle;}
	public void setPtitle(String ptitle) {this.ptitle = ptitle;}
	public String getNickname() {return nickname;}
	public void setNickname(String nickname) {this.nickname = nickname;}
	public Date getWrittenDate() {return writtenDate;}
	public void setWrittenDate(Date writtenDate) {this.writtenDate = writtenDate;}
	public int getPlikeno() {return plikeno;}
	public void setPlikeno(int plikeno) {this.plikeno = plikeno;}
	public String getPinfo() {return pinfo;}
	public void setPinfo(String pinfo) {this.pinfo = pinfo;}
	public HashMap<String, String> getPlace() {	return place;}
	public void setPlace(HashMap<String, String> place) {this.place = place;}
	//public ArrayList<String> getImgUrl() {return imgUrl;}
	//public void setImgUrl(ArrayList<String> imgUrl) {this.imgUrl = imgUrl;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public String getLocUrl() {return locUrl;}
	public void setLocUrl(String locUrl) {this.locUrl = locUrl;}
	public HashMap<String, String[]> getPcheck() {return pcheck;}
	public void setPcheck(HashMap<String, String[]> pcheck) {	this.pcheck = pcheck;}
	public int getPview() {return pview;}
	public void setPview() {this.pview = pview;}
}
/*place_post(pno number constraint pk_place_post primary key, 
  			ptitle varchar2(60), //한글 최대 20자, 장소명이 아니라 글 부제
            nickname varchar2(30) unique references member, //한글 최대 10자
            pwrittenDate date,
            plikeno number,
            pview number,
            pinfo varchar2(1200)); //한글 최대 400자*/
/*
	create table place_basicinfo(pno number constraint fk_place_basicinfo references place_post,
            pname varchar2(45) constraint pk_place_basicinfo primary key, //한글로 최대 15자
            category varchar2(20), 
            oprtime varchar2(150), //한글로 최대 50자
            offdays varchar2(50), //한글로 최대 16자
            fee varchar2(150),
            imgUrl varchar2(45); //한글로 최대 15자*/

/*create table place_locinfo(pno number constraint fk_place_locinfo references place_post,
                             address varchar2(120) constraint pk_place_locinfo primary key, //한글 최대 40자
                             url varchar2(40)); //영문+숫자 40자*/

/*
create table place_filter(pno number constraint fk_place_filter references place_post,
                          paccess varchar2(15), 
                          pcomtype varchar2(6),
                          pstyle varchar2(10),
                          page varchar2(10),
                          pkeyword varchar2(50));
 */
/*create table place_image(pno number references place_post,
        url1 varchar2(40),
        url2 varchar2(40),
        url3 varchar2(40),
        url4 varchar2(40),
        url5 varchar2(40));*/
