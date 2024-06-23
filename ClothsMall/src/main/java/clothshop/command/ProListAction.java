package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;
import clothshop.bean.MngrDataBean;

public class ProListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		List<MngrDataBean> clothList = null;
		int count = 0;
		String cloth_category = request.getParameter("cloth_category");
		MngrDBBean clothProcess = MngrDBBean.getInstance();

		// kind 값이 all 이면 전체 상품의 수를 얻어냄
		if (cloth_category.equals("all")) {
			count = clothProcess.getClothCount();
		} else {
			// all 이 아니면 해당 카테고리의 상품수를 얻어냄
			count = clothProcess.getCategoryCount(cloth_category);
		}

		if (count > 0) {// 상품이 있으면 수행
			// 상품목록을 얻어냄
			clothList = clothProcess.getCloths(cloth_category);
			request.setAttribute("clothList", clothList);
		}

		// 해당 뷰에서 사용할 속성
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("cloth_category", cloth_category);
		request.setAttribute("type", Integer.valueOf(1));
		return "/shop/showList.jsp";
	}
}
