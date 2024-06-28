package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String member_id = request.getParameter("member_id");
		  
		//주어진 id의 중복여부를 체크해 값을 반환.
		LogonDBBean manager = LogonDBBean.getInstance();
		int check= manager.confirmId(member_id);
		
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/confirmId.jsp";
	}
}