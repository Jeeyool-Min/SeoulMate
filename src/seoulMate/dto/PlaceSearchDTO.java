package seoulMate.dto;

import java.io.Serializable;

public class PlaceSearchDTO implements Serializable {
	private static PlaceSearchDTO instance = new PlaceSearchDTO();
	private String gu;
	private String[] page; //��� ���ŷ� �־�� �� �� ������..
	private String[] paccess;
	private String pkeyword;
	private String[] pstyle;
	private String[] pcomtype;
	private int pno;
	private String category;
	private String pname;
	private String imgUrl;
	
	public static PlaceSearchDTO getInstance() {return instance;}
	public String getGu() {	return gu;}
	public void setGu(String gu) {	this.gu = gu;	}
	public String[] getPage() {	return page; }
	public void setPage(String[] page) {this.page = page;}
	public String[] getPaccess() {return paccess;}
	public void setPaccess(String[] paccess) {this.paccess = paccess;}
	public String getPkeyword() {	return pkeyword;}
	public void setPkeyword(String pkeyword) {this.pkeyword = pkeyword;}
	public String[] getPstyle() {return pstyle;}
	public void setPstyle(String[] pstyle) {this.pstyle = pstyle;}
	public String[] getPcomtype() {	return pcomtype;}
	public void setPcomtype(String[] pcomtype) {this.pcomtype = pcomtype;}
	
	public int getPno() {return pno;}
	public void setPno(int pno) {this.pno = pno;}
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	public String getPname() {return pname;}
	public void setPname(String pname) {this.pname = pname;}
	public String getImgUrl() {return imgUrl;}
	public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}
}
