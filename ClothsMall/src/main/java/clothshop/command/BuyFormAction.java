package clothshop.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import clothshop.bean.LogonDataBean;
import clothshop.command.CommandAction;
import clothshop.bean.LogonDBBean;
import clothshop.bean.CartDataBean;
import clothshop.bean.CartDBBean;
import clothshop.bean.BuyDBBean;

public class BuyFormAction implements CommandAction {
	@Override
	public String requestPro(HttpServletRequest request,

			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String member_id = request.getParameter("member_id");
		List<CartDataBean> cartLists = null;
		List<String> accountLists = null;
		LogonDataBean member = null;
		int count = 0;
		int discount=0;
//해당 member_id 의 장바구니 목록의 수를 얻어냄
		CartDBBean bookProcess = CartDBBean.getInstance();

		count = bookProcess.getListCount(member_id);
		if (count > 0) {// 장바구니 목록이 있으면 수행
//구매에 필요한 해당 member_id 의 장바구니 목록을 얻어냄
			cartLists = bookProcess.getCart(member_id, count);
			request.setAttribute("cartLists", cartLists);
		}
//구매에 필요한 member_id 의 정보를 얻어냄
		LogonDBBean memberProcess = LogonDBBean.getInstance();
		member = memberProcess.getMember(member_id);
		request.setAttribute("member", member);
		
//회원 등급에 따른 할인율 알아냄
		discount=memberProcess.getDiscount(member_id);
		request.setAttribute("discount", discount);

		request.setAttribute("type", Integer.valueOf(1));
		return "/buy/buyForm.jsp";
	}
}