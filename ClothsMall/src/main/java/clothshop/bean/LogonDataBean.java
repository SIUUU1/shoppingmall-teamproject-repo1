package clothshop.bean;

import java.sql.Timestamp;

public class LogonDataBean {
	private String member_id; // 회원 아이디
	private String member_passwd; // 회원 비밀번호
	private String member_name; // 회원 이름
	private Timestamp reg_date; // 회원 가입 날짜
	private String member_address; // 주소
	private String member_postal_code; // 우편번호
	private String member_detailed_address; // 상세주소
	private String member_tel; // 전화번호
	private String member_gender; // 성별
	private String member_grade; // 고객 등급
	private int mileage; // 적립금
	private int point; // 충전머니

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_passwd() {
		return member_passwd;
	}

	public void setMember_passwd(String member_passwd) {
		this.member_passwd = member_passwd;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public String getMember_postal_code() {
		return member_postal_code;
	}

	public void setMember_postal_code(String member_postal_code) {
		this.member_postal_code = member_postal_code;
	}

	public String getMember_detailed_address() {
		return member_detailed_address;
	}

	public void setMember_detailed_address(String member_detailed_address) {
		this.member_detailed_address = member_detailed_address;
	}

	public String getMember_tel() {
		return member_tel;
	}

	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}

	public String getMember_grade() {
		return member_grade;
	}

	public void setMember_grade(String member_grade) {
		this.member_grade = member_grade;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}