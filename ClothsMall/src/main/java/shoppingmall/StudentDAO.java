package shoppingmall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.err.println("Connection 생성실패");
		}
		System.out.println("Connection 생성성공");
		return conn;
	}

	public boolean connCheck() {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			if (conn == null) {
				result = false;
			}
		} catch (Exception sqle) {
			sqle.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle3) {
				}
		}
		return result;
	}

	public boolean idCheck(String id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from student where id =?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next())
				result = false;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException sqle1) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException sqle2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException sqle3) {
				}
		}
		return result;
	}
}
