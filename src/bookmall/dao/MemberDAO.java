package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVO;

public class MemberDAO {

	public List<MemberVO> getList() {

		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetction();

			String sql = "select name, phone, email from member order by no";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();// pstmt 내부에 있으므로 sql문장을 넣어줄 필요 없다.

			while (rs.next()) {
				String name = rs.getString(1);
				String phone = rs.getString(2);
				String email = rs.getString(3);

				MemberVO vo = new MemberVO();
				vo.setName(name);
				vo.setPhone(phone);
				vo.setEmail(email);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("errr=" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return list;
	}

	public Boolean insert(MemberVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();

			String sql = "insert into member values(null,?,?,?,password(?));";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());

			int cnt = pstmt.executeUpdate();
			result = cnt == 1 ? true : false;

		} catch (SQLException e) {
			System.out.println("errr=" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	public Boolean update(MemberVO vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();

			String sql = "update member set name = ?, phone = ?, email = ?, password = password(?) where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());
			pstmt.setLong(5, vo.getNo());

			int cnt = pstmt.executeUpdate();
			result = cnt == 1 ? true : false;

		} catch (SQLException e) {
			System.out.println("errr=" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	public Boolean delete(long no) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();
			
			String sql = "delete from member where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			int cnt = pstmt.executeUpdate();
			result = cnt==1?true:false;
			
		}catch(SQLException e) {
			System.out.println("errr="+e);
		}finally {
				try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn!=null) {
					conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			
		}
		return result;
	}

	private Connection getConnetction() throws SQLException {

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.37:3307/bookmall";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("DriverLoading 실패 : " + e);
		}

		return conn;
	}
}
