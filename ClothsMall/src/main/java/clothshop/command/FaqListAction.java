package clothshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.FaqDBBean;
import clothshop.bean.FaqDataBean;

public class FaqListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		List<FaqDataBean> faqList = null;
		FaqDBBean process = FaqDBBean.getInstance();

		faqList = process.callFaq();
		if (!faqList.isEmpty()) {
			request.setAttribute("faqList", faqList);
		}
		request.setAttribute("type", Integer.valueOf(1));
		request.setAttribute("footer", 1);
		return "/faq/faqList.jsp";
	}

}
