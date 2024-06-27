package clothshop.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dbcon.DBUtil;
import work.crypt.SHA256;
import work.crypt.BCrypt;

public class MemberDBBean {

	// LogonDBBean 전역 객체 생성 <- 한개의 객제만 생성해서 공유
	private static MemberDBBean instance = new MemberDBBean();

	// LogonDBBean객체를 리턴하는 메소드
	public static MemberDBBean getInstance() {
		return instance;
	}

	private MemberDBBean() {
	}

	// 회원 가입 처리에서 사용하는 메소드
	public void insertMember(LogonDataBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();

			String orgPass = member.getMember_passwd();
			System.out.println("member.getPasswd()=" + member.getMember_passwd());
			String shaPass = sha.getSha256(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

			pstmt = conn.prepareStatement(
					"insert into member(member_id, member_passwd, member_name, reg_date, member_address, member_postal_code, member_detailed_address, member_tel, member_gender) values(?,?,?,SYSDATE,?,?,?,?,?)");
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, bcPass);
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getMember_address());
			pstmt.setString(5, member.getMember_postal_code());
			pstmt.setString(6, member.getMember_detailed_address());
			pstmt.setString(7, member.getMember_tel());
			pstmt.setString(8, member.getMember_gender());
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

	// 로그인 폼 처리의 사용자 인증 처리에서 사용하는 메소드
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

			pstmt = conn.prepareStatement("select member_passwd from member where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디가 있으면 수행
				String dbpasswd = rs.getString("member_passwd");
				if (BCrypt.checkpw(shaPass, dbpasswd))
					x = 1; // 인증성공
				else
					x = 0; // 비밀번호 틀림
			} else// 해당 아이디 없으면 수행
				x = -1;// 아이디 없음

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
		return x;
	}

	// 아이디 중복 확인에서 아이디의 중복 여부를 확인하는 메소드
	public int confirmId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select member_id from member where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())// 아이디 존재
				x = 1; // 같은 아이디 있음
			else
				x = -1;// 같은 아이디 없음
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
		return x;
	}

	// 주어진 id에 해당하는 회원정보를 얻어내는 메소드
	public LogonDataBean getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select * from member where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				member = new LogonDataBean();// 데이터저장빈 객체생성
				member.setMember_id(rs.getString("member_id"));
				member.setMember_name(rs.getString(0));
				member.setReg_date(rs.getTimestamp("reg_date"));
				member.setMember_address(rs.getString("member_address"));
				member.setMember_postal_code(rs.getString("member_postal_code"));
				member.setMember_detailed_address(rs.getString("member_detailed_address"));
				member.setMember_tel(rs.getString("member_tel"));
				member.setMember_gender(rs.getString("member_gender"));
				member.setMember_grade(rs.getString("member_grade"));
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
		return member;// 데이터 저장빈 객체 member 리턴
	}

	// 주어진 id, passwd에 해당하는 회원정보를 얻어내는 메소드
	public LogonDataBean getMember(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;

		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();

			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());

			pstmt = conn.prepareStatement("select * from member where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				String dbpasswd = rs.getString("member_passwd");
				// 사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					member = new LogonDataBean();// 데이터저장빈 객체생성
					member.setMember_id(rs.getString("member_id"));
					member.setMember_name(rs.getString("member_name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setMember_address(rs.getString("member_address"));
					member.setMember_postal_code(rs.getString("member_postal_code"));
					member.setMember_detailed_address(rs.getString("member_detailed_address"));
					member.setMember_tel(rs.getString("member_tel"));
					member.setMember_gender(rs.getString("member_gender"));
					member.setMember_grade(rs.getString("member_grade"));
				}
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
		return member;// 데이터 저장빈 객체 member 리턴
	}

	// 회원 정보 수정을 처리하는 메소드
	@SuppressWarnings("resource")
	public int updateMember(LogonDataBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();

			String orgPass = member.getMember_passwd();
			String shaPass = sha.getSha256(orgPass.getBytes());

			pstmt = conn.prepareStatement("select member_passwd from member where member_id = ?");
			pstmt.setString(1, member.getMember_id());
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디가 있으면 수행
				String dbpasswd = rs.getString("member_passwd");
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					pstmt = conn.prepareStatement(
							"update member set member_passwd=?,member_address=?,member_postal_code=?,member_detailed_address=?,member_tel=? "
									+ "where member_id=?");
					pstmt.setString(1, member.getMember_passwd());
					pstmt.setString(2, member.getMember_address());
					pstmt.setString(3, member.getMember_postal_code());
					pstmt.setString(4, member.getMember_detailed_address());
					pstmt.setString(5, member.getMember_tel());
					pstmt.setString(6, member.getMember_id());
					pstmt.executeUpdate();
					x = 1;// 회원정보 수정 처리 성공
				} else
					x = 0;// 회원정보 수정 처리 실패
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
		return x;
	}

	// 회원 정보를 삭제하는 메소드
	@SuppressWarnings("resource")
	public int deleteMember(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();

			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());

			pstmt = conn.prepareStatement("select member_passwd from member where member_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbpasswd = rs.getString("member_passwd");
				if (BCrypt.checkpw(shaPass, dbpasswd)) {
					pstmt = conn.prepareStatement("delete from member where member_id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					x = 1;// 회원탈퇴처리 성공
				} else
					x = 0;// 회원탈퇴 처리 실패
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
		return x;
	}
	// ======================================================================== 추가항목

	// 1. 전체 회원수
	public int getMemberCount() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			sql = "select count(*) from member";
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbReleaseClose(rs, stmt, conn);
		}

		return count;
	}

	// 2. 회원 전체 리스트 호출
	public List<LogonDataBean> getMemberAll(int count) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;
		String sql = "";
		List<LogonDataBean> memberList = null;

		try {
			conn = DBUtil.getConnection();
			sql = "select * from member";
			stmt = conn.createStatement();
			rs= stmt.executeQuery(sql);

			memberList = new ArrayList<LogonDataBean>(count);
			while (rs.next()) {
				member = new LogonDataBean();
				member.setMember_id(rs.getString("member_id"));
				member.setMember_passwd(rs.getString("member_passwd"));
				member.setMember_name(rs.getString("member_name"));
				member.setReg_date(rs.getTimestamp("reg_date"));
				member.setMember_address(rs.getString("member_address"));
				member.setMember_postal_code(rs.getString("member_postal_code"));
				member.setMember_detailed_address(rs.getString("member_detailed_address"));
				member.setMember_tel(rs.getString("member_tel"));
				member.setMember_gender(rs.getString("member_gender"));
				member.setMember_grade(rs.getString("member_grade"));

				memberList.add(member);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, stmt, conn);
		}
		return memberList;
	}
	
	// 3. delete
	
}




















