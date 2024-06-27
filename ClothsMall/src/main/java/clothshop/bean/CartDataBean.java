package clothshop.bean;

public class CartDataBean {
	private int cart_id;			// CART 아이디 (PK)
	private String member_id;		// 구매자 아이디
	private int cloth_id;			// 옷 아이디
	private String cloth_category;	// 옷 카테고리
	private String cloth_name;		// 옷 상품명
	private String cloth_size;		// 옷 사이즈
	private int cloth_price;		// 옷 정가(할인율 적용 전, 판매가 x)
	private String cloth_brand;		// 옷 브랜드
	private String cloth_image;		// 옷 이미지
	private int discount_rate;		// 옷 할인율
	private int quantity;			// 카트 내 옷 단일 상품 수량

	
	// GETTER, SETTER
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

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

	public String getCloth_brand() {
		return cloth_brand;
	}

	public void setCloth_brand(String cloth_brand) {
		this.cloth_brand = cloth_brand;
	}

	public String getCloth_image() {
		return cloth_image;
	}

	public void setCloth_image(String cloth_image) {
		this.cloth_image = cloth_image;
	}

	public int getDiscount_rate() {
		return discount_rate;
	}

	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
