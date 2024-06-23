package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;

public class ClothDeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int cloth_id = Integer.parseInt(request.getParameter("cloth_id"));
		String cloth_category = request.getParameter("cloth_category");
		
		//DB 연동 - cloth_id 에 해당하는 상품을 삭제
		MngrDBBean clothProcess = MngrDBBean.getInstance();
		clothProcess.deleteCloth(cloth_id);
		
		request.setAttribute("cloth_category", cloth_category);
		return "/mngr/productProcess/clothDeletePro.jsp";
	}

}
