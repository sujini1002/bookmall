package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVO;

public class BookDAO {
	public List<BookVO> getList() {
		
		List<BookVO> list = new ArrayList<BookVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetction();
			
			String sql = "select c.name,b.title,b.price from book b, category c where b.cate_no = c.no order by b.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();//pstmt 내부에 있으므로 sql문장을 넣어줄 필요 없다.

			while(rs.next()) {
				String category = rs.getString(1);
				String title = rs.getString(2);
				long price = rs.getLong(3);
				
				BookVO vo = new BookVO();
				vo.setCategory(category);
				vo.setTitle(title);
				vo.setPrice(price);
				
				list.add(vo);
			}
		}catch(SQLException e) {
			System.out.println("errr="+e);
		}finally {
				try {
					if(rs!=null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn!=null) {
					conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			
		}
		
		return list;
	}

	public Boolean insert(BookVO vo) {
		Boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();
			
			String sql = "insert into book values(null,(select no from category where name = ?),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setLong(3, vo.getPrice());
			
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

	public Boolean update(BookVO vo) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();
			
			String sql = "update book set cate_no = (select no from category where name = ?), title = ?,price = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCategory());
			pstmt.setString(2, vo.getTitle());
			pstmt.setLong(3, vo.getPrice());
			pstmt.setLong(4, vo.getNo());
			
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

	public Boolean delete(long no) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();
			
			String sql = "delete from book where no = ?";
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
