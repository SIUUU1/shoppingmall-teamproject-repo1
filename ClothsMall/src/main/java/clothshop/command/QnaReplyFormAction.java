package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDBBean;
import clothshop.bean.QnaDataBean;

public class QnaReplyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		//qna_id에 해당하는 QnA를 가져옴
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		QnaDataBean qna  =  qnaProcess.updateGetArticle(qna_id);
		
		//QnA답변에 필요한 정보를 얻어냄
		int cloth_id = qna.getCloth_id();
		String cloth_name = qna.getCloth_name();
		String qna_content = qna.getQna_content();
		byte qora = 2;//답변글
		
		request.setAttribute("qna_id", qna_id);
		request.setAttribute("cloth_id", cloth_id);
		request.setAttribute("cloth_name", cloth_name);
		request.setAttribute("qna_content", qna_content);
		request.setAttribute("qora", qora);
		request.setAttribute("type", 0);
		return "/mngr/qnaProcess/qnaReplyForm.jsp";
	}
}