package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.PointDBBean;
import clothshop.bean.PointDataBean;

public class IncreasePointAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		//포인트 정보
		PointDataBean point = new PointDataBean();
		point.setMember_id(request.getParameter("member_id"));
		point.setPoint(Integer.parseInt(request.getParameter("passwd")));
		point.setBank(request.getParameter("passwd"));
        point.setAccount(Integer.parseInt(request.getParameter("address")));


        //포인트 추가 처리
        PointDBBean dbPro = PointDBBean.getInstance();
        dbPro.increasePoint(point);
        
        LogonDBBean dbPro2 = LogonDBBean.getInstance();
        int nowPoint =dbPro2.getPoint(request.getParameter("member_id"));  //순서확인
        request.setAttribute("point", Integer.valueOf(nowPoint));
		
        return "/point/IncreasePoint.jsp";
        
        
	}
}
