package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.BuyDBBean;
import clothshop.bean.CartDBBean;
import clothshop.bean.CartDataBean;
import clothshop.bean.PointDBBean;
import clothshop.bean.ReceiptDBBean;
import clothshop.bean.ReceiptDataBean;
import clothshop.command.CommandAction;

public class BuyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String member_id = request.getParameter("member_id");
		
		
		
		
		//구매 처리에 필요한 정보를 파라미터에서 얻어냄
		ReceiptDataBean receipt = new ReceiptDataBean();
		receipt.setPrice((int)Double.parseDouble(request.getParameter("price")));

		double gradeDiscount = Double.parseDouble(request.getParameter("gadeDiscount"));
		receipt.setGrade_discount((int) gradeDiscount);
		receipt.setUse_mileage(Integer.parseInt(request.getParameter("useMileage")));
		double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
		receipt.setTotal_price((int) totalPrice);

		receipt.setMember_id(member_id);
		receipt.setDelivery_name(request.getParameter("member_name"));
		System.out.println("request.getParameter(\"member_name\")"+request.getParameter("member_name"));
		receipt.setDelivery_address(request.getParameter("member_address"));
		receipt.setDelivery_postal_code(request.getParameter("member_postal_code"));
		receipt.setDelivery_detailed_address(request.getParameter("member_detailed_address"));
		receipt.setDelivery_tel(request.getParameter("member_tel"));
		
		int count = 0;
		
		//영수증 테이블에 추가
		ReceiptDBBean receiptPro =ReceiptDBBean.getInstance();
		receiptPro.regReceipt(receipt);

		
		
		//구매처리를 위해 장바구니의 목록을 얻어냄
		CartDBBean cartProcess = CartDBBean.getInstance();
		count = cartProcess.getListCount(member_id);
		List<CartDataBean> cartLists = cartProcess.getCart(member_id,count);
		
		
		
		//포인트 사용한거 테이블에 추가 (member에 point는 트리거로 수정됨)
		PointDBBean pointPro = PointDBBean.getInstance();
		String usePointstr=request.getParameter("usePoint");
		int usePoint=0;
		if(usePointstr.length()>1) {
			usePoint=Integer.parseInt(usePointstr);
		}
		pointPro.decreasePoint(usePoint,member_id);
		
		BuyDBBean buyProcess = BuyDBBean.getInstance();
		buyProcess.insertBuy(cartLists,member_id);

		request.setAttribute("orderStus", "주문완료");
		request.setAttribute("type", Integer.valueOf(1));
		return "/buy/buyPro.jsp";
	}
}