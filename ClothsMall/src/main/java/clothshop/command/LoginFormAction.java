package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String member_id = request.getParameter("member_id");
		if (!member_id.equals("")) {
			request.setAttribute("type", 1);
			request.setAttribute("footer", 1);
			
		}
		return "/member/loginForm.jsp";
	}
}