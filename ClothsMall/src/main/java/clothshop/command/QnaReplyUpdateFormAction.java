package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDataBean;
import clothshop.bean.QnaDBBean;

public class QnaReplyUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8");
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		//주어진 qna_id에 해당하는 수정할 qna답변을 가져옴 
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		QnaDataBean qna  =  qnaProcess.updateGetArticle(qna_id);
		
		request.setAttribute("qna", qna);
		request.setAttribute("qna_id", Integer.valueOf(qna_id));
		request.setAttribute("type", Integer.valueOf(0));
		return "/mngr/qnaProcess/qnaReplyUpdateForm.jsp";
	}
}