package seoulMate.dto;

import java.io.Serializable;

public class PlaceMainDTO implements Serializable {
	private int rank;
	private int pview;
	private int pno;
	private String pname;
	private String ptitle;
	private String category;
	private String imgUrl;
	private String address;
	
	//private static PlaceMainDTO instance = new PlaceMainDTO();
	
	public int getRank() {return rank;}
	public void setRank(int rank) {this.rank = rank;}
	public int getPview() {return pview;}
	public void setPview(int pview) {this.pview = pview;}
	public int getPno() {return pno;}
	public void setPno(int pno) {this.pno = pno;}
	public String getPname() {return pname;}
	public void setPname(String pname) {this.pname = pname;}
	public String getPtitle() {return ptitle;}
	public void setPtitle(String ptitle) {this.ptitle = ptitle;}
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	public String getImgUrl() {return imgUrl;}
	public void setImgUrl(String imgUrl) {this.imgUrl = imgUrl;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	
	public PlaceMainDTO(int rank, int pview, int pno, String pname, String ptitle, String category, String imgUrl,
			String address) {
		this.rank = rank;	this.pview = pview; 	this.pno = pno;		this.pname = pname;
		this.ptitle = ptitle;		this.category = category;		this.imgUrl = imgUrl;		this.address = address;
	}
	
	
	//public static PlaceMainDTO getInstance() {return instance;}
	//public static void setInstance(PlaceMainDTO instance) {	PlaceMainDTO.instance = instance;}
	/*
	String sql = "select rank() over(order by p.pview desc, p.pno desc) as rank,"
			+ " p.pview, p.pno, b.pname, p.ptitle, b.category, b.imgUrl from place_post p "
			+ "INNER JOIN place_basicinfo b ON p.pno = b.pno INNER JOIN place_locinfo l ON p.pno=l.pno";*/
}