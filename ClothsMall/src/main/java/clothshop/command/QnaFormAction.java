package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;

public class QnaFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String cloth_category = request.getParameter("cloth_category");
		int cloth_id=Integer.parseInt(request.getParameter("cloth_id"));  
		
		//book_id에 해당하는 book_title을 얻어냄
		MngrDBBean clothProcess = MngrDBBean.getInstance();
		String cloth_name = clothProcess.getClothName(cloth_id);
	    
		request.setAttribute("cloth_category", cloth_category);
	    request.setAttribute("cloth_id", Integer.valueOf(cloth_id));
	    request.setAttribute("cloth_name", cloth_name);
	    request.setAttribute("qora", Integer.valueOf(1));
		request.setAttribute("type", Integer.valueOf(1));
		return "/qna/qnaForm.jsp";
	}
}