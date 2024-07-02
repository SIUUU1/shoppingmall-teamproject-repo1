package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dbcon.DBUtil;

public class ReceiptDBBean {
	private static ReceiptDBBean instance = new ReceiptDBBean();

	public static ReceiptDBBean getInstance() {
		return instance;
	}

	private ReceiptDBBean() {
	}

	public void regReceipt(ReceiptDataBean receipt) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();

			String sql = "insert into receipt (receipt_id, member_id, price,";
			sql += " grade_discount, use_mileage, total_price, delivery_name,";
			sql += " delivery_address, delivery_postal_code, delivery_detailed_address, delivery_tel)";
			sql += " VALUES (receipt_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, receipt.getMember_id());
			pstmt.setInt(2, receipt.getPrice());
			pstmt.setInt(3, receipt.getGrade_discount());
			pstmt.setInt(4, receipt.getUse_mileage());
			pstmt.setInt(5, receipt.getTotal_price());
			pstmt.setString(6, receipt.getDelivery_name());
			pstmt.setString(7, receipt.getDelivery_address());
			pstmt.setString(8, receipt.getDelivery_postal_code());
			pstmt.setString(9, receipt.getDelivery_detailed_address());
			pstmt.setString(10, receipt.getDelivery_tel());
			pstmt.executeUpdate();
			System.out.println("reipt insert ");

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}

	}

	// 특정 member_id에 해당하는 영수증을 가져오는 메소드
	public List<ReceiptDataBean> getReceiptList(String memberId) {
		List<ReceiptDataBean> receipts = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM receipt WHERE member_id = ? ORDER BY receipt_id DESC";
			//내림차순으로 최신순 정렬

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReceiptDataBean receipt = new ReceiptDataBean();
				receipt.setReceipt_id(rs.getLong("RECEIPT_ID"));
				receipt.setMember_id(rs.getString("member_id"));
				receipt.setPrice(rs.getInt("PRICE"));
				receipt.setGrade_discount((int) rs.getDouble("GRADE_DISCOUNT"));
				receipt.setBuy_date(rs.getTimestamp("BUY_DATE"));
				receipt.setUse_mileage(rs.getInt("USE_MILEAGE"));
				receipt.setTotal_price(rs.getInt("TOTAL_PRICE"));
				receipt.setDelivery_name(rs.getString("delivery_name"));
				receipt.setDelivery_address(rs.getString("delivery_address"));
				receipt.setDelivery_postal_code(rs.getString("delivery_postal_code"));
				receipt.setDelivery_detailed_address(rs.getString("delivery_detailed_address"));
				receipt.setDelivery_tel(rs.getString("delivery_tel"));
				receipt.setState(rs.getString("state"));
				receipts.add(receipt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return receipts;
	}
	// 모든 영수증 가져오는 메소드
		public List<ReceiptDataBean> getReceiptList() {
			List<ReceiptDataBean> receipts = new ArrayList<>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBUtil.getConnection();
				String query = "SELECT * FROM receipt ORDER BY receipt_id DESC";
				//내림차순으로 최신순 정렬

				pstmt = conn.prepareStatement(query);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ReceiptDataBean receipt = new ReceiptDataBean();
					receipt.setReceipt_id(rs.getLong("RECEIPT_ID"));
					receipt.setMember_id(rs.getString("member_id"));
					receipt.setPrice(rs.getInt("PRICE"));
					receipt.setGrade_discount((int) rs.getDouble("GRADE_DISCOUNT"));
					receipt.setBuy_date(rs.getTimestamp("BUY_DATE"));
					receipt.setUse_mileage(rs.getInt("USE_MILEAGE"));
					receipt.setTotal_price(rs.getInt("TOTAL_PRICE"));
					receipt.setDelivery_name(rs.getString("delivery_name"));
					receipt.setDelivery_address(rs.getString("delivery_address"));
					receipt.setDelivery_postal_code(rs.getString("delivery_postal_code"));
					receipt.setDelivery_detailed_address(rs.getString("delivery_detailed_address"));
					receipt.setDelivery_tel(rs.getString("delivery_tel"));
					receipt.setState(rs.getString("state"));
					receipts.add(receipt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBUtil.dbReleaseClose(rs, pstmt, conn);
			}

			return receipts;
		}

		public void updateOrderState(String receipt_id, String state) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DBUtil.getConnection();

				String sql = "update receipt set state=? where receipt_id=? ";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, state);
				pstmt.setLong(2, Long.parseLong(receipt_id));
				pstmt.executeUpdate();

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBUtil.dbReleaseClose(pstmt, conn);
			}
			
		}

}
