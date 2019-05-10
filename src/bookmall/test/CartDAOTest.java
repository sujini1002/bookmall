package bookmall.test;

import java.util.List;

import bookmall.dao.CartDAO;
import bookmall.vo.CartVO;

public class CartDAOTest {

	public static void main(String[] args) {
		getList(1L);
	}
	public static void insert(long book_no,long member_no,long cnt) {
		CartVO vo = new CartVO();
		vo.setBook_no(book_no);
		vo.setMember_no(member_no);
		vo.setCnt(cnt);
		
		new CartDAO().insert(vo);
	}
	public static void getList(long member_no) {
		List<CartVO> list = new CartDAO().getList(member_no);
		for(CartVO vo : list) {
			System.out.println(vo);
		}
	}
	public static void delete(long book_no,long member_no) {
		new CartDAO().delete(book_no,member_no);
	}
}
