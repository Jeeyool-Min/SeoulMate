package seoulMate.dto;

import java.io.Serializable;

public class PlaceSearchDTO implements Serializable {
	private static PlaceSearchDTO instance = new PlaceSearchDTO();
	private String gu;
	private String[] page; //사실 열거로 넣어야 할 것 같은데..
	
	private String[] paccess;
	private String pkeyword;
	private String[] pstyle;
	private String[] pcomtype;
	private int pno;
	private String category;
	private String pname;
	private String imgUrl;
	private String age;
	private String style;
	private String comtype;
	private String keyword;
	private String access;
	private int rsSize;
	
	
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
	
	public PlaceSearchDTO(String gu, String age, String access, String keyword, String style,
			String comtype, int pno, String category, String pname, String imgUrl, int rsSize) {
		this.gu = gu;
		this.age = age;
		this.access = access;
		this.keyword = keyword;
		this.style = style;
		this.comtype = comtype;
		this.pno = pno;
		this.category = category;
		this.pname = pname;
		this.imgUrl = imgUrl;
		this.rsSize = rsSize;
	}
	
	
	
	public int getRsSize() {
		return rsSize;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getComtype() {
		return comtype;
	}
	public void setComtype(String comtype) {
		this.comtype = comtype;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public PlaceSearchDTO() {	}
	
	
}
