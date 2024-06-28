package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String member_id = request.getParameter("member_id");
		String member_passwd  = request.getParameter("member_passwd");

		//사용자가 입력한 id, passwd를 가지고 인증 체크 후 값 반환
		LogonDBBean manager = LogonDBBean.getInstance();
		int check= manager.userCheck(member_id,member_passwd);
		
		request.setAttribute("id", member_id);
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/loginPro.jsp";
	}
}