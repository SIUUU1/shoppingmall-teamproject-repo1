package clothshop.command;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.BuyDataBean;
import clothshop.bean.ReceiptDBBean;
import clothshop.bean.ReceiptDataBean;
import clothshop.command.CommandAction;
import clothshop.bean.BuyDBBean;

public class BuyListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String member_id = request.getParameter("member_id");
		
		List<HashMap<String, Object>> buyLists = null;
		List<ReceiptDataBean> receiptLists=null;
		int count = 0;
		
		//해당 member_id의 구매목록의 수를 얻어냄
		BuyDBBean buyProcess = BuyDBBean.getInstance();
		count = buyProcess.getListCount(member_id);

		if(count > 0){//구매 목록이 있으면 수행
			//해당 member_id의 구매목록을 얻어냄
			buyLists = buyProcess.getBuyList(member_id);
			request.setAttribute("buyLists",buyLists);
			
			//영수증도 가져옴
			ReceiptDBBean receiptPro =ReceiptDBBean.getInstance();
			receiptLists=receiptPro.getReceiptList(member_id);
			request.setAttribute("receiptLists",receiptLists);
					
		}
		
		request.setAttribute("footer", 1);
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("type", Integer.valueOf(1));
		return "/buy/buyList.jsp";
	}
}