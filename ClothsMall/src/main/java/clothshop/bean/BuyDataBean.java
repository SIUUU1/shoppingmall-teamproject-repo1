package clothshop.bean;

import java.sql.Timestamp;

public class BuyDataBean {
	private Long buy_id;			//구매 아이디 (PK)
//	private int buy_group_id;		//구매 그룹 아이디 (한번에 어려개 살때 묶어주기 위해)
	private String member_id;		//구매자 아이디(FK) (멤버 테이블에 이름 맞추기)
	private String cloth_id;		//옷 아이디 (FK)
	private int quantity;			//수량		
	private Long receipt_id;		//영수증 아이디 (FK)
	private int cloth_price;		// 옷 정가(할인율 적용 전, 판매가 x)
	private int discount_rate;		// 옷 할인율
	
	
	public Long getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(Long buy_id) {
		this.buy_id = buy_id;
	}
//	public int getBuy_group_id() {
//		return buy_group_id;
//	}
//	public void setBuy_group_id(int buy_group_id) {
//		this.buy_group_id = buy_group_id;
//	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getCloth_id() {
		return cloth_id;
	}
	public void setCloth_id(String cloth__id) {
		this.cloth_id = cloth__id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getReceipt_id() {
		return receipt_id;
	}
	public void setReceipt_id(Long receipt_id) {
		this.receipt_id = receipt_id;
	}
	public int getCloth_price() {
		return cloth_price;
	}
	public void setCloth_price(int cloth_price) {
		this.cloth_price = cloth_price;
	}
	public int getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	
	


}
