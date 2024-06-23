package clothshop.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clothshop.bean.MngrDBBean;
import clothshop.bean.MngrDataBean;

public class ShopMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MngrDataBean clothList[] = null;
		List<MngrDataBean[]> clothLists = new ArrayList<MngrDataBean[]>();
		MngrDBBean clothProcess = MngrDBBean.getInstance();// DB 연동

		// 카테고리별 최신의 상품 3 개씩 얻어내서 List 에 저장
		for (int i = 1; i <= 3; i++) {
			clothList = clothProcess.getCloths(i + "000", 3);
			clothLists.add(clothList);
		}
		// 해당 페이지로 보낼 내용 설정
		request.setAttribute("clothLists", clothLists);
		// 사용자 화면을 의미하는 값을 설정
		request.setAttribute("type", Integer.valueOf(1));
		return "/shop/shopMain.jsp";
	}
}
