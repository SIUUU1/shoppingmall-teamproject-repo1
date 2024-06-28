package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String cart_id = request.getParameter("cart_id");
		String quantity = request.getParameter("quantity");
		request.setAttribute("cart_id", cart_id);
		request.setAttribute("quantity", quantity);
		request.setAttribute("type", Integer.valueOf(1));
		return "/cart/cartUpdateForm.jsp";
	}

}
