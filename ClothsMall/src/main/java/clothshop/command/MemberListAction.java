package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import clothshop.bean.LogonDataBean;
import clothshop.bean.MemberDBBean;

public class MemberListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		List<LogonDataBean> memberList = null;
		int count = 0;
		
		// (수정) LogonDBBean에 관련 메소드 없어 새로운 빈(MemberDBBean)으로 작업함
		MemberDBBean process = MemberDBBean.getInstance();
		
		// (수정) MemberDBBean --- 1 --- 총 회원수 호출
		count = process.getMemberCount();
		if (count > 0) {
			
			// (수정) MemberDBBean --- 2 ---  회원 리스트 반환
			memberList = process.getMemberAll(count);
			request.setAttribute("memberList", memberList);
		}
		
		// 총 회원 수
		request.setAttribute("count", Integer.valueOf(count));
		
		request.setAttribute("type", Integer.valueOf(0));
		// (수정) 관련 jsp 파일 부재로 backmain 의 리스트 파일로 리턴
		return "/backmain/memberList.jsp";
	}

}
