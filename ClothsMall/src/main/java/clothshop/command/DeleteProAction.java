package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String id = request.getParameter("member_id");
		
		//사용자가 입력한 id, passwd를 가지고 회원정보 삭제 후 성공여부 반환
		LogonDBBean manager = LogonDBBean.getInstance();
		int check = manager.deleteMember(id);
		
		request.setAttribute("check", Integer.valueOf(check));  
		return "/member/deletePro.jsp";
	}
}