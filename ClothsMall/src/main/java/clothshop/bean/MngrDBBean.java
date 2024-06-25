package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class MngrDBBean {
	private static MngrDBBean instance = new MngrDBBean();

	private MngrDBBean() {
	}

	public static MngrDBBean getInstance() {
		return instance;
	}

	// 관리자 인증 메소드
	public int userCheck(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			pstmt = conn.prepareStatement("select manager_passwd from manager where manager_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 해당 아이디가 있으면 수행
				String dbpasswd = rs.getString("manager_passwd");
				if (BCrypt.checkpw(shaPass, dbpasswd))
					x = 1; // 인증성공
				else
					x = 0; // 비밀번호 틀림
			} else {// 해당 아이디 없으면 수행
				x = -1;// 아이디 없음
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 옷 등록 메소드
	public void insertCloth(MngrDataBean cloth) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into cloth values(cloth_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cloth.getCloth_category());
			pstmt.setString(2, cloth.getCloth_gender());
			pstmt.setString(3, cloth.getCloth_name());
			pstmt.setString(4, cloth.getCloth_size());
			pstmt.setInt(5, cloth.getCloth_price());
			pstmt.setInt(6, cloth.getCloth_count());
			pstmt.setString(7, cloth.getCloth_brand());
			pstmt.setTimestamp(8, cloth.getReg_date());
			pstmt.setString(9, cloth.getCloth_image());
			pstmt.setString(10, cloth.getCloth_content());
			pstmt.setInt(11, cloth.getDiscount_rate());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}

	// 이미 등록된 옷을 검증
	public int registedClothconfirm(String cloth_name, String cloth_brand) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();
			String sql = "select cloth_name from cloth where cloth_name=? and cloth_brand=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cloth_name);
			pstmt.setString(2, cloth_brand);
			rs = pstmt.executeQuery();
			if (rs.next())
				x = 1; // 해당 책이 이미 등록되어 있음
			else
				x = -1;// 해당 책이 이미 등록되어 있지 않음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 전체 등록된 옷의 수를 얻어내는 메소드
	public int getClothCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from cloth");
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

	// 해당 분류의 옷의 수를 얻어내는 메소드
	public int getCategoryCount(String cloth_category) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		int kind = Integer.parseInt(cloth_category);
		try {
			conn = DBUtil.getConnection();
			String query = "select count(*) from cloth where cloth_category=" + kind;
			pstmt = conn.prepareStatement(query);
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

	// 해당 브랜드의 옷의 수를 얻어내는 메소드
	public int getBrandCount(String cloth_brand) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		int kind = Integer.parseInt(cloth_brand);
		try {
			conn = DBUtil.getConnection();
			String query = "select count(*) from cloth where cloth_brand=" + kind;
			pstmt = conn.prepareStatement(query);
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

	// 옷이름 얻어냄
	public String getClothName(int cloth_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String x = "";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select cloth_name from cloth where cloth_id = " + cloth_id);
			rs = pstmt.executeQuery();
			if (rs.next())
				x = rs.getString(1);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 분류별또는 전체등록된 옷의 정보를 얻어내는 메소드
	public List<MngrDataBean> getCloths(String cloth_category) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MngrDataBean> clothList = null;
		try {
			conn = DBUtil.getConnection();
			String sql1 = "select * from cloth";
			String sql2 = "select * from cloth where cloth_category = ? order by reg_date desc";
			if (cloth_category.equals("all") || cloth_category.equals("")) {
				pstmt = conn.prepareStatement(sql1);
			} else {
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, cloth_category);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				clothList = new ArrayList<MngrDataBean>();
				do {
					MngrDataBean cloth = new MngrDataBean();
					cloth.setCloth_id(rs.getInt("cloth_id"));
					cloth.setCloth_category(rs.getString("cloth_category"));
					cloth.setCloth_gender(rs.getString("cloth_gender"));
					cloth.setCloth_name(rs.getString("cloth_name"));
					cloth.setCloth_size(rs.getString("cloth_size"));
					cloth.setCloth_price(rs.getInt("cloth_price"));
					cloth.setCloth_count(rs.getInt("cloth_count"));
					cloth.setCloth_brand(rs.getString("cloth_brand"));
					cloth.setReg_date(rs.getTimestamp("reg_date"));
					cloth.setCloth_image(rs.getString("cloth_image"));
					cloth.setCloth_content(rs.getString("cloth_content"));
					cloth.setDiscount_rate(rs.getInt("discount_rate"));
					clothList.add(cloth);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return clothList;
	}

	// 쇼핑몰 메인에 표시하기 위해서 사용하는 분류별 신간 옷 목록을 얻어내는 메소드
	public MngrDataBean[] getCloths(String cloth_category, int count) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MngrDataBean clothList[] = null;
		int i = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM ( SELECT * FROM cloth WHERE cloth_category = ?";
			sql += " ORDER BY reg_date DESC) WHERE ROWNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cloth_category);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				clothList = new MngrDataBean[count];
				do {
					MngrDataBean cloth = new MngrDataBean();
					cloth.setCloth_id(rs.getInt("cloth_id"));
					cloth.setCloth_category(rs.getString("cloth_category"));
					cloth.setCloth_gender(rs.getString("cloth_gender"));
					cloth.setCloth_name(rs.getString("cloth_name"));
					cloth.setCloth_size(rs.getString("cloth_size"));
					cloth.setCloth_price(rs.getInt("cloth_price"));
					cloth.setCloth_count(rs.getInt("cloth_count"));
					cloth.setCloth_brand(rs.getString("cloth_brand"));
					cloth.setReg_date(rs.getTimestamp("reg_date"));
					cloth.setCloth_image(rs.getString("cloth_image"));
					cloth.setCloth_content(rs.getString("cloth_content"));
					cloth.setDiscount_rate(rs.getInt("discount_rate"));
					clothList[i] = cloth;
					i++;
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return clothList;
	}

	// cloth_id 에 해당하는 옷의 정보를 얻어내는 메소드로
	// 등록된 옷을 수정하기 위해 수정폼으로 읽어들기이기 위한 메소드
	public MngrDataBean getCloth(int cloth_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MngrDataBean cloth = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from cloth where cloth_id = ?");
			pstmt.setInt(1, cloth_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cloth = new MngrDataBean();
				cloth.setCloth_category(rs.getString("cloth_category"));
				cloth.setCloth_gender(rs.getString("cloth_gender"));
				cloth.setCloth_name(rs.getString("cloth_name"));
				cloth.setCloth_size(rs.getString("cloth_size"));
				cloth.setCloth_price(rs.getInt("cloth_price"));
				cloth.setCloth_count(rs.getInt("cloth_count"));
				cloth.setCloth_brand(rs.getString("cloth_brand"));
				cloth.setCloth_image(rs.getString("cloth_image"));
				cloth.setCloth_content(rs.getString("cloth_content"));
				cloth.setDiscount_rate(rs.getInt("discount_rate"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return cloth;
	}

	// 등록된 옷의 정보를 수정시 사용하는 메소드
	public void updateCloth(MngrDataBean cloth, int cloth_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update cloth set cloth_category=?, cloth_gender=?, cloth_name=?";
			sql += ", cloth_size=?, cloth_price=?, cloth_count=?, cloth_brand=?";
			sql += ", cloth_image=?, cloth_content=?, discount_rate=?";
			sql += " where cloth_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cloth.getCloth_category());
			pstmt.setString(2, cloth.getCloth_gender());
			pstmt.setString(3, cloth.getCloth_name());
			pstmt.setString(4, cloth.getCloth_size());
			pstmt.setInt(5, cloth.getCloth_price());
			pstmt.setInt(6, cloth.getCloth_count());
			pstmt.setString(7, cloth.getCloth_brand());
			pstmt.setString(8, cloth.getCloth_image());
			pstmt.setString(9, cloth.getCloth_content());
			pstmt.setInt(10, cloth.getDiscount_rate());
			//pstmt.setTimestamp(11, cloth.getReg_date());
			pstmt.setInt(11, cloth_id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}

	// cloth_id 에 해당하는 책의 정보를 삭제시 사용하는 메소드
	public void deleteCloth(int cloth_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from cloth where cloth_id=?");
			pstmt.setInt(1, cloth_id);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}

}
