package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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
				String sql = "insert into buy (buy_id, member_id, CLOTH_ID, quantity, cloth_price, discount_rate, cloth_image,cloth_size, receipt_id) ";
				sql += "values (BUY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?,?, "
						+ "(SELECT MAX(receipt_id) AS max_receipt_id FROM receipt))";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, cart.getCloth_id());
				pstmt.setInt(3, cart.getQuantity());
				pstmt.setInt(4, cart.getCloth_price());
				pstmt.setInt(5, cart.getDiscount_rate());
				pstmt.setString(6, cart.getCloth_image());
				pstmt.setString(7, cart.getCloth_size());
				pstmt.executeUpdate();
//상품이 구매되었으므로 cloth 테이블의 상품수량을 재조정함
				pstmt = conn.prepareStatement("select cloth_count from cloth where cloth_id =?");
				pstmt.setInt(1, cart.getCloth_id());
				rs = pstmt.executeQuery();
				rs.next();
				nowCount = (int) (rs.getInt(1) - cart.getQuantity());
				sql = "update cloth set cloth_count=? where cloth_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, nowCount);
				pstmt.setInt(2, cart.getCloth_id());
				pstmt.executeUpdate();
			}
			pstmt = conn.prepareStatement("delete from cart where member_id=?");
			pstmt.setString(1, member_id);
			pstmt.executeUpdate();
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
	public List<HashMap<String, Object>> getBuyList(String member_id){
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<HashMap<String, Object>> lists = null;
	    
	    try {
	        conn = DBUtil.getConnection();
	        String sql = "SELECT b.member_id, b.cloth_id, b.quantity, b.receipt_id, b.cloth_price, b.discount_rate, " +
	                     "c.cloth_category, c.cloth_name, b.cloth_size, c.cloth_brand, c.cloth_image, c.cloth_content " +
	                     ", b.quantity FROM buy b " +
	                     "JOIN cloth c ON b.cloth_id = c.cloth_id WHERE  b.member_id=? ORDER BY b.receipt_id DESC";
	        //내림차순으로 최근 순으로 정렬
	                     
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
	        lists = new ArrayList<>();
	        
	        while (rs.next()) {
	            HashMap<String, Object> buyMap = new HashMap<>();
	            buyMap.put("member_id", rs.getString("member_id"));
	            buyMap.put("cloth_id", rs.getString("cloth_id"));
	            buyMap.put("cloth_price", rs.getInt("quantity"));
	            buyMap.put("receipt_id", rs.getLong("receipt_id"));
	            buyMap.put("cloth_price", rs.getInt("cloth_price"));
	            buyMap.put("discount_rate", rs.getInt("discount_rate"));
	            buyMap.put("cloth_category", rs.getString("cloth_category"));
	            buyMap.put("cloth_name", rs.getString("cloth_name"));
	            buyMap.put("cloth_size", rs.getString("cloth_size"));
	            buyMap.put("cloth_brand", rs.getString("cloth_brand"));
	            buyMap.put("cloth_image", rs.getString("cloth_image"));
	            buyMap.put("cloth_content", rs.getString("cloth_content"));
	            buyMap.put("quantity", rs.getInt("quantity"));
	            lists.add(buyMap);
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        DBUtil.dbReleaseClose(rs, pstmt, conn);
	    }
	    
	    return lists;
	}

	// buy 테이블의 전체 목록을 얻어내는 메소드
	public List<HashMap<String, Object>> getBuyList() {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<HashMap<String, Object>> lists = null;
	    
	    try {
	        conn = DBUtil.getConnection();
	        String sql = "SELECT b.member_id, b.cloth_id, b.quantity, b.receipt_id, b.cloth_price, b.discount_rate, " +
	                     "c.cloth_category, c.cloth_name, b.cloth_size, c.cloth_brand, c.cloth_image, c.cloth_content " +
	                     "FROM buy b " +
	                     "JOIN cloth c ON b.cloth_id = c.cloth_id";
	                     
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        lists = new ArrayList<>();
	        
	        while (rs.next()) {
	        	HashMap<String, Object> buyMap = new HashMap<>();
	            buyMap.put("member_id", rs.getString("member_id"));
	            buyMap.put("cloth_id", rs.getString("cloth_id"));
	            buyMap.put("quantity", rs.getInt("quantity"));
	            buyMap.put("receipt_id", rs.getLong("receipt_id"));
	            buyMap.put("cloth_price", rs.getInt("cloth_price"));
	            buyMap.put("discount_rate", rs.getInt("discount_rate"));
	            buyMap.put("cloth_category", rs.getString("cloth_category"));
	            buyMap.put("cloth_name", rs.getString("cloth_name"));
	            buyMap.put("cloth_size", rs.getString("cloth_size"));
	            buyMap.put("cloth_brand", rs.getString("cloth_brand"));
	            buyMap.put("cloth_image", rs.getString("cloth_image"));
	            buyMap.put("cloth_content", rs.getString("cloth_content"));
	            
	            lists.add(buyMap);
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	        DBUtil.dbReleaseClose(rs, pstmt, conn);
	    }
	    
	    return lists;
	}
}