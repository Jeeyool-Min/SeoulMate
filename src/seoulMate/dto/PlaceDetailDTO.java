package seoulMate.dto;

import java.io.Serializable;

public class PlaceDetailDTO implements Serializable{
	private String ptitle;
	private int pview;
	private int plikeno;
	private String pname;
	private String imgUrl;
	private String pinfo;
	private String category;
	private String offdays;
	private String tel;
	private String oprtime;
	private String fee;
	private String address;
	private String gu;

public void setPtitle(String ptitle) { this.ptitle = ptitle; }
public void setPview(int pview) { this.pview = pview; }
public void setPlikeno(int plikeno) { this.plikeno = plikeno;}
public void setPname(String pname) {this.pname = pname; }
public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl;}
public void setPinfo(String pinfo) {this.pinfo = pinfo;}
public void setCategory(String category) {this.category = category;}
public void setOffdays(String offdays) {this.offdays = offdays;}
public void setTel(String tel) {this.tel = tel;}
public void setOprtime(String oprtime) {this.oprtime = oprtime;}
public void setFee(String fee) {this.fee = fee;}
public void setAddress(String address) {this.address = address;}
public void setGu(String gu) {	this.gu = gu;}


public String getGu() {	return gu;}
public String getPtitle() {	return ptitle;	}
public int getPview() {	return pview;	}
public int getPlikeno() {return plikeno;}
public String getPname() {	return pname;}
public String getImgUrl() {	return imgUrl;}
public String getPinfo() {return pinfo;}
public String getCategory() {return category;}
public String getOffdays() {return offdays;}
public String getTel() {return tel;}
public String getOprtime() {return oprtime;}
public String getFee() {return fee;}
public String getAddress() {return address;}



public PlaceDetailDTO(String ptitle, int pview, int plikeno, String pname, String imgUrl, String pinfo, String category,
		String offdays, String tel, String oprtime, String fee, String address, String gu) {
	this.ptitle = ptitle;	this.pview = pview;	this.plikeno = plikeno;	this.pname = pname;
	this.imgUrl = imgUrl;	this.pinfo = pinfo;	this.category = category;	this.offdays = offdays;
	this.tel = tel;		this.oprtime = oprtime;	this.fee = fee;	this.address = address;	this.gu = gu;
}

/*
String query="select ptitle, pview, plikeno, pname, imgurl, pinfo, category, offdays, tel, oprtime, fee, address"
		+ "from place_post p INNER JOIN place_basicinfo b ON p.pno = b.pno "
		+ "INNER JOIN place_locinfo l ON l.pno = p.pno where p.pno=34";*/
}