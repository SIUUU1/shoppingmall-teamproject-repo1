package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;
import clothshop.bean.MngrDataBean;
import clothshop.bean.QnaDBBean;
import clothshop.bean.QnaDataBean;

public class ClothContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// qna 리스트
		List<QnaDataBean> qnaLists;
		String cloth_category = request.getParameter("cloth_category");
		int cloth_id = Integer.parseInt(request.getParameter("cloth_id"));

		// cloth_id 에 해당하는 상품을 얻어냄
		MngrDBBean clothProcess = MngrDBBean.getInstance();
		MngrDataBean cloth = clothProcess.getCloth(cloth_id);

		// cloth_id 에 해당하는 상품의 QnA 수를 얻어냄
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		int count = qnaProcess.getArticleCount(cloth_id);

		if (count > 0) {// QnA 가 있으면 수행
			/// cloth_id 에 해당하는 상품의 QnA 를 얻어냄
			qnaLists = qnaProcess.getArticles(count, cloth_id);
			request.setAttribute("qnaLists", qnaLists);
		}

		request.setAttribute("cloth", cloth);
		request.setAttribute("cloth_id", Integer.valueOf(cloth_id));
		request.setAttribute("cloth_category", cloth_category);
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("type", Integer.valueOf(1));
		return "/shop/clothContent.jsp";
	}
}
