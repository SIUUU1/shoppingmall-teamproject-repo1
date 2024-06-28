package clothshop.bean;

import java.sql.Timestamp;

public class PointDataBean {
	private Long point_id; 			// 포인트 아이디
	private String member_id; 		// 구매자 아이디
	private int point; 				// 증감된 포인트
	private String type; 			// + or -
	private int account; 			// 출금될 계좌 번호
	private String bank; 			// 출금될 계좌 은행명
	private Timestamp point_date; 	// 변동 날짜
	
	
	public Long getPoint_id() {
		return point_id;
	}
	public void setPoint_id(Long point_id) {
		this.point_id = point_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Timestamp getPoint_date() {
		return point_date;
	}
	public void setPoint_date(Timestamp point_date) {
		this.point_date = point_date;
	}
	
}
