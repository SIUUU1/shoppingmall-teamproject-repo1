package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.CartDBBean;

public class CartUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		byte quantity = Byte.parseByte(request.getParameter("quantity"));
		
		CartDBBean clothProcess = CartDBBean.getInstance();
		clothProcess.updateCount(cart_id, quantity);
		request.setAttribute("type", Integer.valueOf(1));
		return "/cart/cartUpdatePro.jsp";
	}

}
