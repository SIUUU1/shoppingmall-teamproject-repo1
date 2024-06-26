package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbcon.DBUtil;

public class CartDBBean {
	// 싱글톤
	private static CartDBBean instance = null;

	private CartDBBean() {

	}

	public static CartDBBean getInstance() {
		if (instance == null) {
			synchronized (CartDBBean.class) {
				instance = new CartDBBean();
			}
		}
		return instance;
	}

	// 1. 장바구니 담기
	public void insertCart(CartDataBean cart) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = DBUtil.getConnection();
			sql = "insert into cart values (cart_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, cart.getMember_id());
			pstmt.setInt(2, cart.getCloth_id());
			pstmt.setString(3, cart.getCloth_category());
			pstmt.setString(4, cart.getCloth_name());
			pstmt.setString(5, cart.getCloth_size());
			pstmt.setInt(6, cart.getCloth_price());
			pstmt.setString(7, cart.getCloth_brand());
			pstmt.setString(8, cart.getCloth_image());
			pstmt.setInt(9, cart.getDiscount_rate());
			pstmt.setInt(10, cart.getQuantity());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}

	}

	// 2. 카트 수 확인
	public int getListCount(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from cart where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 3. 구매자 일치 카트 호출
	public List<CartDataBean> getCart(String id, int count) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartDataBean cart = null;
		String sql = "";
		List<CartDataBean> lists = null;

		try {
			conn = DBUtil.getConnection();
			sql = "select * from cart where member_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			lists = new ArrayList<CartDataBean>(count);
			while (rs.next()) {
				cart = new CartDataBean();
				cart.setCart_id(rs.getInt("cart_id"));
				cart.setCloth_id(rs.getInt("cloth_id"));
				cart.setCloth_category(rs.getString("cloth_category"));
				cart.setCloth_name(rs.getString("cloth_name"));
				cart.setCloth_size(rs.getString("cloth_size"));
				cart.setCloth_price(rs.getInt("cloth_price"));
				cart.setCloth_brand(rs.getString("cloth_brand"));
				cart.setCloth_image(rs.getString("cloth_image"));
				cart.setDiscount_rate(rs.getInt("discount_rate"));
				cart.setQuantity(rs.getInt("quantity"));

				lists.add(cart);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return lists;
	}

	// 4. 카트 옷 수량 수정
	public void updateCount(int cart_id, byte quantity) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("update cart set quantity = ? where cart_id = ?");
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, cart_id);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}

	// 5. 카트 삭제
	public void deleteList(int cart_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from cart where cart_id=?");
			pstmt.setInt(1, cart_id);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}

	// 6. 구매자 일치 카트 일괄 삭제 (장바구니 비우기)
	public void deleteAll(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from cart where member_id = ?");
			pstmt.setString(1, id);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}
}
