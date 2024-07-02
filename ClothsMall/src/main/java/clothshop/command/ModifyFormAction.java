package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.LogonDataBean;

public class ModifyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		String member_id = request.getParameter("member_id");
		String member_passwd = request.getParameter("member_passwd");

		// 회원 정보를 수정하기 위한 정보를 얻어냄
		LogonDBBean manager = LogonDBBean.getInstance();
		LogonDataBean m = manager.getMember(member_id, member_passwd);

		request.setAttribute("m", m);
		request.setAttribute("id", member_id);
		request.setAttribute("type", Integer.valueOf(1));
		if (m == null) {
			request.setAttribute("check", Integer.valueOf(0));
			return "/member/modify.jsp";
		}
		return "/member/modifyForm.jsp";
	}
}