package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDBBean;
import clothshop.bean.QnaDataBean;

public class QnaReplyUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int qna_id =  Integer.parseInt(request.getParameter("qna_id"));
		String qna_content = request.getParameter("qna_content");
		
		//QnA 답변글 수정 정보 설정
		QnaDataBean qna = new QnaDataBean();
		qna.setQna_id(qna_id);
		qna.setQna_content(qna_content);
		
		//QnA 답변글 수정 처리
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		int check = qnaProcess.updateArticle(qna);
		
		request.setAttribute("check", Integer.valueOf(check));
		return "/mngr/qnaProcess/qnaReplyUpdatePro.jsp";
	}
}
