package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;
import clothshop.bean.PointDBBean;
import clothshop.bean.PointDataBean;

public class IncreasePointProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		
		//포인트 정보
		PointDataBean point = new PointDataBean(); 
		
		point.setMember_id(request.getParameter("member_id"));
		point.setBank(request.getParameter("bank"));
		point.setPoint(Integer.parseInt(request.getParameter("point")));
		point.setAccount(request.getParameter("account"));
		
		//포인트 충전
		PointDBBean pointPro = PointDBBean.getInstance();
		pointPro.increasePoint(point);

		//충전 포인트 갱신
		LogonDBBean dbPro2 = LogonDBBean.getInstance();
        int nowpoint =dbPro2.getMember(request.getParameter("member_id")).getPoint();
        request.setAttribute("point", Integer.valueOf(nowpoint));
        request.setAttribute("type", Integer.valueOf(1));
		
        return "/point/increasePointForm.jsp";
        
        
        
	}
}
