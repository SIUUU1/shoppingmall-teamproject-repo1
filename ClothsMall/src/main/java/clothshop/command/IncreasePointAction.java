package clothshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.LogonDBBean;

public class IncreasePointAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		

        LogonDBBean dbPro2 = LogonDBBean.getInstance();
        int point =dbPro2.getMember(request.getParameter("member_id")).getPoint();
        request.setAttribute("point", Integer.valueOf(point));
        request.setAttribute("type", Integer.valueOf(1));
		
        return "/point/increasePointForm.jsp";
        
        
	}
}
