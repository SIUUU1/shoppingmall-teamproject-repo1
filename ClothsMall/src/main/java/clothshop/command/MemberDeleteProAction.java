package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MemberDBBean;

public class MemberDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String[] delMemberIdList = request.getParameterValues("delMemberIdList");

		// MngrDBBean 에서 관리 할 경우 관련 메소드 필요
		// (수정) 우선은 MemberDBBeam 속 회원 삭제 메소드 참조
		MemberDBBean process = MemberDBBean.getInstance();

		// 삭제한 회원수
		int delCount = process.delMember(delMemberIdList);
		request.setAttribute("delCount", delCount);
		request.setAttribute("type", Integer.valueOf(0));
		// (수정) 관련 jsp 파일이 없어 임시로 backmain 폴더의 memberDeletePro.jsp 로 리턴
		return "/backmain/memberDeletePro.jsp";
	}

}
