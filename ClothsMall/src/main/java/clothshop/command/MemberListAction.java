package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import clothshop.bean.LogonDataBean;
import clothshop.bean.LogonDBBean;
import clothshop.bean.MemberDBBean;

public class MemberListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
request.setCharacterEncoding("utf-8");
		
		String member_id = request.getParameter("member_id");
		List<LogonDataBean> memberLists = null;
		int count = 0;
		MemberDBBean process = MemberDBBean.getInstance();
				
		count = process
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
