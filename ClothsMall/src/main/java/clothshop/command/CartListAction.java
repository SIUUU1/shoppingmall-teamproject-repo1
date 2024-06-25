package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.CartDBBean;
import clothshop.bean.CartDataBean;

public class CartListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String member_id = request.getParameter("member_id");
		List<CartDataBean> cartLists = null;
		int quantity = 0;
		CartDBBean clothProcess = CartDBBean.getInstance();
		
		quantity = clothProcess.getListCount(member_id);
		if (quantity > 0) {
			cartLists = clothProcess.getCart(member_id, quantity);
			request.setAttribute("cartLists", cartLists);
		}
		request.setAttribute("quantity", Integer.valueOf(quantity));
		request.setAttribute("type", Integer.valueOf(1));
		return "/cart/cartList.jsp";
	}

}
