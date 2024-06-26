package clothshop.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.QnaDataBean;
import clothshop.bean.QnaDBBean;

public class QnaProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		//폼에서 입력 후 넘어온 qna 내용
		String qna_writer =  request.getParameter("qna_writer");
		String cloth_name =  request.getParameter("cloth_name");
		String qna_content =  request.getParameter("qna_content");
		int cloth_id =  Integer.parseInt(request.getParameter("cloth_id"));
		Byte qora =  Byte.parseByte(request.getParameter("qora"));
		byte reply = 0; //답변여부 - 미답변
		
		//qna를 추가하기 위한 정보작성
		QnaDataBean qna = new QnaDataBean();
		qna.setCloth_id(cloth_id);
		qna.setCloth_name(cloth_name);
		qna.setQna_content(qna_content);
        qna.setQna_writer(qna_writer);
        qna.setReply(reply);
        qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(qora);
		
		//qna를 테이블에 추가
		QnaDBBean qnaProcess = QnaDBBean.getInstance();
		int check = qnaProcess.insertArticle(qna);
		
		request.setAttribute("check", Integer.valueOf(check));
		return "/qna/qnaPro.jsp";
	}

}
