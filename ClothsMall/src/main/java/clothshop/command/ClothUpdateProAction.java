package clothshop.command;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import clothshop.bean.MngrDBBean;
import clothshop.bean.MngrDataBean;

public class ClothUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        request.setCharacterEncoding("utf-8"); // 한글 인코딩
        String filename = "";
        String realFolder = ""; // 웹 어플리케이션상의 절대 경로 저장
        String saveFolder = "/clothImage"; // 파일 업로드 폴더 지정
        String encType = "utf-8"; // 인코딩타입
        int maxSize = 50 * 1024 * 1024; // 최대 업로될 파일크기 50Mb
        MultipartRequest imageUp = null;

        // 웹 어플리케이션상의 절대 경로를 구함
        ServletContext context = request.getSession().getServletContext();
        realFolder = context.getRealPath(saveFolder);

        try {
            // 파일 업로드를 수행하는 MultipartRequest 객체 생성
            imageUp = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
            // <input type="file">인 모든 파라미터를 얻어냄
            Enumeration<?> files = imageUp.getFileNames();
            while (files.hasMoreElements()) {
                String name = (String) files.nextElement();
                filename = imageUp.getFilesystemName(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		MngrDataBean cloth = new MngrDataBean();
		int cloth_id= Integer.parseInt(imageUp.getParameter("cloth_id"));
		String cloth_category = imageUp.getParameter("cloth_category");
		String cloth_gender = imageUp.getParameter("cloth_gender");
		String cloth_name = imageUp.getParameter("cloth_name");
		String cloth_size = imageUp.getParameter("cloth_size");
		String cloth_price = imageUp.getParameter("cloth_price");
		String cloth_count = imageUp.getParameter("cloth_count");
		String cloth_brand = imageUp.getParameter("cloth_brand");
		String cloth_content = imageUp.getParameter("cloth_content");
		String discount_rate = imageUp.getParameter("discount_rate");
		
		cloth.setCloth_category(cloth_category);
		cloth.setCloth_gender(cloth_gender);
		cloth.setCloth_name(cloth_name);
		cloth.setCloth_size(cloth_size);
		cloth.setCloth_price(Integer.parseInt(cloth_price));
		cloth.setCloth_count(Integer.parseInt(cloth_count));
		cloth.setCloth_brand(cloth_brand);
		cloth.setCloth_content(cloth_content);
		cloth.setDiscount_rate(Integer.parseInt(discount_rate));
		cloth.setCloth_image(filename);
		cloth.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		//DB 연동해서 상품 수정 처리
		MngrDBBean clothProcess = MngrDBBean.getInstance();
		clothProcess.updateCloth(cloth, cloth_id);
		request.setAttribute("cloth_category", cloth_category);
		return "/mngr/productProcess/clothUpdatePro.jsp";
	}
}
