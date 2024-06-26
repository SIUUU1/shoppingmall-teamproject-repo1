package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDataBean;
import clothshop.bean.QnaDBBean;

public class QnaUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		String cloth_category = request.getParameter("cloth_category");

		//수정할 qna를 테이블에서 가져옴
		QnaDBBean qnaProcess = QnaDBBean.getInstance(); 
		QnaDataBean qna  =  qnaProcess.updateGetArticle(qna_id);
		
		request.setAttribute("qna", qna);
		request.setAttribute("qna_id", Integer.valueOf(qna_id));
		request.setAttribute("cloth_category", cloth_category);
		request.setAttribute("type", Integer.valueOf(1));
		return "/qna/qnaUpdateForm.jsp";
	}
}