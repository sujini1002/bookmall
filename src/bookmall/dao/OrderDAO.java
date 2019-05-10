package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderVO;
import bookmall.vo.OrderBookVO;

public class OrderDAO {
	public Long insertOrder(OrderVO vo) {
		Long order_no = 0L;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetction();

			String sql = "insert into orders values(null,?,(select concat(date_format(now(),'%Y%m%d'),'-',lpad((select count(*)from orders a where DATE(order_date) = DATE(now()))+1,5,'0'))),?,?,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getMember_no());
			pstmt.setLong(2, vo.getOrder_price());
			pstmt.setString(3, vo.getReceive());
			
			int cnt = pstmt.executeUpdate();
			if(cnt==1) {
				String id = "select last_insert_id()";
				pstmt = conn.prepareStatement(id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					order_no = rs.getLong(1);
				}
			}

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
		return order_no;
	}
	
	public boolean insertOrderBook(List<OrderBookVO> list) {
		Boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetction();
			for(OrderBookVO vo : list) {
				String sql = "insert into order_book values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, vo.getOrder_no());
				pstmt.setLong(2, vo.getBook_no());
				pstmt.setLong(3, vo.getCnt());

				int cnt = pstmt.executeUpdate();
				result = cnt == 1 ? true : false;
			}
			
			

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
	public List<OrderVO> getOrderList(long member_no){
		
		List<OrderVO> list = new ArrayList<OrderVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetction();

			String sql = "select o.order_no,if((select count(*) from order_book where order_no = o.no)<=1,b.title,concat(b.title, ' 외 ',(select count(*) from order_book where order_no = o.no)-1,'건')),date_format(o.order_date,'%Y-%m-%d %H:%i')as 'order_date',o.order_price,o.receive from orders o, order_book ob, book b where o.no = ob.order_no and ob.book_no = b.no and o.member_no = ? group by o.no order by o.order_date";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, member_no);

			rs = pstmt.executeQuery();// pstmt 내부에 있으므로 sql문장을 넣어줄 필요 없다.

			while (rs.next()) {
				String order_no = rs.getString(1);
				String order_title = rs.getString(2);
				String date = rs.getString(3);
				long order_price = rs.getLong(4);
				String receive = rs.getString(5);
				

				OrderVO vo = new OrderVO();
				vo.setOrder_no(order_no);
				vo.setOrder_title(order_title);
				vo.setDate(date);
				vo.setOrder_price(order_price);
				vo.setReceive(receive);

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
	public List<OrderBookVO> getOrderBookList(long order_no){
		List<OrderBookVO> list = new ArrayList<OrderBookVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnetction();

			String sql = "select b.title,b.price,ob.cnt from order_book ob, book b where ob.book_no = b.no and ob.order_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, order_no);

			rs = pstmt.executeQuery();// pstmt 내부에 있으므로 sql문장을 넣어줄 필요 없다.

			while (rs.next()) {
				String title = rs.getString(1);
				long price = rs.getLong(2);
				long cnt = rs.getLong(3);

				OrderBookVO vo = new OrderBookVO();
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCnt(cnt);

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
