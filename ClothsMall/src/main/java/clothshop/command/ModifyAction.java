package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.LogonDataBean;

public class ModifyAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String member_id = request.getParameter("member_id");
		
		// 회원 정보를 수정하기 위한 정보를 얻어냄
		LogonDBBean manager = LogonDBBean.getInstance();
		LogonDataBean m = manager.getMember(member_id);
		request.setAttribute("m", m);
		request.setAttribute("type", Integer.valueOf(1));
		return "/member/modify.jsp";
	}
}