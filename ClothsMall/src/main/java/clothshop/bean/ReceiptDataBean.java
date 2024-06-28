package clothshop.bean;

import java.sql.Timestamp;

public class ReceiptDataBean {
	private int recipt_id;
	private int price;
	private int grade_discount;
	private Timestamp buy_date;
	private int use_mileage;
	private int total_price;
	
	private String member_id;
	private String delivery_name;
	private String delivery_address;
	private String delivery_postal_code;
	private String delivery_detailed_address;
	private String delivery_tel;
	
	private String state;

	public int getRecipt_id() {
		return recipt_id;
	}

	public void setRecipt_id(int recipt_id) {
		this.recipt_id = recipt_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getGrade_discount() {
		return grade_discount;
	}

	public void setGrade_discount(int grade_discount) {
		this.grade_discount = grade_discount;
	}

	public Timestamp getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(Timestamp buy_date) {
		this.buy_date = buy_date;
	}

	public int getUse_mileage() {
		return use_mileage;
	}

	public void setUse_mileage(int use_mileage) {
		this.use_mileage = use_mileage;
	}

	public int getTotal_price() {
		return total_price;
	}

	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}

	public String getDelivery_name() {
		return delivery_name;
	}

	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}

	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}

	public String getDelivery_postal_code() {
		return delivery_postal_code;
	}

	public void setDelivery_postal_code(String delivery_postal_code) {
		this.delivery_postal_code = delivery_postal_code;
	}

	public String getDelivery_detailed_address() {
		return delivery_detailed_address;
	}

	public void setDelivery_detailed_address(String delivery_detailed_address) {
		this.delivery_detailed_address = delivery_detailed_address;
	}

	public String getDelivery_tel() {
		return delivery_tel;
	}

	public void setDelivery_tel(String delivery_tel) {
		this.delivery_tel = delivery_tel;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
	

}
