package seoulMate.dto;

import java.io.Serializable;
import java.sql.Date;

import seoulMate.dao.MemberDAO;

//자바빈즈로 만들기 규칙
public class MemberDTO implements Serializable { //1.implement
	private static MemberDTO instance = new MemberDTO();
	private String userId; //3.전부 private선언
	private String name;
	private String password;
	private String nickname;
	private String email;
	private Date birthdate;
	
	public MemberDTO() {} //2.기본생성자

	//4.Setter, Getter
	public String getUserId() {return userId;}
	public void setUserId(String userId) {this.userId = userId;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public String getNickname() {return nickname;}
	public void setNickname(String nickname) {this.nickname = nickname;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public Date getBirthdate() {return birthdate;}
	public void setBirthdate(Date birthdate) {this.birthdate = birthdate;}

	public static MemberDTO getInstance() {return instance;}
	
}

/*create table member(userId varchar2(15) constraint pk_member primary key,
                      name varchar2(5),
                      password varchar2(20) not null,
              		  nickname varchar2(15) unique, (not null)
              		  email varchar2(20) unique, (not null)
                      birthdate date) (not null) ;*/ 