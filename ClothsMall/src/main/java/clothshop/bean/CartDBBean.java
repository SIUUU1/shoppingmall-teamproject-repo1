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
			sql = "insert into cart (cart_id, buyer, cloth_id, cloth_category, cloth_name, cloth_size, "
					+ "cloth_price, cloth_brand, cloth_image, discount_rate, quantity)"
					+ "values (cart_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, cart.getCart_id());
			pstmt.setString(2, cart.getBuyer());
			pstmt.setInt(3, cart.getCloth_id());
			pstmt.setString(4, cart.getCloth_category());
			pstmt.setString(5, cart.getCloth_name());
			pstmt.setString(6, cart.getCloth_size());
			pstmt.setInt(7, cart.getCloth_price());
			pstmt.setString(8, cart.getCloth_brand());
			pstmt.setString(9, cart.getCloth_image());
			pstmt.setInt(10, cart.getDiscount_rate());
			pstmt.setInt(11, cart.getQuantity());

			pstmt.executeUpdate();
		} catch (Exception e) {

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
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
			pstmt = conn.prepareStatement("select count(*) from cart where buyer = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	// 3. 구매자 일치 카트 호출
	public List<CartDataBean> getCart(String id, int quantity) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartDataBean cart = null;
		String sql = "";
		List<CartDataBean> lists = null;

		try {
			conn = DBUtil.getConnection();
			sql = "select * from cart where buyer = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			lists = new ArrayList<CartDataBean>(quantity);
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
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
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
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
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

			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	// 6. 구매자 일치 카트 일괄 삭제 (장바구니 비우기)
	public void deleteAll(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from cart where buyer = ?");
			pstmt.setString(1, id);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}
}
