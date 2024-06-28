package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.CartDBBean;
import clothshop.bean.CartDataBean;

public class InsertCartAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String member_id = request.getParameter("member_id");
		int cloth_id = Integer.parseInt(request.getParameter("cloth_id"));
		String cloth_category = request.getParameter("cloth_category");
		String cloth_name = request.getParameter("cloth_name");
		String cloth_size = request.getParameter("cloth_size");
		int cloth_price = (int) Float.parseFloat(request.getParameter("cloth_price"));
		String cloth_brand = request.getParameter("cloth_brand");
		String cloth_image = request.getParameter("cloth_image");
		int discount_rate = Integer.parseInt(request.getParameter("discount_rate"));
		byte quantity = Byte.parseByte(request.getParameter("quantity"));
		// 카트 객체 생성
		CartDataBean cart = new CartDataBean();
		
		cart.setMember_id(member_id);
		cart.setCloth_id(cloth_id);
		cart.setCloth_category(cloth_category);
		cart.setCloth_name(cloth_name);
		cart.setCloth_size(cloth_size);
		cart.setCloth_price(cloth_price);
		cart.setCloth_brand(cloth_brand);
		cart.setCloth_image(cloth_image);
		cart.setDiscount_rate(discount_rate);
		cart.setQuantity(quantity);
		// 장바구니에 추가
		CartDBBean clothProcess = CartDBBean.getInstance();
		clothProcess.insertCart(cart);
		return "/cart/insertCart.jsp";
	}

}
