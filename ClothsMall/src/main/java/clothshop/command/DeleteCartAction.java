package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.CartDBBean;

public class DeleteCartAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String msg = "";
		String member_id = request.getParameter("member_id");
		String cloth_id = request.getParameter("cloth_id");
		CartDBBean clothProcess = CartDBBean.getInstance();

		// 장바구니 비우기
		if (cloth_id == null) {
			clothProcess.deleteAll(member_id);
			msg = "장바구니가 모두 비워졌습니다.";
		} else {
			clothProcess.deleteItem(cloth_id);
			msg = "장바구니 항목이 삭제되었습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("type", Integer.valueOf(1));
		return "/cart/deleteCart.jsp";
	}

}
