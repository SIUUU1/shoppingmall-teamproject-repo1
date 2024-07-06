package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.LogonDataBean;

public class LoginFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String member_id = request.getParameter("member_id");
		if (!member_id.equals("")) {
			// 회원 정보를 얻어냄
			LogonDBBean manager = LogonDBBean.getInstance();
			LogonDataBean m = manager.getMember(member_id);
			request.setAttribute("m", m);
			request.setAttribute("id", member_id);
			request.setAttribute("type", 1);
			request.setAttribute("footer", 1);
		}
		return "/member/loginForm.jsp";
	}
}