package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbcon.DBUtil;

public class QnaDBBean {

	private static QnaDBBean instance = new QnaDBBean();

	// .jsp페이지에서 DB연동빈인 BoardDBBean클래스의 메소드에 접근시 필요
	public static QnaDBBean getInstance() {
		return instance;
	}

	private QnaDBBean() {
	}

	// qna테이블에 글을 추가 - 사용자가 작성하는 글
	@SuppressWarnings("resource")
	public int insertArticle(QnaDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		String sql = "";
		int group_id = 1;
		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select max(qna_id) from qna");
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);

			if (x > 0)
				group_id = rs.getInt(1) + 1;

			// 쿼리를 작성 :qna테이블에 새로운 레코드 추가
			sql = "insert into qna(qna_id,cloth_id,cloth_name,qna_writer,qna_content,";
			sql += "group_id,qora,reply,reg_date) values(qna_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getCloth_id());
			pstmt.setString(2, article.getCloth_name());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_content());
			pstmt.setInt(5, group_id);
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();

			x = 1; // 레코드 추가 성공
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// qna테이블에 글을 추가 - 관리자가 작성한 답변
	@SuppressWarnings("resource")
	public int insertArticle(QnaDataBean article, int qna_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x = 0;
		String sql = "";

		try {
			conn = DBUtil.getConnection();

			// 쿼리를 작성 :qna테이블에 새로운 레코드 추가
			sql = "insert into qna(qna_id,cloth_id,cloth_name,qna_writer,qna_content,";
			sql += "group_id,qora,reply,reg_date) values(qna_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, article.getCloth_id());
			pstmt.setString(2, article.getCloth_name());
			pstmt.setString(3, article.getQna_writer());
			pstmt.setString(4, article.getQna_content());
			pstmt.setInt(5, article.getGroup_id());
			pstmt.setInt(6, article.getQora());
			pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
			pstmt.executeUpdate();

			sql = "update qna set reply=? where qna_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, qna_id);
			pstmt.executeUpdate();

			x = 1; // 레코드 추가 성공
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return x;
	}

	// qna테이블에 저장된 전체글의 수를 얻어냄
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select count(*) from qna");
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 특정 옷에 대해 작성한 qna글의 수를 얻어냄
	public int getArticleCount(int cloth_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select count(*) from qna where cloth_id = " + cloth_id);
			rs = pstmt.executeQuery();

			if (rs.next())
				x = rs.getInt(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 모든 qna글을 가져옴
	public List<QnaDataBean> getArticles(int count) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaDataBean> articleList = null;// 글목록을 저장하는 객체
		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select * from qna order by group_id desc, qora asc");

			rs = pstmt.executeQuery();

			if (rs.next()) {// ResultSet이 레코드를 가짐
				articleList = new ArrayList<QnaDataBean>(count);
				do {
					QnaDataBean article = new QnaDataBean();
					article.setQna_id(rs.getInt("qna_id"));
					article.setCloth_id(rs.getInt("cloth_id"));
					article.setCloth_name(rs.getString("cloth_name"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_content(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getByte("qora"));
					article.setReply(rs.getByte("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));

					// List객체에 데이터저장빈인 BoardDataBean객체를 저장
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return articleList;// List객체의 레퍼런스를 리턴
	}

	// 특정 옷에 대해 작성한 qna글을 지정한 수 만큼 얻어냄
	public List<QnaDataBean> getArticles(int count, int cloth_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaDataBean> articleList = null;// 글목록을 저장하는 객체
		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement(
					"select * from qna where cloth_id=" + cloth_id + " order by group_id desc, qora asc");

			rs = pstmt.executeQuery();

			if (rs.next()) {// ResultSet이 레코드를 가짐
				articleList = new ArrayList<QnaDataBean>(count);
				do {
					QnaDataBean article = new QnaDataBean();
					article.setQna_id(rs.getInt("qna_id"));
					article.setCloth_id(rs.getInt("cloth_id"));
					article.setCloth_name(rs.getString("cloth_name"));
					article.setQna_writer(rs.getString("qna_writer"));
					article.setQna_content(rs.getString("qna_content"));
					article.setGroup_id(rs.getInt("group_id"));
					article.setQora(rs.getByte("qora"));
					article.setReply(rs.getByte("reply"));
					article.setReg_date(rs.getTimestamp("reg_date"));

					// List객체에 데이터저장빈인 QnaDataBean객체를 저장
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return articleList;// List객체의 레퍼런스를 리턴
	}

	// QnA글 수정폼에서 사용할 글의 내용
	public QnaDataBean updateGetArticle(int qna_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaDataBean article = null;
		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select * from qna where qna_id = ?");
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				article = new QnaDataBean();
				article.setQna_id(rs.getInt("qna_id"));
				article.setCloth_id(rs.getInt("cloth_id"));
				article.setCloth_name(rs.getString("cloth_name"));
				article.setQna_writer(rs.getString("qna_writer"));
				article.setQna_content(rs.getString("qna_content"));
				article.setGroup_id(rs.getInt("group_id"));
				article.setQora(rs.getByte("qora"));
				article.setReply(rs.getByte("reply"));
				article.setReg_date(rs.getTimestamp("reg_date"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return article;
	}

	// QnA글 수정
	public int updateArticle(QnaDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();

			String sql = "update qna set qna_content=? where qna_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getQna_content());
			pstmt.setInt(2, article.getQna_id());
			pstmt.executeUpdate();
			x = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return x;
	}

	// QnA글 삭제
	public int deleteArticle(int qna_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("delete from qna where qna_id=?");
			pstmt.setInt(1, qna_id);
			pstmt.executeUpdate();
			x = 1; // 글삭제 성공
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return x;
	}
}