package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import dbcon.DBUtil;

public class BuyDBBean {
	private static BuyDBBean instance = new BuyDBBean();

	public static BuyDBBean getInstance() {
		return instance;
	}

	private BuyDBBean() {
	}


//구매 테이블인 buy 에 구매목록 등록
	@SuppressWarnings("resource")
	public void insertBuy(List<CartDataBean> lists, String member_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Timestamp reg_date = null;
		
		long buyId = 0;
		int nowCount;
		try {
			conn = DBUtil.getConnection();
			for (int i = 0; i < lists.size(); i++) {
//해당 아이디에 대한 cart 테이블 레코드를을 가져온후 buy 테이블에 추가
				CartDataBean cart = lists.get(i);
				String sql = "insert into buy (member_id, cloth_id, quantity, receipt_id,cloth_price,discount_rate)";
				sql += " values (BUY_SEQ.NEXTVAl,?,?,?,?,receipt_seq.CURRVAL)"; //recipt를 먼저 넣어야 함.
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, cart.getCloth_id());
				pstmt.setInt(3, cart.getQuantity());
				pstmt.setInt(4, cart.getCloth_price());
				pstmt.setInt(5, cart.getDiscount_rate());
				pstmt.executeUpdate();
//상품이 구매되었으므로 cloth 테이블의 상품수량을 재조정함
				pstmt = conn.prepareStatement("select cloth_count from cloth where cloth_id =?");
				pstmt.setInt(1, cart.getCloth_id());
				rs = pstmt.executeQuery();
				rs.next();
				nowCount = (int) (rs.getInt(1) - cart.getQuantity());
				sql = "update book set cloth_count=? where cloth_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nowCount);
				pstmt.setInt(2, cart.getCloth_id());
				pstmt.executeUpdate();
			}
			pstmt = conn.prepareStatement("delete from cart where member_id=?");
			pstmt.setString(1, member_id);
			pstmt.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
	}

//id 에 해당하는 buy 테이블의 레코드수를 얻어내는 메소드
	public int getListCount(String member_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from buy where member_id=?");
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return x;

	}

//buy 테이블의 전체 레코드수를 얻어내는 메소드
	public int getListCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from buy");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return x;

	}

//id 에 해당하는 buy 테이블의 구매목록을 얻어내는 메소드
	public List<BuyDataBean> getBuyList(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyDataBean buy = null;
		String sql = "";
		List<BuyDataBean> lists = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from buy where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			lists = new ArrayList<BuyDataBean>();
			while (rs.next()) {
				buy = new BuyDataBean();
				buy = new BuyDataBean();
				buy.setMember_id(rs.getString("member_id"));
				buy.setCloth_id(rs.getString("cloth_id"));
				buy.setQuantity(rs.getInt("quantity"));
				buy.setReceipt_id(rs.getLong("receipt_id"));
				buy.setCloth_price(rs.getInt("cloth_price"));
				buy.setDiscount_rate(rs.getInt("discount_rate"));
				lists.add(buy);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return lists;

	}

	// buy 테이블의 전체 목록을 얻어내는 메소드
	public List<BuyDataBean> getBuyList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BuyDataBean buy = null;
		String sql = "";
		List<BuyDataBean> lists = null;
		try {
			conn = DBUtil.getConnection();
			sql = "select * from buy";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			lists = new ArrayList<BuyDataBean>();
			while (rs.next()) {
				buy = new BuyDataBean();
				buy.setMember_id(rs.getString("member_id"));
				buy.setCloth_id(rs.getString("cloth_id"));
				buy.setQuantity(rs.getInt("quantity"));
				buy.setReceipt_id(rs.getLong("receipt_id"));
				buy.setCloth_price(rs.getInt("cloth_price"));
				buy.setDiscount_rate(rs.getInt("discount_rate"));
				lists.add(buy);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}

		return lists;
	}
}