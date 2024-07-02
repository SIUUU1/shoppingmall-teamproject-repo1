package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.LogonDataBean;

public class ModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
	
		request.setCharacterEncoding("utf-8");
		
		//수정할 회원 정보
		LogonDataBean member = new LogonDataBean();
		member.setMember_id(request.getParameter("member_id"));
		member.setMember_passwd(request.getParameter("member_passwd"));
		member.setMember_name(request.getParameter("member_name"));
        member.setMember_address(request.getParameter("member_address"));
        member.setMember_postal_code(request.getParameter("member_postal_code"));
		member.setMember_detailed_address(request.getParameter("member_detailed_address"));
		member.setMember_tel(request.getParameter("member_tel"));
		
		//수정할 회원 정보를 가지고 수정 처리 후 성공여부 반환
		LogonDBBean manager = LogonDBBean.getInstance();
		int check = manager.updateMember(member);
		
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/modifyPro.jsp";
	}
}