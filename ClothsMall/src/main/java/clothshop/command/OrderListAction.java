package clothshop.command;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.BuyDBBean;
import clothshop.bean.BuyDataBean;
import clothshop.bean.ReceiptDBBean;
import clothshop.bean.ReceiptDataBean;

public class OrderListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<HashMap<String, Object>> buyLists = null;
		List<ReceiptDataBean> receiptLists=null;
		int count = 0;

		// 전체 주문목록의 수를 얻어냄
		BuyDBBean buyProcess = BuyDBBean.getInstance();
		count = buyProcess.getListCount();
		System.out.println("count"+count);
		if (count > 0) {// 주문목록이 있으면
			// 전체 주문목록을 얻어냄
			buyLists = buyProcess.getBuyList();
			System.out.println("buyLists.size"+buyLists.size());
			request.setAttribute("buyLists", buyLists);
		
			//영수증도 가져옴
			ReceiptDBBean receiptPro =ReceiptDBBean.getInstance();
			receiptLists=receiptPro.getReceiptList();
			request.setAttribute("receiptLists",receiptLists);
					
		}

		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("type", Integer.valueOf(0));
		return "/mngr/orderedProduct/orderList.jsp";
	}
}
