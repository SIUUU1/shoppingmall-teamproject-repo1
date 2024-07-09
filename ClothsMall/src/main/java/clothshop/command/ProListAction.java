package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;
import clothshop.bean.MngrDataBean;

public class ProListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		List<MngrDataBean> clothList = null;
		int count = 0;
		String cloth_category = request.getParameter("cloth_category");
		String search = request.getParameter("search");

		MngrDBBean clothProcess = MngrDBBean.getInstance();

		// 검색어리스트 출력
		if (search != null) {
			count = clothProcess.getSearchCount(search);
			if (count != 0) {
				clothList = clothProcess.getSearchCloth(search);
				request.setAttribute("cloth_category", "search");
			}
			request.setAttribute("clothList", clothList);
		} else {//카테고리 해당 리스트 출력

			// cloth_category 값이 all 이면 전체 상품의 수를 얻어냄
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
				request.setAttribute("cloth_category", cloth_category);
			}

		}

		// 해당 뷰에서 사용할 속성
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("type", Integer.valueOf(1));
		request.setAttribute("footer", 1);
		return "/shop/showList.jsp";
	}
}
