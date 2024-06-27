package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MemberDBBean;

public class MemberDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String member_id = request.getParameter("member_id");
		String member_passwd = request.getParameter("member_passwd");

		// MngrDBBean 에서 관리 할 경우 관련 메소드 필요
		// (수정) 우선은 MemberDBBeam 속 회원 삭제 메소드 참조
		MemberDBBean process = MemberDBBean.getInstance();

		// 삭제 성공 여부 flag(1 = 성공, 0 = 실패)
		int flag = process.deleteMember(member_id, member_passwd);
		request.setAttribute("flag", flag);
		request.setAttribute("type", Integer.valueOf(0));
		// (수정) 관련 jsp 파일이 없어 임시로 backmain 폴더의 memberDeletePro.jsp 로 리턴
		return "/backmain/memberDeletePro.jsp";
	}

}
