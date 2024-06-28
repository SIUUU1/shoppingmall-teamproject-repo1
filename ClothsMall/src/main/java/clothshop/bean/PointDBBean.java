package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dbcon.DBUtil;

public class PointDBBean {
	private static PointDBBean instance = new PointDBBean();

	public static PointDBBean getInstance() {
		return instance;
	}

	private PointDBBean() {
	}

	public void increasePoint(PointDataBean point) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement(
					"insert into point (point_id, member_id, point,type,bank,account) values(point_seq.nextval?,?,?,?,?)");
			pstmt.setString(1, point.getMember_id());
			pstmt.setInt(2, point.getPoint());
			pstmt.setString(3, "INCREASE");
			pstmt.setString(4, point.getBank());
			pstmt.setInt(5, point.getAccount());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}

	public void decreasePoint(int usePoint, String member_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement(
					"insert into point (point_id, member_id, point,type) values(point_seq.nextval,?,?,?)");
			pstmt.setString(1, member_id);
			pstmt.setInt(2, usePoint);
			pstmt.setString(3, "DECREASE");
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}
}
