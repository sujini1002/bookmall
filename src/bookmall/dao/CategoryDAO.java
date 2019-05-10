package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVO;

public class CategoryDAO {
	
	public Boolean insert(String category) {
		Boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnetction();
			
			String sql = "insert into category values(null,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			
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
	
	public List<CategoryVO> getList() {
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnetction();
			
			String sql = "select no, name from category order by no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();//pstmt 내부에 있으므로 sql문장을 넣어줄 필요 없다.

			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				CategoryVO vo = new CategoryVO();
				vo.setNo(no);
				vo.setName(name);
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
