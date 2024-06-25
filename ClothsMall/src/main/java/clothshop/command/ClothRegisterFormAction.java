package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClothRegisterFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("type", 0);
		return "/mngr/productProcess/clothRegisterForm.jsp";
	}

}
