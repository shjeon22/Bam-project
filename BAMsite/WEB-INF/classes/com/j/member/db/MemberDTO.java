package com.j.member.db;

public class MemberDTO {
	private int num; // 회원번호1
	private String id;// 아이디2
	private String pw;// 비밀번호3
	private String name;// 이름4
	private int pin1;// 주번 앞자리5
	private int pin2;// 주번 뒷자리6
	private String email;// 이메일7
	private String tel;// 전화번호8
	private int zipcode;// 우편주소9
	private String address;// 주소10
	private String address2;// 상세주소11
	private String job;// 직업12
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPin1() {
		return pin1;
	}
	public void setPin1(int pin1) {
		this.pin1 = pin1;
	}
	public int getPin2() {
		return pin2;
	}
	public void setPin2(int pin2) {
		this.pin2 = pin2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "MemberDTO [num=" + num + ", id=" + id + ", pw=" + pw + ", name=" + name + ", pin1=" + pin1 + ", pin2="
				+ pin2 + ", email=" + email + ", tel=" + tel + ", zipcode=" + zipcode + ", address=" + address
				+ ", address2=" + address2 + ", job=" + job + "]";
	}
	
	
}