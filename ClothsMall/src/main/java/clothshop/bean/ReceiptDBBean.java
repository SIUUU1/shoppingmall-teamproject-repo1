package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

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
		ResultSet rs = null;
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
		        pstmt.setString(9, receipt.getDelivery_address());
		        pstmt.setString(10, receipt.getDelivery_tel());

		}catch(Exception ex){
		ex.printStackTrace();
	}finally
	{
		DBUtil.dbReleaseClose(rs, pstmt, conn);
	}

}

}
