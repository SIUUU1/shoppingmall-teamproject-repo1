package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;

public class MemberDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String[] delMemberIdList = request.getParameterValues("delMemberIdList");
		LogonDBBean process = LogonDBBean.getInstance();

		// 삭제한 회원수
		int delCount = process.delMember(delMemberIdList);
		request.setAttribute("delCount", delCount);
		request.setAttribute("type", Integer.valueOf(0));

		return "/mngr/memberProcess/memberDeletePro.jsp";
	}

}
