package mngr.enc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class PassCrypt {
    private static PassCrypt instance = new PassCrypt();
    
	public static PassCrypt getInstance() {
		return instance;
	}
	    
	private PassCrypt() {}
	
    public void cryptProcess(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        
        //SHA-256를 사용하는 SHA256클래스의 객체를 얻어낸다.
        SHA256 sha = SHA256.getInsatnce();
        
        try {
            conn = DBUtil.getConnection();
            
            pstmt = conn.prepareStatement(
                	"select manager_id, manager_passwd from manager");
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            	String id = rs.getString("manager_id");
            	String orgPass = rs.getString("manager_passwd");
            	
            	String shaPass = sha.getSha256(orgPass.getBytes());

            	String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
            	
            	pstmt = conn.prepareStatement(
                    "update manager set manager_passwd=? where manager_id=?");
                pstmt.setString(1, bcPass);
                pstmt.setString(2, id);
                pstmt.executeUpdate();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    }
}