package clothshop.bean;

import java.sql.Timestamp;

public class MngrDataBean {
	private int cloth_id; // 옷 id
	private String cloth_category; // 옷 종류
	private String cloth_gender; // 옷 성별
	private String cloth_name; // 옷 이름
	private String cloth_size; // 옷 사이즈
	private int cloth_price; // 옷 가격
	private int cloth_count; // 옷 재고 수량
	private String cloth_brand; // 판매 브랜드
	private Timestamp reg_date; // 옷등록날짜
	private String cloth_image; // 옷 이미지
	private String cloth_content; // 옷 설명
	private int discount_rate; // 할인율

	public int getCloth_id() {
		return cloth_id;
	}

	public void setCloth_id(int cloth_id) {
		this.cloth_id = cloth_id;
	}

	public String getCloth_category() {
		return cloth_category;
	}

	public void setCloth_category(String cloth_category) {
		this.cloth_category = cloth_category;
	}

	public String getCloth_gender() {
		return cloth_gender;
	}

	public void setCloth_gender(String cloth_gender) {
		this.cloth_gender = cloth_gender;
	}

	public String getCloth_name() {
		return cloth_name;
	}

	public void setCloth_name(String cloth_name) {
		this.cloth_name = cloth_name;
	}

	public String getCloth_size() {
		return cloth_size;
	}

	public void setCloth_size(String cloth_size) {
		this.cloth_size = cloth_size;
	}

	public int getCloth_price() {
		return cloth_price;
	}

	public void setCloth_price(int cloth_price) {
		this.cloth_price = cloth_price;
	}

	public int getCloth_count() {
		return cloth_count;
	}

	public void setCloth_count(int cloth_count) {
		this.cloth_count = cloth_count;
	}

	public String getCloth_brand() {
		return cloth_brand;
	}

	public void setCloth_brand(String cloth_brand) {
		this.cloth_brand = cloth_brand;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getCloth_image() {
		return cloth_image;
	}

	public void setCloth_image(String cloth_image) {
		this.cloth_image = cloth_image;
	}

	public String getCloth_content() {
		return cloth_content;
	}

	public void setCloth_content(String cloth_content) {
		this.cloth_content = cloth_content;
	}

	public int getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}

}
