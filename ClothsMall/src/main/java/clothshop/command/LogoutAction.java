package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		return "/member/logout.jsp";
	}
}
