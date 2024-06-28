package clothshop.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.LogonDataBean;

public class RegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		//회원가입 정보
		LogonDataBean member = new LogonDataBean();
		member.setMember_id(request.getParameter("member_id"));
        member.setMember_passwd(request.getParameter("member_passwd"));
        member.setMember_name(request.getParameter("member_name"));
        member.setReg_date(new Timestamp(System.currentTimeMillis()));
		member.setMember_address(request.getParameter("member_address"));
		member.setMember_postal_code(request.getParameter("member_postal_code"));
		member.setMember_detailed_address(request.getParameter("member_detailed_address"));
		member.setMember_tel(request.getParameter("member_tel"));
		member.setMember_gender(request.getParameter("member_gender"));
		
        //회원가입처리
        LogonDBBean dbPro = LogonDBBean.getInstance();
        dbPro.insertMember(member);
		
		return "/member/registerPro.jsp";
	}
}
