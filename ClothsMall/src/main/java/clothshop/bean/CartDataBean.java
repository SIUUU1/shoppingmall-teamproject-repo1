package clothshop.bean;

public class CartDataBean {
	private int cart_id;
	private String buyer;
	private int cloth_id;
	private String cloth_category;
	private String cloth_name;
	private String cloth_size;
	private int cloth_price;
	private String cloth_brand;
	private String cloth_image;
	private int discount_rate;
	private int quantity;

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
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
