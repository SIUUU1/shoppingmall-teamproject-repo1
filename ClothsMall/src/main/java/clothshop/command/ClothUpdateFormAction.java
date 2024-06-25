package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;
import clothshop.bean.MngrDataBean;

public class ClothUpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int cloth_id = Integer.parseInt(request.getParameter("cloth_id"));
		String cloth_category = request.getParameter("cloth_category");
		String cloth_gender = request.getParameter("cloth_gender");
		String cloth_size = request.getParameter("cloth_size");
		// DB 연동 cloth_id 에 해당하는 상품을 얻내서 cloth 에 저장
		MngrDBBean clothProcess = MngrDBBean.getInstance();
		MngrDataBean cloth = clothProcess.getCloth(cloth_id);
		request.setAttribute("cloth_id", cloth_id);
		request.setAttribute("cloth_category", cloth_category);
		request.setAttribute("cloth_gender", cloth_gender);
		request.setAttribute("cloth_size", cloth_size);
		request.setAttribute("cloth", cloth);
		request.setAttribute("type", 0);
		return "/mngr/productProcess/clothUpdateForm.jsp";
	}

}
