package clothshop.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDBBean;
import clothshop.bean.QnaDataBean;

public class QnaReplyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8");
		
        //상품Qna 답변글 관련내용
        int qna_id =  Integer.parseInt(request.getParameter("qna_id"));
        int cloth_id =  Integer.parseInt(request.getParameter("cloth_id"));
        String qna_writer =  request.getParameter("qna_writer");
		String cloth_name =  request.getParameter("cloth_name");
		String qna_content = ">>[답변]: "+request.getParameter("qna_content");
		Byte qora =  Byte.parseByte(request.getParameter("qora"));
		byte reply = 1;//답변여부- 답변함
		
		//상품Qna 답변글 저장을 위한 정보 설정
		QnaDataBean qna = new QnaDataBean();
		qna.setQna_id(qna_id);
		qna.setCloth_id(cloth_id);
		qna.setCloth_name(cloth_name);
		qna.setQna_content(qna_content);
        qna.setQna_writer(qna_writer);
        qna.setGroup_id(qna_id);
        qna.setReply(reply);
        qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(qora);
		
		//DB작업 - 테이블에 상품Qna 답변글 추가
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		int check = qnaProcess.insertArticle(qna, qna_id);
		
		request.setAttribute("check", check);
		return "/mngr/qnaProcess/qnaReplyPro.jsp";
	}

}
