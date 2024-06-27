package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//		List<BuyDataBean> buyLists = null;
//		int count = 0;
//
//		// 전체 주문목록의 수를 얻어냄
//		BuyDBBean buyProcess = BuyDBBean.getInstance();
//		count = buyProcess.getListCount();
//		if (count > 0) {// 주문목록이 있으면
//			// 전체 주문목록을 얻어냄
//			buyLists = buyProcess.getBuyList();
//			request.setAttribute("buyLists", buyLists);
//		}
//
//		request.setAttribute("count", Integer.valueOf(count));
//		request.setAttribute("type", Integer.valueOf(0));
		return "/mngr/orderedProduct/orderList.jsp";
	}
}
