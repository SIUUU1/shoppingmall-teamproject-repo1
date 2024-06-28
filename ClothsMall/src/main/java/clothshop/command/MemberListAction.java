package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.LogonDataBean;

public class MemberListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		List<LogonDataBean> memberList = null;
		int count = 0;

		LogonDBBean process = LogonDBBean.getInstance();

		// 총 회원수 호출
		count = process.getMemberCount();
		if (count > 0) {
			// 회원 리스트 반환
			memberList = process.getMemberAll(count);
			request.setAttribute("memberList", memberList);
		}
		
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("type", Integer.valueOf(0));
		return "/mngr/memberProcess/memberList.jsp";
	}

}
