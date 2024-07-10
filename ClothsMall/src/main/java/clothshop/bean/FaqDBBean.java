package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbcon.DBUtil;

public class FaqDBBean {
	// 싱글톤
	private static FaqDBBean instance = null;

	private FaqDBBean() {

	}

	public static FaqDBBean getInstance() {
		if (instance == null) {
			synchronized (FaqDBBean.class) {
				instance = new FaqDBBean();
			}
		}
		return instance;
	}

	// 1. 전체 faq 호출
	public ArrayList<FaqDataBean> callFaq() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FaqDataBean faq = null;
		String sql = "";
		ArrayList<FaqDataBean> faqList = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM FAQ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			faqList = new ArrayList<FaqDataBean>();

			while (rs.next()) {
				faq = new FaqDataBean();
				faq.setFaq_no(rs.getInt("faq_no"));
				faq.setFaq_category(rs.getInt("faq_category"));
				faq.setFaq_title(rs.getString("faq_title"));
				faq.setFaq_content(rs.getString("faq_content"));
				faqList.add(faq);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return faqList;
	}

	// 2. faq 추가
	public int insertFaq(FaqDataBean faq) {
		int flag = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO FAQ VALUES (FAQ_SEQ.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, faq.getFaq_category());
			pstmt.setString(2, faq.getFaq_title());
			pstmt.setString(3, faq.getFaq_content());

			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return flag;
	}

}
