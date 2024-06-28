package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDBBean;

public class QnaDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		int qora = Integer.parseInt(request.getParameter("qora"));

		// qna_id 에 해당하는 qna 삭제
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		int check = qnaProcess.deleteArticle(qna_id, group_id,qora);
		request.setAttribute("check", Integer.valueOf(check));
		return "/qna/qnaDeletePro.jsp";
	}
}