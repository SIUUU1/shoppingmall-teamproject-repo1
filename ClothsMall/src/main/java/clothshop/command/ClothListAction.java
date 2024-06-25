package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;
import clothshop.bean.MngrDataBean;

public class ClothListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<MngrDataBean> clothList = null;
		String cloth_category = request.getParameter("cloth_category");
		int count = 0;
		// DB 연동 - 전체 상품의 수를 얻어냄
		MngrDBBean clothProcess = MngrDBBean.getInstance();
		count = clothProcess.getClothCount();
		if (count > 0) {// 상품이 있으면 수행
			// 상품전체를 테이블에서 얻어내서 bookList 에 저장
			clothList = clothProcess.getCloths(cloth_category);
			// bookList 를 뷰에서 사용할 수 있도록 request 속성에 저장
			request.setAttribute("clothList", clothList);
		}
		// 뷰에서 사용할 속성
		request.setAttribute("count", count);
		request.setAttribute("cloth_category", cloth_category);
		request.setAttribute("type", 0);
		return "/mngr/productProcess/clothList.jsp";
	}
}
