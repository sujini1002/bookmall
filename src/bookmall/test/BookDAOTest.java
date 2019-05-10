package bookmall.test;

import java.util.List;

import bookmall.dao.BookDAO;
import bookmall.vo.BookVO;

public class BookDAOTest {

	public static void main(String[] args) {

		getList();
		
	}
	public static void insert(String category,String title,long price) {
		BookVO vo = new BookVO();
		vo.setCategory(category);
		vo.setTitle(title);
		vo.setPrice(price);
		
		new BookDAO().insert(vo);
	}
	public static void getList() {
		List<BookVO> list = new BookDAO().getList();
		for(BookVO vo : list) {
			System.out.println(vo);
		}
	}
	public static void update(long no,String category,String title,long price) {
		BookVO vo = new BookVO();
		vo.setNo(no);
		vo.setCategory(category);
		vo.setTitle(title);
		vo.setPrice(price);
		
		new BookDAO().update(vo);
	}
	public static void delete(long no) {
		new BookDAO().delete(no);
	}

}
