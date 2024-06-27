package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDBBean;

public class QnaReplyDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		//DB 연동 - qna_id 에 해당하는 qna 및 답변 삭제
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		qnaProcess.deleteArticle(qna_id);
		return "/mngr/productProcess/qnaDeletePro.jsp";
	}
 
}
