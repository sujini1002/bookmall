package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVO;
import bookmall.vo.CartVO;

public class CartDAO {

	public List<CartVO> getList(long member_no) {

		List<CartVO> list = new ArrayList<CartVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetction();

			String sql = "select b.title,c.cnt,c.price from book b, member m, cart c where b.no = c.book_no and m.no = c.member_no and c.member_no = ? order by b.title";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, member_no);

			rs = pstmt.executeQuery();// pstmt 내부에 있으므로 sql문장을 넣어줄 필요 없다.

			while (rs.next()) {
				String title = rs.getString(1);
				long cnt = rs.getLong(2);
				long price = rs.getLong(3);

				CartVO vo = new CartVO();
				vo.setTitle(title);
				vo.setCnt(cnt);
				vo.setPrice(price);

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

	public Boolean insert(CartVO vo) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();

			String sql = "insert into cart values(?,?,?,(select price*? from book where no = ?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getBook_no());
			pstmt.setLong(2, vo.getMember_no());
			pstmt.setLong(3, vo.getCnt());
			pstmt.setLong(4, vo.getCnt());
			pstmt.setLong(5, vo.getBook_no());

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

	public Boolean delete(long book_no, long member_no) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();
			
			String sql = "delete from cart where book_no = ? and member_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, book_no);
			pstmt.setLong(2, member_no);
			
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
